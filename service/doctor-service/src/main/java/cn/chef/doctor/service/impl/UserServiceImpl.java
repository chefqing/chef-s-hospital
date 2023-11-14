package cn.chef.doctor.service.impl;

import cn.chef.doctor.mapper.UserMapper;
import cn.chef.doctor.service.UserService;
import cn.chef.pojo.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
