package com.jimwang.jimblog.handler;


import com.jimwang.jimblog.commonUtils.R;
import com.jimwang.jimblog.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public R error(Exception e){
//        // 将error输出到日志文件中
//        log.error(e.getMessage());
//        return R.error().message("发生了全局异常，请联系管理员...");
//    }
//
//    @ExceptionHandler(value = BlogException.class)
//    @ResponseBody
//    public R error(BlogException e){
//        // 将error输出到日志文件中
//        log.error(e.getMessage());
//        return R.error().code(e.getCode()).message(e.getMsg());
//    }
}
