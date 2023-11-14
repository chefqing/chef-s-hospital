package cn.chef.acl.service;

import cn.chef.pojo.Role;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author chef
* @description 针对表【tb_role(角色表)】的数据库操作Service
* @createDate 2023-09-28 23:51:47
*/
public interface RoleService extends IService<Role> {

    Map<String, Object> findRoleByUserId(String userId);
}
