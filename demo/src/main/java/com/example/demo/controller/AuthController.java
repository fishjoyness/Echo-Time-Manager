package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // 统一前缀，专门管身份认证
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 极其强大的单向加密机

    @Autowired
    private JwtUtil jwtUtil; // 我们的通行证打印机

    // ==========================================
    // 1. 注册接口：负责收录新人并给密码上锁
    // ==========================================
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody AuthRequest request) {
        Map<String, Object> response = new HashMap<>();

        // 1. 检查账号是否被抢注了
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            response.put("success", false);
            response.put("message", "账号已存在，换个霸气点的名字吧！");
            return response;
        }

        // 2. 创建新用户，极其重要：密码必须加密后才能存进数据库！
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // 把 123456 变成一段乱码暗文

        userRepository.save(newUser);

        response.put("success", true);
        response.put("message", "注册成功！欢迎加入 Echo！");
        return response;
    }

    // ==========================================
    // 2. 登录接口：负责核验身份并颁发 JWT 通行证
    // ==========================================
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AuthRequest request) {
        Map<String, Object> response = new HashMap<>();

        // 1. 去数据库里找这个账号
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "查无此人，请先注册！");
            return response;
        }

        User user = userOpt.get();

        // 2. 密码大比对：拿着前端传来的明文，去和数据库里的暗文强行匹配
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            response.put("success", false);
            response.put("message", "密码错误，再想想？");
            return response;
        }

        // 3. 身份确认无误，印发专属 JWT 通行证！
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());

        response.put("success", true);
        response.put("message", "登录成功！");
        response.put("token", token); // 把通行证发给前端保管
        response.put("userId", user.getId()); // 顺便把 ID 给前端，方便它接下来的操作

        System.out.println("🎉 玩家 [" + user.getUsername() + "] 已上线！通行证已发放。");
        return response;
    }
}