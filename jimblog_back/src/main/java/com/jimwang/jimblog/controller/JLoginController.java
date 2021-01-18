package com.jimwang.jimblog.controller;

import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.entity.vo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JLoginController {

    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletRequest request) {
        if("jimwang".equals(user.getUsername())) {
            if ("Whoyouare48.".equals(user.getPwd())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", "jimwanglogin");
                return R.ok().message("登录成功");
            }
        }
        return R.error().message("登录失败，用户名或者密码错误");
    }

    @GetMapping("logout")
    public R logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return R.ok().message("登出成功");
    }
}
