package cn.chef.security.utils;


import cn.chef.utils.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * 密码加密比对
 */
@Component
@Slf4j
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }
    public DefaultPasswordEncoder(int strength) {
    }
    //进行MD5加密
    @Override
    public String encode(CharSequence charSequence) {
        System.out.println("密码为："+charSequence.toString());
        System.out.println("密码为："+MD5.encrypt(charSequence.toString()));
        return MD5.encrypt(charSequence.toString());
    }
    //进行密码比对
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(charSequence.toString()));
    }
}
