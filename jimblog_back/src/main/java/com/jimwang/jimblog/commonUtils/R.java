package com.jimwang.jimblog.commonUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    // 私有构造方法
    private R(){}

    // 成功静态方法
    public static R ok(){
        R jsonRes = new R();
        jsonRes.setSuccess(true);
        jsonRes.setCode(ResultCode.SUCCESS);
        jsonRes.setMessage("成功");
        return jsonRes;
    }

    // 失败静态方法
    public static R error(){
        R jsonRes = new R();
        jsonRes.setSuccess(false);
        jsonRes.setCode(ResultCode.ERROR);
        jsonRes.setMessage("失败");
        return jsonRes;
    }

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

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
