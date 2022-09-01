package com.pad.exceptionhandler;

import com.pad.response.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice //给Controller控制器添加统一的操作或处理
public class GlobalExceptionHandler {
    //全局异常处理
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody  //返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    @ExceptionHandler(PadException.class)
    @ResponseBody  //返回数据
    public R error(PadException e){
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
