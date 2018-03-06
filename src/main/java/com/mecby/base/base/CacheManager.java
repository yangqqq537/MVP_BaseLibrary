package com.mecby.base.base;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mecby.base.mvp.bean.ConfigBean;
import com.mecby.base.mvp.bean.UserInfoBean;
import com.mecby.base.utils.SharedPreferenceUtils;

/**
 * Author:Jerry
 * Time:2017/10/20 0020   9:30
 * Des:缓存管理
 */

public class CacheManager {
    private Context context;

    //全局的app配置文件
    //private AppConfigData appConfigData;

    //用户账号信息
    private UserInfoBean userInfoData;
    //系统配置信息
    private ConfigBean mConfigInfo;
    //当前的版本
    private String versionCode;

    public static CacheManager getInstance() {
        return CacheManagerHolder.instance;
    }

    private static class CacheManagerHolder {
        private final static CacheManager instance = new CacheManager();
    }

    private CacheManager() {
    }

    /**
     * 设置context
     */
    public CacheManager loadContext(Context mContext) {
        this.context = mContext;
        return this;
    }

    /**********************************用户信息的缓存处理begin*********************************/

    public CacheManager loadUserInfo() {
        String userInfo = (String) SharedPreferenceUtils.get(context, AppConstant.USER_INFO, "");
        userInfoData = new Gson().fromJson(userInfo,UserInfoBean.class);
        return this;
    }

    /**
     * 加载token数据
     */
    public String getUseToken() {
        if (null != userInfoData) {
            return userInfoData.token;
        } else
            return "";
    }

    public UserInfoBean getUserInfoData() {
        return userInfoData;
    }

    public void setUserInfoData(UserInfoBean userInfoData) {
        this.userInfoData = userInfoData;
        //缓存到本地文件
        SharedPreferenceUtils.put(context, AppConstant.USER_INFO, new Gson().toJson(userInfoData));
    }

    /**
     * 清空缓存的用户信息
     */
    public void clearUserInfoData() {
        this.userInfoData = null;
        SharedPreferenceUtils.remove(context, AppConstant.USER_INFO);
    }

    public boolean islogin(){
        if(TextUtils.isEmpty(getUseToken()))
        return false;
        else
            return true;
    }
    /**********************************用户信息的缓存处理end*********************************/

/**********************************系统配置信息的缓存处理begin*********************************/

    public void setConfigInfo(ConfigBean configInfo) {
        mConfigInfo = configInfo;
        SharedPreferenceUtils.put(context, AppConstant.CONFIG_INFO, new Gson().toJson(configInfo));
    }

    public ConfigBean getConfigInfo() {
        return mConfigInfo;
    }


    public CacheManager loadConfigInfo(){
        String configInfo = (String) SharedPreferenceUtils.get(context, AppConstant.CONFIG_INFO, "");
        mConfigInfo = new Gson().fromJson(configInfo,ConfigBean.class);
        return this;
    }

    public String getConfigMd5(){
        if(mConfigInfo==null)
            return "";
        else
            return mConfigInfo.md5;
    }

    public void clearConfig(){
        mConfigInfo=null;
        SharedPreferenceUtils.remove(context,AppConstant.CONFIG_INFO);
    }
}
