package com.lemon.spring_first.interceptor;

import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.model.User;
import com.lemon.spring_first.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class SessionInterceptor implements HandlerInterceptor {


    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length >= 1) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample example = new UserExample();
                    example.createCriteria()
                            .andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(example);
//                    user = userMapper.findByToken(token);
                    System.out.println("拦截器，登录用户为："+users.toString());
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                    }
                    break;

                }
            }
        }

        return true;
    }

}
