package com.jimwang.jimblog.controller;

import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.entity.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JLoginController {

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        // 获取权限操作对象，利用这个对象来完成登录操作
        Subject subject = SecurityUtils.getSubject();
        // 登录之前先登出一次，为了防止因为缓存的原因，无法切换账号。
        subject.logout();
        // 用户是否认证过（是否登陆过）
        if(!subject.isAuthenticated()){
            // 创建用户认证的身份令牌，并设置从页面中传递过来的账号和密码
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPwd());
            try {
                // 用户登录。会自动调用realm对象中的认证方法
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e){
                return R.error().message("账号或密码错误错误");
            } catch (LockedAccountException e){
                return R.error().message("账号或密码错误错误");
            }catch (IncorrectCredentialsException e){
                return R.error().message("账号或密码错误错误");
            }catch (AuthenticationException e){
                return R.error().message("账号或密码错误错误");
            }
        }
//        if("jimwang".equals(user.getUsername())) {
//            if ("Whoyouare48.".equals(user.getPwd())) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", "jimwanglogin");
//                return R.ok().message("登录成功");
//            }
//        }
        return R.ok().message("登录成功");
    }

    @GetMapping("logout")
    public R logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return R.ok().message("登出成功");
    }
}
