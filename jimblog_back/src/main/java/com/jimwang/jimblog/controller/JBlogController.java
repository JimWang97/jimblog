package com.jimwang.jimblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.entity.JBlog;
import com.jimwang.jimblog.entity.JBlogTag;
import com.jimwang.jimblog.entity.vo.AdminEditBlogVo;
import com.jimwang.jimblog.entity.vo.blogShowVo;
import com.jimwang.jimblog.service.JBlogService;
import com.jimwang.jimblog.service.JBlogTagService;
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
    @Autowired
    private JBlogTagService jBlogTagService;

//    admin操作

    @PostMapping("/admin/addBlog")
    public R adminAddBlog(@RequestBody AdminEditBlogVo adminEditBlogVo) {
        JBlog jBlog = new JBlog();
        BeanUtils.copyProperties(adminEditBlogVo, jBlog);
        jBlog.setPublished(true);
        jBlog.setLastEdit(new Date());
        boolean flag = jBlogService.save(jBlog);
        if (flag) {
            List<String> tags = adminEditBlogVo.getTags();
            boolean flag1 = true;
            for(String tag : tags) {
                flag1 &= jBlogTagService.save(new JBlogTag(jBlog.getId(), tag));
            }
            if(flag1) {
                return R.ok().message("添加博客成功");
            }
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
        List<blogShowVo> blogShowVos = new ArrayList<>();
        for(JBlog jb : jBlogs) {
            blogShowVo blogShowVo = new blogShowVo();
            BeanUtils.copyProperties(jb, blogShowVo);

            QueryWrapper<JBlogTag> qw = new QueryWrapper<>();
            Long id = jb.getId();
            qw.eq("blog_id", id);

            List<JBlogTag> jBlogTags = jBlogTagService.list(qw);
            List<String> tags = new ArrayList<>();
            for(JBlogTag jt : jBlogTags) {
                tags.add(jt.getTag());
            }
            blogShowVo.setTags(tags);
            blogShowVos.add(blogShowVo);
        }
        return R.ok().message("查询成功").data("blogs", blogShowVos);
    }

    @GetMapping("/admin/blog/{id}")
    public R adminGetBlog(@PathVariable Long id) {
        JBlog jBlog = jBlogService.getById(id);
        if(jBlog!=null) {
            blogShowVo blogShowVo = new blogShowVo();
            BeanUtils.copyProperties(jBlog, blogShowVo);

            QueryWrapper<JBlogTag> qw = new QueryWrapper<>();
            qw.eq("blog_id", jBlog.getId());

            List<JBlogTag> jBlogTags = jBlogTagService.list(qw);
            List<String> tags = new ArrayList<>();
            for(JBlogTag jt : jBlogTags) {
                tags.add(jt.getTag());
            }
            blogShowVo.setTags(tags);
            return R.ok().message("查询成功").data("blog", blogShowVo);
        }
        return R.error().message("查询失败或不存在该博客");
    }


//    用户操作

    @GetMapping("/blog/{id}")
    public R getBlog(@PathVariable Long id) {
        JBlog jBlog = jBlogService.getById(id);
        if(jBlog!=null) {
            blogShowVo blogShowVo = new blogShowVo();
            BeanUtils.copyProperties(jBlog, blogShowVo);

            QueryWrapper<JBlogTag> qw = new QueryWrapper<>();
            qw.eq("blog_id", jBlog.getId());

            List<JBlogTag> jBlogTags = jBlogTagService.list(qw);
            List<String> tags = new ArrayList<>();
            for(JBlogTag jt : jBlogTags) {
                tags.add(jt.getTag());
            }
            blogShowVo.setTags(tags);
            return R.ok().message("查询成功").data("blog", blogShowVo);
        }
        return R.error().message("查询失败或不存在该博客");
    }

    @GetMapping("/blogs")
    public R getBlogs() {
        List<JBlog> jBlogs = jBlogService.list();
        List<blogShowVo> blogShowVos = new ArrayList<>();
        for(JBlog jb : jBlogs) {
            blogShowVo blogShowVo = new blogShowVo();
            BeanUtils.copyProperties(jb, blogShowVo);

            QueryWrapper<JBlogTag> qw = new QueryWrapper<>();
            qw.eq("blog_id", jb.getId());

            List<JBlogTag> jBlogTags = jBlogTagService.list(qw);
            List<String> tags = new ArrayList<>();
            for(JBlogTag jt : jBlogTags) {
                tags.add(jt.getTag());
            }
            blogShowVo.setTags(tags);
            blogShowVos.add(blogShowVo);
        }
        return R.ok().message("查询成功").data("blogs", blogShowVos);
    }
}

