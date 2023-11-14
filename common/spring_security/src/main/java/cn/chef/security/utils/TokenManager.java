package cn.chef.security.utils;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * token 工具类
 */
@Component
public class TokenManager {
    //token有效时长
    private final long tokenExpiration = 24*60*60*1000;
    //编码秘钥
    private final String tokenSignKey = "123456";
    //1 使用jwt根据用户名生成token
    public String createToken(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+ tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
    }
    //2 根据token字符串得到用户信息
    public String getUserInfoFromToken(String token) {
        String userinfo = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return userinfo;
    }

    public void removeToken(String token) {
    }
}
