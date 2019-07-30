package com.example.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    //@PostMapping 处理post请求
    //@RequestParam  GET和POST请求传的参数会自动转换赋值到@RequestParam 所注解的变量上
    //@RequestParam（org.springframework.web.bind.annotation.RequestParam）用于将指定的请求参数赋值给方法中的形参。
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {

        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功,防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser", username);
            //重定向
            return "redirect:/main.html";
        } else {
            //登录失败
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }
}
