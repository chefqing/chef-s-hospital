package cn.chef.acl.controller;

import cn.chef.acl.service.RoleService;
import cn.chef.pojo.Role;
import cn.chef.utils.Result;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/acl/role")
public class RoleController {
    @Resource
    RoleService roleService;

    @ApiOperation("获取角色分页")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam("分页页码")
            @PathVariable Long page,
            @ApiParam("分页大小")
            @PathVariable Long limit,
            @RequestParam(required = false) String RoleName
    ){
        Page<Role> pagePram= new Page<>(page,limit);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isEmpty(RoleName)) {
            queryWrapper.like("name", RoleName);
        }
        Page<Role> pageRes = roleService.page(pagePram, queryWrapper);
        Map<String,Object> res = new HashMap<>();
        res.put("items",pageRes.getRecords());
        res.put("total",pageRes.getSize());
        return Result.error().data(res);
    }

    @PostMapping()
    public Result createRole(@RequestBody Role role){
        roleService.save(role);
        return Result.ok();
    }

}
