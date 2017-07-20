package com.yicj.shiro.realm;

import com.yicj.shiro.entity.ActivityUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yichengjie on 2017/7/18.
 */
public class CustomRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(CustomRealm.class) ;

    @Override
    public String getName() {
        return "customRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof UsernamePasswordToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("---------------------------------");
        logger.info("授权method is called ...");
        logger.info("---------------------------------");
        ActivityUser activityUser =  (ActivityUser)principalCollection.getPrimaryPrincipal() ;
        List<String> permissions = activityUser.getPermissions() ;

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo() ;
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("---------------------------------");
        logger.info("认证method is called ... ");
        logger.info("---------------------------------");
        String userCode =  (String) token.getPrincipal() ;
        //111111->md5->96e79218965eb72c92a549dd5a330112
        String pwd1 = "123" ;
        String pwd2 = "96e79218965eb72c92a549dd5a330112" ;
        String password_db = pwd2 ;
        //用户名不存在
        if(!"zhangsan".equals(userCode)){
            return null ;
        }

        List<String> permissions = new ArrayList<String>() ;
        permissions.add("user:query") ;
        permissions.add("user:add") ;
        permissions.add("user:edit") ;
        //permissions.add("user:delete") ;

        List<String> menus = new ArrayList<String>() ;
        menus.add("用户管理") ;
        menus.add("角色管理") ;
        menus.add("权限管理") ;


        ActivityUser activityUser = new ActivityUser() ;
        activityUser.setMenus(menus);
        activityUser.setPermissions(permissions);


        //Object principal, Object credentials, String realmName
        SimpleAuthenticationInfo simpleAuthenticationInfo
                = new SimpleAuthenticationInfo(activityUser,password_db,this.getName()) ;
        return simpleAuthenticationInfo;
    }


    //在权限修改后调用realm的此方法，清楚登录授权等信息
    public void clearCached(){
        Subject currentUser = SecurityUtils.getSubject() ;
        PrincipalCollection principals = currentUser.getPrincipals() ;
        super.clearCache(principals);
    }

}
