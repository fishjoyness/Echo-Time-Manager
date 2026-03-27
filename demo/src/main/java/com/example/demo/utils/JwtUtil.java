package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ⚠️ 注意：每次重启服务器，这里会生成一个新随机密钥。
    // 这意味着重启后，以前发出的 Token 都会失效（需要重新登录），在开发阶段这是非常正常的防黑客手段！
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 1. 印发通行证 (原有功能)
    public String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // 2. 🌟 新增：验票！检查这串乱码是不是我们伪造的，有没有过期
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true; // 没抛异常就是好票！
        } catch (Exception e) {
            return false; // 伪造的或者过期的假票！
        }
    }

    // 3. 🌟 新增：从通行证里把用户名读出来
    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}