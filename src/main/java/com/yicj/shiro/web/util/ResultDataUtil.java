package com.yicj.shiro.web.util;

import com.yicj.shiro.exception.AppException;
import com.yicj.shiro.exception.ParameterException;
import com.yicj.shiro.web.common.ResultData;

/**
 * Created by yichengjie on 2017/6/26.
 */
public class ResultDataUtil {


    public static void main(String [] args){

        ResultData result = success("success") ;

    }

    public static  ResultData success(Object data){
        ResultData result = new ResultData () ;
        result.setFlag(true) ;
        result.setMsg("success");
        result.setData(data);
        return result ;
    }

    public static ResultData success(){

        return success(null);
    }


    public static ResultData error(Exception ex) {
        String msg = ex.getMessage() ;
        String code = "-1" ;
        if(ex instanceof AppException) {
            code = ((AppException)ex).getCode() ;
        }
        return error(msg,"-1") ;
    }

    public static ResultData error(String msg) {
        return error(msg,"-1") ;
    }

    public static ResultData error(String msg,String code) {
        ResultData result = new ResultData();
        result.setFlag(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
