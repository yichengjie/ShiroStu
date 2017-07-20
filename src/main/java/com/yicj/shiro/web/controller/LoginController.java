package com.yicj.shiro.web.controller;

import com.yicj.shiro.entity.ActivityUser;
import com.yicj.shiro.exception.BusinessException;
import com.yicj.shiro.realm.CustomRealm;
import com.yicj.shiro.web.common.ResultData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by yichengjie on 2017/7/18.
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    @Qualifier("customRealm")
    private  CustomRealm customRealm ;

    @RequestMapping("/login")
    //@ResponseBody
    public String login(HttpServletRequest req, HttpSession session){
        logger.info("login is call...");
        Subject currentUser =  SecurityUtils.getSubject() ;
        //String username = (String)currentUser.getPrincipal() ;
        //logger.info("当前登录的用户名为{}",username);
        String exceptionClassname = (String) req.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME) ;
        logger.info("exceptionClassname : {}" ,exceptionClassname);

        //这里一定要清除验证码错误
        //session.removeAttribute("validateCode");
        if(exceptionClassname != null){
            if(UnknownAccountException.class.getName().equals(exceptionClassname)){
                throw new BusinessException("10000","账号不存在") ;
            }else if(IncorrectCredentialsException.class.getName().equals(exceptionClassname)){
                throw new BusinessException("10000","密码错误") ;
            } else if("randomCodeError".equals(exceptionClassname)){
                throw new BusinessException("10000","验证码输入有误") ;
            }else{
                throw new RuntimeException("其他错误 " + exceptionClassname) ;
            }
        }

        //如果没有登录则会走这里，跳转到登录页面
        return "/login.jsp" ;
    }

    @RequestMapping("clearCached")
    @ResponseBody
    public ResultData clearCached(){
        this.customRealm.clearCached();
        return this.success("清除缓存成功！") ;
    }
}
