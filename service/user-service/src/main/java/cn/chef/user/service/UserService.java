package cn.chef.user.service;

import cn.chef.pojo.User;
import cn.chef.user.pojo.RequestParam;
import cn.chef.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    Result sendCode(String phone);

    Result info();

    Result cancel();

    Result signup(RequestParam param);

    Result login(RequestParam requestParam);
}
