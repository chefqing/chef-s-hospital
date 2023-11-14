package cn.chef.acl.controller;

import cn.chef.acl.service.RoleService;
import cn.chef.acl.service.UserService;
import cn.chef.pojo.User;
import cn.chef.utils.MD5;
import cn.chef.utils.Result;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/acl/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    RoleService roleService;

    @ApiOperation("获取用户分页列表")
    @GetMapping(value = "{page}/{limit}")
    public Result index(
                        @ApiParam("页面大小")
                        @PathVariable Long limit,

                        @ApiParam("页码")
                        @PathVariable() Long page,

                        @ApiParam("用户名")
                        @RequestParam(required = false)
                            String name){
        QueryWrapper<User> wrapper= new QueryWrapper<>();
        if (StrUtil.isEmpty(name)) {
            wrapper.like("username",name);
        }
        Page<User> pageRes = userService.page(new Page<>(page, limit),wrapper);

        return Result.ok().data("items",pageRes.getRecords()).data("total",pageRes.getSize());
    }

    @ApiOperation("添加管理用户")
    @PostMapping
    public Result createUser(@RequestBody User user){
        if (StrUtil.isEmpty(user.getPassword())) {
            return Result.error().message("密码不能为空");
        }
        user.setPassword(MD5.encrypt(user.getPassword()));
        return Result.ok();
    }
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return Result.ok().data(roleMap);
    }



}
