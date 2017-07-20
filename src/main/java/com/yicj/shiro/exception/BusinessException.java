package com.yicj.shiro.exception;

/**
 * Created by yichengjie on 2017/6/26.
 */
public class BusinessException extends AppException{

    public BusinessException(String code, String msg) {
        super(code, msg);
    }
}
