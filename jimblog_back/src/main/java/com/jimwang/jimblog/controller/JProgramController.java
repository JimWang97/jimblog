package com.jimwang.jimblog.controller;


import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.entity.JBlog;
import com.jimwang.jimblog.entity.JBlogTag;
import com.jimwang.jimblog.entity.JProgram;
import com.jimwang.jimblog.entity.vo.AdminEditBlogVo;
import com.jimwang.jimblog.service.JProgramService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jimwang
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/api")
public class JProgramController {

    @Autowired
    JProgramService jProgramService;


}

