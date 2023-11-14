package cn.chef.acl.service.impl;

import cn.chef.acl.mapper.UserMapper;
import cn.chef.acl.service.UserService;
import cn.chef.pojo.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
* @author chef
* @description 针对表【tb_user(用户表)】的数据库操作Service实现
* @createDate 2023-09-28 23:51:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




