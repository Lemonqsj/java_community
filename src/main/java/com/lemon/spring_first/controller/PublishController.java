package com.lemon.spring_first.controller;

import com.lemon.spring_first.mapper.QuestionMapper;
import com.lemon.spring_first.mapper.UserMapper;
import com.lemon.spring_first.model.QuestionModel;
import com.lemon.spring_first.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class PublishController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request, HttpServletResponse response) {
        return "publish";
    }



    @PostMapping("/publish")
    public String doPostPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);




        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies!=null&&cookies.length>=1){

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                     user= userMapper.findByToken(token);
                    break;

                }
            }
        }


        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }


        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if (description==null||description==""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }

        if (tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        QuestionModel question = new QuestionModel();
        question.setTitle(title);
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.createQuestion(question);

        return "redirect:/";

    }

}
