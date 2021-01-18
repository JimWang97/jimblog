package com.jimwang.jimblog.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AdminShowBlogVo {

    private Long id;

    private String title;

    private String content;

    private String type;

    private int views;

    private Boolean recommend;

    private List<String> tags;

    private Date lastEdit;
}
