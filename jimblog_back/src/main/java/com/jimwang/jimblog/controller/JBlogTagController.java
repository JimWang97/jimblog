package com.jimwang.jimblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.entity.JBlogTag;
import com.jimwang.jimblog.service.JBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jimwang
 * @since 2021-01-17
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class JBlogTagController {
    @Autowired
    public JBlogTagService jBlogTagService;

    @GetMapping("/getTags")
    public R getTags(){
        QueryWrapper<JBlogTag> qw = new QueryWrapper<>();
        qw.select("count(tag) as tagNums, tag");
        qw.groupBy("tag");
        qw.orderByDesc("tagNums");
        List<JBlogTag> list = jBlogTagService.list(qw);
        return R.ok().data("tags", list);
    }
}

