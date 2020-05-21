package com.lemon.spring_first.service;


import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.model.User;
import com.lemon.spring_first.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdateUser(User user) {

        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
//        User dbUser=userMapper.selectUserByAccountId(user.getAccountId());

       if (users.size()==0){
           //插入用户
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
//           userMapper.insertUser(user);
       }else {
           //更新用户
           User dbUser=new User();
           dbUser.setGmtModified(user.getGmtCreate());
           dbUser.setToken(user.getToken());
           dbUser.setName(user.getName());
           dbUser.setAvatarUrl(user.getAvatarUrl());
           dbUser.setBio(user.getBio());
           UserExample example1 = new UserExample();
           example1.createCriteria()
                   .andAccountIdEqualTo(user.getAccountId());
           userMapper.updateByExampleSelective(dbUser,example1);
//           userMapper.updateByExample(user, example1);
//           userMapper.updateByPrimaryKey(user);
//           userMapper.updateUser(user);

       }

    }
}
