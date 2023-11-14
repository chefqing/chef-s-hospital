package cn.chef.user.service.impl;


import cn.chef.pojo.User;
import cn.chef.security.pojo.SecurityUser;
import cn.chef.user.service.UserService;
import cn.chef.utils.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("UserDetailService")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    //todo UserService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(MD5.encrypt("admin"));
        if (user==null){
            throw  new UsernameNotFoundException("用户不存在");
        }
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(user);
        List<String> list = new ArrayList();
        list.add("ROLE_ADMIN");
        securityUser.setRoleValueList(list);
        log.info("username={}",username);
        log.info("password={}",user.getPassword());
        return securityUser;
    }
}
