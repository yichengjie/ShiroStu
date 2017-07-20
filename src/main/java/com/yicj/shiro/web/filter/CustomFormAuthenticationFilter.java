package com.yicj.shiro.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.yicj.shiro.entity.ActivityUser;
import com.yicj.shiro.web.common.ResultData;
import com.yicj.shiro.web.util.ResultDataUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by yichengjie on 2017/7/18.
 * 需要在验证账号和密码之前进行验证码的校验
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class) ;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest req = (HttpServletRequest)request ;
        //验证码校验
        HttpSession session = req.getSession() ;
        String validateCode = (String) session.getAttribute("validateCode") ;
        //页面输入的验证码
        String randomCode = req.getParameter("randomCode") ;
        //如果页面存在验证码功能的话
        logger.info("-------------------------------------");
        logger.info("validateCode : {} , randomCode : {}" ,validateCode ,randomCode);
        logger.info("-------------------------------------");
        if(randomCode != null && validateCode != null){
            if(!randomCode.equals(validateCode)){
                req.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME,"randomCodeError");
                //拒绝访问，不再校验账号和密码
                return true ;
            }
        }
        return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        Subject currentUser =  SecurityUtils.getSubject() ;
        ActivityUser activeUser = (ActivityUser)currentUser.getPrincipal() ;
        HttpSession session = ((HttpServletRequest)request).getSession();
        session.setAttribute("activeUser",activeUser);
        //this.printJsonToUI(response,activeUser);
        //经测试发现这里返回true或则false都无所谓
        //return true ;
        return super.onLoginSuccess(token, subject, request, response);
    }
//
//
//    private void printJsonToUI(ServletResponse response,Object obj){
//        PrintWriter out = null ;
//        ResultData resultData = null ;
//        try{
//            response.setCharacterEncoding("utf-8");
//            out = response.getWriter();
//            response.setContentType("application/json; charset=utf-8");
//            resultData = ResultDataUtil.success(obj) ;
//        }catch (Exception e){
//            logger.error("出错:",e);
//            resultData = ResultDataUtil.error(e) ;
//        }finally {
//            out.print(JSONObject.toJSONString(resultData));
//            out.flush();
//            out.close();
//        }
//    }

}
