package com.lemon.spring_first.mapper;

import com.lemon.spring_first.model.QuestionModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,tag,creator) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{tag},#{creator})")
    void createQuestion(QuestionModel question);
}
