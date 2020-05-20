package com.lemon.spring_first.mapper;


import com.lemon.spring_first.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@RequestParam("id") Integer id);

    @Select("select * from user where account_id=#{accountId}")
    User selectUserByAccountId(@RequestParam("accountId")String accountId);

    @Update("update user set name=#{name},token=#{token},avatar_url=#{avatarUrl},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified} where account_id=#{accountId}")
    void updateUser(User user);
}
