package com.mecby.base.base;

import com.google.gson.annotations.SerializedName;
import com.zhouyou.http.model.ApiResult;

/**
 * Created by jerry on 2017/9/20 0020.
 * 返回数据的基础类,
 */

public class BaseApiBean<T> extends ApiResult<T> {

    /**
     * body : {"list":[{"bikeId":"70300323","imageDesc":"","imageUrl":"https://qibei-app.s3-ap-northeast-1.amazonaws.com/parkFolder/201707177e02fed36190.jpg","latitude":"30.236406375209395","longitude":"120.2037328514298","thumbImageUrl":"https://qibei-app.s3-ap-northeast-1.amazonaws.com/parkFolder/201707177e02fed36190.jpg?x-oss-process=image/resize,m_fill,h_110,w_130"}],"tips":"There are 1 bikes available in your area"}
     * meta : {"code":"200","message":"SUCCESS"}
     */
    @SerializedName("body")
     T body;
    @SerializedName("meta")
     MetaBean meta;

    public static class MetaBean {
        /**
         * code : 200
         * message : SUCCESS
         */
        @SerializedName("code")
        public String code;
        @SerializedName("message")
        public String message;

        @Override
        public String toString() {
            return "MetaBean{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    @Override
    public int getCode() {
        return Integer.valueOf(meta.code);
    }


    @Override
    public String getMsg() {
        return meta.message;
    }


    @Override
    public T getData() {
        return body;
    }

    @Override
    public boolean isOk() {
        return Integer.valueOf(meta.code)==200;
    }

    @Override
    public String toString() {
        return "BaseApiBean{" +
                "result=" + body +
                ", meta=" + meta.toString() +
                '}';
    }
}
