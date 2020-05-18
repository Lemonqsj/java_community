package com.lemon.spring_first.controller;

import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @GetMapping("/")
    public String test(HttpServletRequest request, HttpServletResponse response) {



        Cookie[] cookies = request.getCookies();

        if (cookies!=null&&cookies.length>=1){

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user= userMapper.findByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;

                }
            }
        }




        return "index";
    }
}
