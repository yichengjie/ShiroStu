package com.yicj.shiro.web.controller;

import com.yicj.shiro.web.common.ResultData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yichengjie on 2017/7/19.
 */
@Controller
public class UserController extends BaseController {

    @RequestMapping("/userDelete")
    @ResponseBody
    //@RequiresPermissions("user:delete")
    public ResultData userDelete(){


        return this.success("删除成功！") ;
    }


}
