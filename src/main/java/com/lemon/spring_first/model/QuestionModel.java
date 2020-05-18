package com.lemon.spring_first.model;

import lombok.Data;

@Data
public class QuestionModel {

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


}
