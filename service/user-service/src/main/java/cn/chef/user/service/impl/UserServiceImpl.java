package cn.chef.user.service.impl;

import cn.chef.pojo.User;
import cn.chef.security.utils.TokenManager;
import cn.chef.user.mapper.UserMapper;
import cn.chef.user.pojo.RequestParam;
import cn.chef.user.service.UserService;
import cn.chef.utils.MD5;
import cn.chef.utils.RedisConstants;
import cn.chef.utils.Result;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.ws.BindingType;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private TokenManager tokenManager;

    private  final  static String USERNAME_PREFIX = "user_";
    //todo
    @Override
    public Result sendCode(String phone) {
        if (!PhoneUtil.isPhone(phone)) {
            return Result.error().message("手机号格式不对");
        }
        String code= RandomUtil.randomNumbers(6);
        String key = RedisConstants.USER_CODE+phone;
        redisTemplate.opsForValue().set(key,code ,RedisConstants.USER_CODE_TTL, TimeUnit.MINUTES);
        return Result.ok().data("code",code);
    }

    //todo
    @Override
    public Result info() {
        return null;
    }

    //todo
    @Override
    public Result cancel() {
        return null;
    }

    @Override
    public Result signup(RequestParam param) {
        if(param==null){
            return Result.error().message("请求参数不能为空");
        }
        if (!PhoneUtil.isPhone(param.getPhone())) {
            return Result.error().message("手机号不符合格式");
        }
        if (!StringUtils.hasLength(param.getPassword())){
            return Result.error().message("初始密码不能为空");
        }
        String key = RedisConstants.USER_CODE+param.getPhone();
        String code = redisTemplate.opsForValue().get(key);
        // 验证码已经过期
        if (code==null){
            return Result.error().message("验证码已经过期");
        }
        if (!code.equals(param.getCode())){
            return Result.error().message("验证码错误");
        }
        // 查询是否已经注册
        Optional<User> userOpt = query().eq("username", param.getPhone()).oneOpt();
        if (userOpt.isPresent()) {
            return Result.error().message("该账号已经被注册");
        }

        // 没有注册则保存到数据库中
        User user = new User();
        user.setUsername(param.getPhone());
        user.setPassword(param.getPassword());
        save(user);
        return Result.ok().message("注册成功");
    }

    /**
     * 登录
     * @param requestParam 请求参数；
     * @return
     */
    @Override
    public Result login(RequestParam requestParam) {
        String phone = requestParam.getPhone();
        String password = requestParam.getPassword();
        String code = requestParam.getCode();
        if (!PhoneUtil.isPhone(phone)) {
            return Result.error().message("手机格式不对");
        }

        if (!StringUtils.hasLength(password)){
          return loginByCode(phone,code);
        }
        return loginByPassword(phone,password);
    }

    // todo 密码登录逻辑
    private Result loginByPassword(String phone, String password) {
        User user = query().eq("username", phone).one();
        if (user==null){
            return Result.error().message("该用户不存在");
        }
        if(!MD5.encrypt(password).equals(user.getPassword())){
            return Result.error().message("密码错误");
        }
        // 查询角色列表
        List<String> roles = baseMapper.selectRolesByUserId(user.getId());
        //保存到redis 中
        JSON jsonStr = JSONUtil.parse(roles);
        redisTemplate.opsForValue().set(phone,jsonStr.toString(), RedisConstants.TOKEN_DEFAULT_TTL, TimeUnit.MINUTES);
        String token = tokenManager.createToken(user.getUsername());
        String tokenWithPrefix = "Bearer " + token;
        return  Result.ok().data("Authorization",tokenWithPrefix);
    }

    // todo 验证码登录逻辑
    private Result loginByCode(String phone, String code) {
        User user = query().eq("username", phone).one();
        if (user==null){
            //todo  用户不存在就注册
            return Result.error().message("该用户不存在");
        }
        // 从redis 获取验证码
        return null;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name="user-exchange"),
            key = "add"
    ))
    void addListener(User user){
        System.out.println("添加用户");
        user.setPassword("user"+user.getUsername());
        save(user);
        // todo 保存并赋予权限
    }
}
