package com.yicj.shiro.entity;/**
 * Created by yichengjie on 2017/7/19.
 */

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: ActivityUser</p>
 * <p>Description：</p>
 *
 * @version : 1.0
 * @author：yicj
 * @email:626659321@qq.com
 * @date： 2017-07-19 下午5:12
 **/
public class ActivityUser implements Serializable {

    private String userId ;
    private String username ;
    private List<String> menus ;
    private List<String> permissions;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
