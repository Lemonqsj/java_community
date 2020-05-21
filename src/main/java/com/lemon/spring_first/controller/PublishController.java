package com.lemon.spring_first.controller;

import com.lemon.spring_first.dto.QuestionDto;
import com.lemon.spring_first.mapper.QuestionMapper;
import com.lemon.spring_first.model.Question;
import com.lemon.spring_first.model.User;
import com.lemon.spring_first.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController {


    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable(name = "id") Integer id,
                               Model model){
        QuestionDto questionModel = questionService.getQuestionDtoById(id);
        model.addAttribute("title",questionModel.getTitle() );
        model.addAttribute("description", questionModel.getDescription());
        model.addAttribute("tag", questionModel.getTag());
        model.addAttribute("id", id);

        return "publish";
    }

    @GetMapping("/publish")
    public String publish(HttpServletRequest request, HttpServletResponse response) {
        return "publish";
    }



    @PostMapping("/publish")
    public String doPostPublish(
            @RequestParam(value = "id",required = false) Integer id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("id", id);


        User user = (User) request.getSession().getAttribute("user");


        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }


        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }

        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdateQuestion(question);

//        questionMapper.createQuestion(question);

        return "redirect:/";

    }

}
