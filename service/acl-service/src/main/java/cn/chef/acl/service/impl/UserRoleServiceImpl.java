package cn.chef.acl.service.impl;

import cn.chef.acl.mapper.UserRoleMapper;
import cn.chef.acl.service.UserRoleService;
import cn.chef.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
* @author chef
* @description 针对表【tb_user_role(用户角色表)】的数据库操作Service实现
* @createDate 2023-10-07 16:30:50
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

}




