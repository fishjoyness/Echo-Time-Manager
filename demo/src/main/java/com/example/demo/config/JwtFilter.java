package com.example.demo.config;

import com.example.demo.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 从前端发来的请求头里，找到名叫 "Authorization" 的那个口袋
        String authHeader = request.getHeader("Authorization");

        // 2. 如果口袋里有东西，并且是以 "Bearer " 开头的（这是国际标准格式）
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // 把前缀去掉，只留那串乱码

            // 3. 让 JwtUtil 去验一验这张票是真是假
            if (jwtUtil.validateToken(token)) {
                // 4. 验票通过！提取用户名，并在系统的安全大厅里给他登记，直接放行！
                String username = jwtUtil.extractUsername(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 5. 无论有没有票，接着往下走（因为有些接口比如注册/登录是不需要票的）
        filterChain.doFilter(request, response);
    }
}