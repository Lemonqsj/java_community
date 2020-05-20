package com.lemon.spring_first.mapper;

import com.lemon.spring_first.dto.QuestionDto;
import com.lemon.spring_first.model.QuestionModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,tag,creator) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{tag},#{creator})")
    void createQuestion(QuestionModel question);

    @Select("select * from question limit #{offsize},#{size}")
    List<QuestionModel> list(@RequestParam("offsize") Integer offsize,
                             @RequestParam("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

//    @Select("select * from user where token=#{token}")
    @Select("select * from question where creator=#{userId} limit #{offsize},#{size}")
    List<QuestionModel> listByUserId(@Param("userId") Integer userId,
                             @Param("offsize") Integer offsize,
                             @Param("size") Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countById(@Param("userId") Integer userId);

    @Select("select * from question where id=#{id}")
    QuestionModel selectQuestionById(@Param("id") Integer id);

    @Update({"update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}"})
    void updateQuestion(QuestionModel question);
}
