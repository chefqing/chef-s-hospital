package cn.chef.acl.service.impl;

import cn.chef.acl.mapper.RoleMapper;
import cn.chef.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.chef.pojo.Role;
import org.springframework.stereotype.Service;


import java.util.Map;

/**
* @author chef
* @description 针对表【tb_role(角色表)】的数据库操作Service实现
* @createDate 2023-09-28 23:51:47
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {



    @Override
    public Map<String, Object> findRoleByUserId(String userId) {
        return null;
    }
}




