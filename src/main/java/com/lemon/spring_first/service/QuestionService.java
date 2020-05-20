package com.lemon.spring_first.service;

import com.lemon.spring_first.dto.QuestionDto;
import com.lemon.spring_first.dto.QuestionIndexDto;
import com.lemon.spring_first.mapper.QuestionMapper;
import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.model.QuestionModel;
import com.lemon.spring_first.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    public QuestionIndexDto list(Integer page, Integer size) {
        QuestionIndexDto questionIndexDto = new QuestionIndexDto();
        Integer totalCount = questionMapper.count();
        questionIndexDto.setPageValue(totalCount,page,size);
        Integer totalPage=0;
        if (totalCount % size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }

        Integer offsize=size*(page-1);
        List<QuestionModel> questionModels = questionMapper.list(offsize,size);
        List<QuestionDto> questionDtos=new ArrayList<>();


        questionModels.forEach(new Consumer<QuestionModel>() {
            @Override
            public void accept(QuestionModel questionModel) {
                User user=userMapper.findById(questionModel.getCreator());
                QuestionDto question = new QuestionDto();
                BeanUtils.copyProperties(questionModel,question);
                question.setUser(user);
                questionDtos.add(question);
            }
        });
        questionIndexDto.setQuestionDtos(questionDtos);
        return questionIndexDto;
    }

    public QuestionIndexDto list(Integer userId, Integer page, Integer size) {

        QuestionIndexDto questionIndexDto = new QuestionIndexDto();
        Integer totalCount = questionMapper.countById(userId);
        questionIndexDto.setPageValue(totalCount,page,size);
        Integer totalPage=0;
        if (totalCount % size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }

        Integer offsize=size*(page-1);
        List<QuestionModel> questionModels = questionMapper.listByUserId(userId,offsize,size);
        List<QuestionDto> questionDtos=new ArrayList<>();


        questionModels.forEach(new Consumer<QuestionModel>() {
            @Override
            public void accept(QuestionModel questionModel) {
                User user=userMapper.findById(questionModel.getCreator());
                QuestionDto question = new QuestionDto();
                BeanUtils.copyProperties(questionModel,question);
                question.setUser(user);
                questionDtos.add(question);
            }
        });
        questionIndexDto.setQuestionDtos(questionDtos);
        return questionIndexDto;

    }

    public QuestionDto getQuestionDtoById(Integer id) {
       QuestionModel questionModel= questionMapper.selectQuestionById(id);
        User user=userMapper.findById(questionModel.getCreator());
        QuestionDto question = new QuestionDto();
        BeanUtils.copyProperties(questionModel,question);
        question.setUser(user);
        return question;
    }

    public void createOrUpdateQuestion(QuestionModel question) {

        if (question.getId()!=null){
            //更新数据
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateQuestion(question);
        }else {
            //插入问题
            questionMapper.createQuestion(question);
        }
    }
}


