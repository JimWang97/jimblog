package com.jimwang.jimblog.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AdminEditBlogVo {

    private String title;

    private String content;

    private Boolean type;

    private Boolean recommend;

    private List<String> tags;
}
