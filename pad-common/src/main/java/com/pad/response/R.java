package com.pad.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class R {

    /*code 0成功 -1失败 状态码*/
    private int code;
    /*success error 是否成功*/
    private Boolean success;
    /*msg 返回信息*/
    private String message;
    /*泛型  Map 返回数据*/
    private Map<String,Object> data = new HashMap<String,Object>();

    private R(){}

    /*成功静态方法*/
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /*成功失败方法*/
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    //其他值设置，返回调用该方法的对象
    //达到链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(Map<String ,Object> map){
        this.setData(map);
        return this;
    }

    //向map中添加值
    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R put(String data, HashMap<String, Object> map) {
        this.data.put(data,map);
        return this;
    }

}
