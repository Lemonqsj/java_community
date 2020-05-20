package com.lemon.spring_first.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionIndexDto {
    private List<QuestionDto> questionDtos;
    private boolean showPre;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    public void setPageValue(Integer totalCount, Integer page, Integer size) {

        pages.clear();

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
        currentPage=page;


        //页码列表

        /**
         *
         * 1  1234
         * 2  12345
         * 3  123456
         * 4  1234567
         * 5  2345678
         */
        pages.add(page);

        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }

            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //如果页码中不包含第一页那么就显示点击到上一页

        if (page==1){
            showPre=false;
        }else {
            showPre=true;
        }

        if (pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }


//         如果当前页码不等于最后一页那么就可以显示点击下一页
        if (page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }


        if (pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }


    }
}
