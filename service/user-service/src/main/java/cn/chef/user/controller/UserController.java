package cn.chef.user.controller;

import cn.chef.user.pojo.RequestParam;
import cn.chef.user.service.UserService;
import cn.chef.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @ApiOperation("发送验证码")
    @GetMapping("/send/{phone}")
    public Result sendCode(@PathVariable String phone){
        return userService.sendCode(phone);
    }
    @ApiOperation("查看用户信息")
    @GetMapping("info")
    public Result info(){
        return userService.info();
    }
    @ApiOperation("注册账号")
    @PostMapping("/signup")
    public Result signup(@RequestBody RequestParam param){
        return userService.signup(param);
    }

    @ApiOperation("注销账号")
    @GetMapping("cancelAccount")
    public Result cancel(){
        return userService.cancel();
    }
    @ApiOperation("登录")
    @PostMapping
    Result login(@RequestBody RequestParam requestParam){
        return  userService.login(requestParam);
    }

}
