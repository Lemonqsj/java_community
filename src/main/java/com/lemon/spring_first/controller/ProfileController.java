package com.lemon.spring_first.controller;

import com.lemon.spring_first.dto.QuestionIndexDto;
import com.lemon.spring_first.mapper.QuestionMapper;
import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.model.User;
import com.lemon.spring_first.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {


    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(value = "page",defaultValue = "1") Integer page ,
                          @RequestParam(value = "size",defaultValue = "5") Integer size) {


        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("selection", "questions");
            model.addAttribute("selectionName", "我的提问");

        } else if ("replies".equals(action)) {
            model.addAttribute("selection", "replies");
            model.addAttribute("selectionName", "最新回复");
        }

        QuestionIndexDto list = questionService.list(user.getId(), page, size);
        model.addAttribute("questionsList", list);
        return "profile";

    }
}
