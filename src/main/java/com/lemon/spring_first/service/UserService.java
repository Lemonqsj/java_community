package com.lemon.spring_first.service;


import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdateUser(User user) {

        User dbUser=userMapper.selectUserByAccountId(user.getAccountId());

       if (dbUser==null){
           //插入用户
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insertUser(user);
       }else {
           //更新用户
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.updateUser(user);

       }

    }
}
