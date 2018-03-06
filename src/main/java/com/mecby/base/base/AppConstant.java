package com.mecby.base.base;


/**
 * Created by dengyc on 15/6/9.
 * 常用变量集合
 */
public class AppConstant {
    // token
    public static final String USER_INFO = "user_info";
    public static final String CONFIG_INFO = "config_info";

    public static final String TIMESTAMP = "timestamp";
    public static final String APPKEY = "appkey";
    public static final String SIGN = "sign";

    public static final String SOURCE = "source";
    public static final String MODEL = "model";
    public static final String TOKEN = "token";
    public static final String SYSTEM_VERSION = "sysVersion";
    public static final String CUSTOMER_NO = "customerNo";
    public static final String VERSION = "version";
    public static String ANDROID="ANDROID";
    public static String QB10001="QB10001";


    public interface Paytype{
        String NO_PAY="0";
        String CANPAY_CANNOPAY="1";
        String SUIYI_PAY="2";
        String GUDING_PAY="3";
    }

    public interface ActiveType{
        String QIFU="qifu";
        String ONE_DAY="oneday";
        String FASHI="fashi";
        String QITA="qita";
        String GONGYANG="gongyang";
        String YIGONG="yigong";
    }

    public interface WechatOrAlipay{
        String Alipay="100";
        String WechatPay="200";
    }


    //登录页Path
    public static final String LOGIN_ACTIVITY="/activity/login";
    //扫描path
    public static final String SCAN_ACTIVITY="/activity/scan";
    //输入信息path
    public static final String INPUTINFO_ACTIVITY="/activity/inputinfo";
    //信息确认path
    public static final String CONFIRM_ACTIVITY="/activity/confirm";
    //支付或者提交完成
    public static final String FINISH_ACTIVITY="/activity/finish";
    //佛像列表页
    public static final String FOXIANG_ACTIVITY="/activity/foxiang";
    //用户信息页
    public static final String USERINFO_ACTIVITY="/activity/userinfo";
    //设置页
    public static final String SETTING_ACTIVITY="/activity/setting";
    //webview
    public static final String WEBVIEW_ACTIVITY="/activity/webview";
    //活动记录页
    public static final String RECORDS_ACTIVITY="/activity/records";
    //意见反馈页
    public static final String FEEDBACK_ACTIVITY="/activity/feedback";
    //供养和日行一善页面
    public static final String GY_ONEDAY_ACTIVITY="/activity/gy_oneday";
    //行善功德榜页
    public static final String SHOW_MERCY_ACTIVITY="/activity/show_mercy";
}
