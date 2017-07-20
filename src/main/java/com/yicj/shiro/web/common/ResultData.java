package com.yicj.shiro.web.common;

/**
 * 后台返回给前台的数据类型
 * Created by yichengjie on 2017/6/26.
 */
public class ResultData {

    //代码是否执行成功
    private boolean flag ;
    //错误码
    private String code ;
    //提示信息
    public String msg ;
    //返回的数据
    public Object data ;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
