package com.yicj.shiro.exception;/**
 * Created by yichengjie on 2017/7/19.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: CustomExceptionResolver</p>
 * <p>Description： 统一异常处理</p>
 * @version : 1.0
 * @author：yicj
 * @email:626659321@qq.com
 * @date： 2017-07-19 下午4:27
 **/
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private  Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class) ;

    /**
     * 前端控制器DispatcherServlet在执行HandlerMapping，调用HandlerAdapter执行Handler过程中，
     * 如果遇到异常就会执行此方法,
     * handler最终要执行Handler，它的真实身份是HandlerMethod
     * @param request
     * @param response
     * @param o
     * @param ex 接受到的异常信息
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                 HttpServletResponse response, Object o, Exception ex) {
        logger.error("统一异常处理 : ",ex);
        String message = null ;
        AppException appException = null ;
        if(ex instanceof AppException ){
            appException = (AppException)ex ;
        }else{
            appException = new BusinessException("1000","系统异常") ;
        }
        message = appException.getMessage() ;
        request.setAttribute("message",message);
        try{
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }catch (Exception e){
            logger.error("统一异常处理:",e);
        }
        return null;
    }
}
