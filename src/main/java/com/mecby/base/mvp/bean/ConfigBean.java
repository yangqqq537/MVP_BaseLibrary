package com.mecby.base.mvp.bean;

import java.util.List;

/**
 * Author:Jerry
 * Time:2017/11/30 0030   15:00
 * Des:配置信息
 */

public class ConfigBean {


    /**
     * config : {"blessingPageUrl":"http://qibeitech.com.test","payTypeConfig":[{"isSelect":"0","name":"支付宝","type":"100"},{"isSelect":"1","name":"微信","type":"200"}],"yearList":["甲寅","乙卯","壬申","癸酉"],"monthList":["正月","腊月"],"dayList":["初一","三十"],"consecrateGuide":"供养活动引导语_再次测试","goodDeedEveryDayGuide":"日行一善活动引导语","masterDisabuseGuide":"师傅解惑活动引导语","volunteerGuide":"义工活动引导语"}
     * md5 : 984cc5aea1fec8236290969d0d1db377
     */

    public ConfigInnerBean config;
    public String md5;
    //当前的版本号
    public String versionCode;

    public static class ConfigInnerBean {
        /**
         * blessingPageUrl : http://qibeitech.com.test
         * payTypeConfig : [{"isSelect":"0","name":"支付宝","type":"100"},{"isSelect":"1","name":"微信","type":"200"}]
         * yearList : ["甲寅","乙卯","壬申","癸酉"]
         * monthList : ["正月","腊月"]
         * dayList : ["初一","三十"]
         * consecrateGuide : 供养活动引导语_再次测试
         * goodDeedEveryDayGuide : 日行一善活动引导语
         * masterDisabuseGuide : 师傅解惑活动引导语
         * volunteerGuide : 义工活动引导语
         * templeIntroPageUrl 寺庙一览
         * advertisingImage 广告图片
         * "customerService": "400-135-3588"
         * defaultPlayerImage:播放器默认图片
         * defaultBirthdayYear默认生日年份
         */
        public String defaultBirthdayYear;
        public String defaultPlayerImage;
        public String customerService;
        public String blessingPageUrl;
        public String consecrateGuide;
        public String goodDeedEveryDayGuide;
        public String masterDisabuseGuide;
        public String volunteerGuide;
        public String templeIntroPageUrl;
        public String advertisingImage;
        public List<PayTypeConfigBean> payTypeConfig;
        public List<String> yearList;
        public List<String> monthList;
        public List<String> dayList;

        public static class PayTypeConfigBean {
            /**
             * isSelect : 0
             * name : 支付宝
             * type : 100
             */

            public String isSelect;
            public String name;
            public String type;
        }
    }
}
