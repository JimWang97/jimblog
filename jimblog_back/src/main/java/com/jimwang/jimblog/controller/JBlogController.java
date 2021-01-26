package com.jimwang.jimblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        if(adminEditBlogVo.getId()==null) {
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
        } else {
            JBlog jBlog = new JBlog();
            BeanUtils.copyProperties(adminEditBlogVo, jBlog);
            jBlog.setPublished(true);
            jBlog.setLastEdit(new Date());
            boolean flag = jBlogService.updateById(jBlog);
            if(flag) {
                return R.ok().message("修改博客成功");
            }
            return R.error().message("修改博客失败");
        }
    }

    @PostMapping("/admin/saveBlog")
    public R adminSaveBlog(@RequestBody AdminEditBlogVo adminEditBlogVo) {
        if(adminEditBlogVo.getId()==null) {
            JBlog jBlog = new JBlog();
            BeanUtils.copyProperties(adminEditBlogVo, jBlog);
            jBlog.setPublished(false);
            jBlog.setLastEdit(new Date());
            boolean flag = jBlogService.save(jBlog);
            if (flag) {
                List<String> tags = adminEditBlogVo.getTags();
                boolean flag1 = true;
                for(String tag : tags) {
                    flag1 &= jBlogTagService.save(new JBlogTag(jBlog.getId(), tag));
                }
                if(flag1) {
                    return R.ok().message("保存博客成功");
                }
            }
            return R.error().message("保存博客失败");
        } else {
            JBlog jBlog = new JBlog();
            BeanUtils.copyProperties(adminEditBlogVo, jBlog);
            jBlog.setPublished(false);
            jBlog.setLastEdit(new Date());
            boolean flag = jBlogService.updateById(jBlog);
            if(flag) {
                return R.ok().message("保存博客成功");
            }
            return R.error().message("保存博客失败");
        }
    }

    @DeleteMapping("/admin/blog/{id}")
    public R adminDeleteBlog(@PathVariable Long id) {
        boolean flag = jBlogService.removeById(id);
        if (flag) {
            QueryWrapper<JBlogTag> qw = new QueryWrapper<>();
            qw.eq("blog_id", id);
            boolean flag1 = jBlogTagService.remove(qw);
            if(flag1){
                return R.ok().message("删除博客成功");
            }
        }
        return R.error().message("删除博客失败或者不存在该博客");
    }

    @GetMapping("/admin/blogs/{page}/{limit}")
    public R adminGetBlogs(@PathVariable long page, @PathVariable long limit) {
        Page<JBlog> pageBlog = new Page<>(page, limit);
        QueryWrapper<JBlog> qw1 = new QueryWrapper<>();
        qw1.orderByDesc("last_edit");
        jBlogService.page(pageBlog, qw1);
        long total = pageBlog.getTotal();
        List<JBlog> jBlogs = pageBlog.getRecords();
        List<blogShowVo> blogShowVos = JBlogToShowBlog(jBlogs);
        return R.ok().message("查询成功").data("blogs", blogShowVos).data("total", total);
    }

    @GetMapping("/admin/blog/{id}")
    public R adminGetBlog(@PathVariable Long id) {
        JBlog jBlog = jBlogService.getById(id);
        if(jBlog!=null) {
            blogShowVo blogShowVo = new blogShowVo();
            BeanUtils.copyProperties(jBlog, blogShowVo);
            if(jBlog.getType()){
                blogShowVo.setType("原创");
            } else {
                blogShowVo.setType("转载");
            }
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

    @GetMapping("/admin/getStat")
    public R getStat() {
        long blogCount=0, sumView=0;
        QueryWrapper<JBlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sum(views) as sumView, count(id) as blogCount");
        JBlog b= jBlogService.getOne(queryWrapper);
        //注意，空指针问题
        if (b== null){
            sumView=(Long.valueOf(0));
            blogCount=(Long.valueOf(0));
        }else{
            sumView=(b.getSumView());
            blogCount=(b.getBlogCount());
        }
        return R.ok().data("count", blogCount).data("views", sumView);
    }


//    用户操作

    @GetMapping("/blog/{id}")
    public R getBlog(@PathVariable Long id) {
        JBlog jBlog = jBlogService.getById(id);
        if(jBlog!=null) {
            jBlog.setViews(jBlog.getViews()+1);
            jBlogService.updateById(jBlog);
            blogShowVo blogShowVo = new blogShowVo();
            BeanUtils.copyProperties(jBlog, blogShowVo);
            if(jBlog.getType()){
                blogShowVo.setType("原创");
            } else {
                blogShowVo.setType("转载");
            }
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

    @GetMapping("/blogs/{page}/{limit}")
    public R getBlogs(@PathVariable long page, @PathVariable long limit) {
        Page<JBlog> pageBlog = new Page<>(page, limit);
        QueryWrapper<JBlog> qw1 = new QueryWrapper<>();
        qw1.eq("published", true);
        qw1.orderByDesc("last_edit");
        jBlogService.page(pageBlog, qw1);
        long total = pageBlog.getTotal();
        List<JBlog> jBlogs = pageBlog.getRecords();
        List<blogShowVo> blogShowVos = JBlogToShowBlog(jBlogs);
        return R.ok().message("查询成功").data("blogs", blogShowVos).data("total", total);
    }

    @GetMapping("/search/{title}/{page}/{limit}")
    public R search(@PathVariable String title, @PathVariable long page, @PathVariable long limit) {
        Page<JBlog> pageBlog = new Page<>(page, limit);
        QueryWrapper<JBlog> qw1 = new QueryWrapper<>();
        qw1.eq("published", true);
        qw1.like("title", title);
        qw1.orderByDesc("last_edit");
        jBlogService.page(pageBlog, qw1);
        long total = pageBlog.getTotal();
        List<JBlog> jBlogs = pageBlog.getRecords();
        List<blogShowVo> blogShowVos = JBlogToShowBlog(jBlogs);
        return R.ok().message("查询成功").data("blogs", blogShowVos).data("total", total);
    }

    @GetMapping("/topBlogs/{num}")
    public R getTopBlogs(@PathVariable int num) {
        QueryWrapper<JBlog> qw = new QueryWrapper<>();
        qw.eq("recommend", true);
        qw.eq("published", true);
        qw.orderByDesc("last_edit");
        qw.last("limit 0," + num);
        List<JBlog> jBlogs = jBlogService.list(qw);
        List<blogShowVo> blogShowVos = JBlogToShowBlog(jBlogs);
        return R.ok().message("查询成功").data("blogs", blogShowVos);
    }

    public List<blogShowVo> JBlogToShowBlog(List<JBlog> jBlogs) {
        List<blogShowVo> blogShowVos = new ArrayList<>();
        for(JBlog jb : jBlogs) {
            blogShowVo blogShowVo = new blogShowVo();
            BeanUtils.copyProperties(jb, blogShowVo);
            if(jb.getType()){
                blogShowVo.setType("原创");
            } else {
                blogShowVo.setType("转载");
            }
            if(jb.getPublished()) {
                blogShowVo.setPublished("已发布");
            } else {
                blogShowVo.setPublished("草稿");
            }
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
        return blogShowVos;
    }
}

