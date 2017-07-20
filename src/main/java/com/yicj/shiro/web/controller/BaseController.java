package com.yicj.shiro.web.controller;

import com.yicj.shiro.exception.AppException;
import com.yicj.shiro.exception.ParameterException;
import com.yicj.shiro.web.common.ResultData;
import com.yicj.shiro.web.util.ResultDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yichengjie on 2017/6/26.
 */
@RequestMapping("api")
public class BaseController {

    //注意这里不能使用@Resource注解，因为在jboss5中会报错
    //@Resource(name="category4Service")
    protected Logger logger = LoggerFactory.getLogger(BaseController.class) ;


    /** 全局异常处理 */
//    @ExceptionHandler
//    @ResponseBody
//    public ResultData exp(HttpServletRequest request, Exception ex) {
//        //request.setAttribute("ex", ex);
//        // 根据不同错误转向不同页面
//        logger.info("测试中文乱码。。。。");
//        if(ex instanceof AppException) {
//            logger.error("business error : " ,ex);
//            return ResultDataUtil.error(ex) ;
//        }else if(ex instanceof ParameterException) {
//            logger.error("parameter error : " ,ex);
//            return ResultDataUtil.error(ex);
//        } else {
//            logger.error("system error : " ,ex);
//            return ResultDataUtil.error(ex) ;
//        }
//    }

    public void printObj(Object obj){



    }

    public ResultData success(Object data){

        return ResultDataUtil.success(data) ;
    }

    public ResultData success(){
        return ResultDataUtil.success() ;
    }

    public ResultData error(Exception ex){
        return ResultDataUtil.error(ex) ;
    }

    public ResultData error(String msg){
        return ResultDataUtil.error(msg) ;
    }

    public ResultData error(String msg,String code){
        return ResultDataUtil.error(msg,code) ;
    }

}
