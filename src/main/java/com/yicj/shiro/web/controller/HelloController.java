package com.yicj.shiro.web.controller;

import com.yicj.shiro.web.common.ResultData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yichengjie on 2017/7/14.
 */
@Controller
public class HelloController extends  BaseController{


    @RequestMapping("/hello")
    @ResponseBody
    public ResultData hello(){

        Subject currentUser =  SecurityUtils.getSubject() ;

        String username = (String)currentUser.getPrincipal() ;

        logger.info("当前登录的用户名为{}",username);

        logger.info("hello world ");
        logger.info("hello world ");
        logger.info("hello world ");
        logger.info("hello world ");

        return this.success("hello world") ;
    }

}
