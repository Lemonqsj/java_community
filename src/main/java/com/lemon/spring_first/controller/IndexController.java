package com.lemon.spring_first.controller;

import com.lemon.spring_first.dto.QuestionIndexDto;
import com.lemon.spring_first.mapper.QuestionMapper;
import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {



    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String test(Model model,
                       @RequestParam(value = "page",defaultValue = "0") Integer page ,
                       @RequestParam(value = "size",defaultValue = "5") Integer size) {

        QuestionIndexDto questionsList = questionService.list(page,size);
        model.addAttribute("questionsList", questionsList);
        return "index";
    }
}
