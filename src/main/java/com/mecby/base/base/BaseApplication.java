package com.mecby.base.base;

import android.app.Activity;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mecby.base.net.CustomSignInterceptor;
import com.mecby.base.net.img.LoaderManager;
import com.mecby.base.net.img.PicassoLoader;
import com.mecby.base.utils.Logger;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;

/**
 * Created by jerry on 2017/8/8 0008.
 *
 */

public abstract class BaseApplication extends Application {

    private static BaseApplication sInstance;


    public static BaseApplication getInstance() {
        return sInstance;
    }
    private Activity app_activity = null;
    @Override
    public void onCreate() {
        super.onCreate();
            sInstance = this;
            //外部定制图片框架,可定制,默认glide
            LoaderManager.setLoader(new PicassoLoader());
            //LoaderManager.getLoader().init(this);
            //读取缓存用户信息,配置信息
            CacheManager.getInstance().loadContext(this).loadUserInfo().loadConfigInfo();
            initArouter();
            initHttp();
       /* registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                app_activity=activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                app_activity=null;
            }
        });*/
    }

    /**
     * 公开方法，外部可通过 MyApplication.getInstance().getCurrentActivity() 获取到当前最上层的activity
     */
    public Activity getCurrentActivity() {
        return app_activity;
    }

    private void initHttp() {
        EasyHttp.init(this);
        EasyHttp.getInstance()
                .debug("base", Logger.DEBUG ? true : false)
                .setReadTimeOut(30 * 1000)
                .setWriteTimeOut(30 * 1000)
                .setConnectTimeout(30 * 1000)
                .setRetryCount(0)//默认网络不好自动重试1次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setBaseUrl(getBaseUrl())
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .setCertificates()//信任所有证书
                .addInterceptor(new CustomSignInterceptor());//添加参数签名拦截器
        //.addCommonHeaders(AppUtils.getHeaderMap())//设置全局公共头
        //.addCommonParams(params)//设置全局公共参数
        //.addInterceptor(new HeTInterceptor());//处理自己业务的拦截器
    }

    public abstract String getBaseUrl();


    private void initArouter() {
        if(Logger.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
            ARouter.init(this);
    }



}
