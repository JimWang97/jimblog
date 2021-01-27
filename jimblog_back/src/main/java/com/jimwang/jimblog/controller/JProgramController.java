package com.jimwang.jimblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.entity.JBlog;
import com.jimwang.jimblog.entity.JBlogTag;
import com.jimwang.jimblog.entity.JProgram;
import com.jimwang.jimblog.entity.vo.AdminEditBlogVo;
import com.jimwang.jimblog.entity.vo.blogShowVo;
import com.jimwang.jimblog.service.JProgramService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/admin/addProgram")
    public R addProgram(@RequestBody JProgram program) {
        if(program.getId()==null) {
            program.setLastEdit(new Date());
            boolean flag = jProgramService.save(program);
            if (flag) {
                return R.ok().message("添加项目成功");
            }
            return R.error().message("添加项目失败");
        } else {
            program.setLastEdit(new Date());
            boolean flag = jProgramService.updateById(program);
            if(flag) {
                return R.ok().message("修改项目成功");
            }
            return R.error().message("修改项目失败");
        }
    }

    @DeleteMapping("/admin/program/{id}")
    public R adminDeleteProgram(@PathVariable Long id) {
        boolean flag = jProgramService.removeById(id);
        if (flag) {
            return R.ok().message("删除博客成功");
        }
        return R.error().message("删除博客失败或者不存在该博客");
    }

    @GetMapping("/admin/programs/{page}/{limit}")
    public R adminGetBlogs(@PathVariable long page, @PathVariable long limit) {
        Page<JProgram> pageProgram = new Page<>(page, limit);
        QueryWrapper<JProgram> qw1 = new QueryWrapper<>();
        qw1.orderByDesc("last_edit");
        jProgramService.page(pageProgram, qw1);
        long total = pageProgram.getTotal();
        List<JProgram> jPrograms = pageProgram.getRecords();
        return R.ok().message("查询成功").data("programs", jPrograms).data("total", total);
    }

    @GetMapping("/admin/program/{id}")
    public R adminGetProgram(@PathVariable Long id) {
        JProgram jProgram = jProgramService.getById(id);
        if(jProgram!=null) {
            return R.ok().message("查询成功").data("program", jProgram);
        }
        return R.error().message("查询失败或不存在该项目");
    }

    @GetMapping("/programs/{page}/{limit}")
    public R getBlogs(@PathVariable long page, @PathVariable long limit) {
        Page<JProgram> pageProgram = new Page<>(page, limit);
        QueryWrapper<JProgram> qw1 = new QueryWrapper<>();
        qw1.orderByDesc("last_edit");
        jProgramService.page(pageProgram, qw1);
        long total = pageProgram.getTotal();
        List<JProgram> jPrograms = pageProgram.getRecords();
        return R.ok().message("查询成功").data("programs", jPrograms).data("total", total);
    }
}

