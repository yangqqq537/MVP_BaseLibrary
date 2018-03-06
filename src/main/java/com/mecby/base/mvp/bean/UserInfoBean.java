package com.mecby.base.mvp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Author:Jerry
 * Time:2017/10/20 0020   9:25
 * Des:登录后的数据
 */

public class UserInfoBean {

    /**
     * payType : 1
     * token : f6c0088294effa821deeb5151b65769c9f34cc2fae834524514cda00c8fea68a
     */
    @SerializedName("token")
    public String token;


    /**
     * userInfo : {"name":"章文君","mobile":"13588755652","gender":"1","birthday":"1990-05-25","address":"杭州萧山"}
     */
    @SerializedName("userInfo")
    public UserBean userInfo;

    public static class UserBean {
        /**
         * name : 章文君
         * mobile : 13588755652
         * gender : 1
         * birthday : 1990-05-25
         * address : 杭州萧山
         */

        public String name;
        public String mobile;
        public String gender;
        public String birthday;
        public String address;


        public  String getSexName(){
            if("0".equals(gender))
                return "女";
            else if("1".equals(gender))
                return "男";
            return "";
        }
    }
}
