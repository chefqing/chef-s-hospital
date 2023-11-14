package cn.chef.security.filter;

import cn.chef.security.utils.TokenManager;
import cn.chef.utils.RedisConstants;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TokenAuthFilter extends BasicAuthenticationFilter {


    private final TokenManager tokenManager;
    private final StringRedisTemplate redisTemplate;
    public TokenAuthFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, StringRedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取当前认证成功用户权限信息
        UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
        //判断如果有权限信息，放到权限上下文中
        if(authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // 去掉前缀 "Bearer "
            // 处理 token
            if(token != null) {
                //从token获取用户名
                String username = tokenManager.getUserInfoFromToken(token);
                //从redis获取对应权限列表
                String rolesStr = redisTemplate.opsForValue().get(RedisConstants.USER_INFO + username);
                List<String> roleValueList = JSONUtil.toList(rolesStr, String.class);
                Collection<GrantedAuthority> authority = new ArrayList<>();
                for(String permissionValue : roleValueList) {
                    SimpleGrantedAuthority auth = new SimpleGrantedAuthority(permissionValue);
                    authority.add(auth);
                }
                return new UsernamePasswordAuthenticationToken(username,token,authority);
            }
        }
        return null;
    }

}
