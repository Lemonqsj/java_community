package com.lemon.spring_first.dto;

import com.lemon.spring_first.model.User;
import lombok.Data;

@Data
public class QuestionDto {


    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private User user;
}
