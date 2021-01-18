package com.jimwang.jimblog.controller;


import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.entity.JBlog;
import com.jimwang.jimblog.entity.vo.AdminEditBlogVo;
import com.jimwang.jimblog.entity.vo.AdminShowBlogVo;
import com.jimwang.jimblog.service.JBlogService;
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
 * @since 2021-01-17
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class JBlogController {
    @Autowired
    private JBlogService jBlogService;

//    admin操作

    @PostMapping("/admin/addBlog")
    public R adminAddBlog(@RequestBody AdminEditBlogVo adminEditBlogVo) {
        JBlog jBlog = new JBlog();
        BeanUtils.copyProperties(adminEditBlogVo, jBlog);
        jBlog.setPublished(true);
        jBlog.setLastEdit(new Date());
        boolean flag = jBlogService.save(jBlog);
        if (flag) {
            return R.ok().message("添加博客成功");
        }
        return R.error().message("添加博客失败");
    }

    @PostMapping("/admin/saveBlog")
    public R adminSaveBlog(@RequestBody AdminEditBlogVo adminEditBlogVo) {
        JBlog jBlog = new JBlog();
        BeanUtils.copyProperties(adminEditBlogVo, jBlog);
        jBlog.setPublished(false);
        jBlog.setLastEdit(new Date());
        boolean flag = jBlogService.save(jBlog);
        if (flag) {
            return R.ok().message("保存博客成功");
        }
        return R.error().message("保存博客失败");
    }

    @DeleteMapping("/admin/blog/{id}")
    public R adminDeleteBlog(@PathVariable Long id) {
        boolean flag = jBlogService.removeById(id);
        if (flag) {
            return R.ok().message("删除博客成功");
        }
        return R.error().message("删除博客失败或者不存在该博客");
    }

    @PutMapping("/admin/blog/{id}")
    public R adminEditBlog(@RequestBody AdminEditBlogVo adminEditBlogVo, @PathVariable Long id) {
        JBlog jBlog = new JBlog();
        BeanUtils.copyProperties(adminEditBlogVo, jBlog);
        jBlog.setId(id);
        jBlog.setPublished(true);
        jBlog.setLastEdit(new Date());
        boolean flag = jBlogService.updateById(jBlog);
        if(flag) {
            return R.ok().message("修改博客成功");
        }
        return R.error().message("修改博客失败");
    }

    @GetMapping("/admin/blogs")
    public R adminGetBlogs() {
        List<JBlog> jBlogs = jBlogService.list();
        List<AdminShowBlogVo> adminShowBlogVos = new ArrayList<>();
        for(JBlog jb : jBlogs) {
            //todo jblog封装到showblogvo中
        }
        return R.ok().message("查询成功").data("blogs", jBlogService.list());
    }

    @GetMapping("/admin/blog/{id}")
    public R adminGetBlog(@PathVariable Long id) {
        JBlog jBlog = jBlogService.getById(id);
        if(jBlog!=null) {
            return R.ok().message("查询成功").data("blog", jBlog);
        }
        return R.error().message("查询失败或不存在该博客");
    }


//    用户操作

    @GetMapping("/blog/{id}")
    public R getBlog(@PathVariable Long id) {
        JBlog jBlog = jBlogService.getById(id);
        if(jBlog!=null) {

            return R.ok().message("查询成功").data("blog", jBlog);
        }
        return R.error().message("查询失败或不存在该博客");
    }

    @GetMapping("/blogs")
    public R getBlogs() {
        //todo jblog封装到showblogvo中
        return R.ok().message("查询成功").data("blogs", jBlogService.list());
    }
}

