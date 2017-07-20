package com.yicj.shiro.exception;

/**
 * 业务异常
 * Created by yichengjie on 2017/6/26.
 */
public abstract class AppException extends  RuntimeException{

    private String code ;

    public AppException(String msg){
        super(msg);
        this.code = "1000" ;//参数异常码为1000
    }

    public AppException(String code , String msg){
        super(msg);
        this.code = code ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
