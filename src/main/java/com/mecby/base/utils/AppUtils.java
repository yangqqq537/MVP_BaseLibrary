package com.mecby.base.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mecby.base.base.AppConstant;
import com.mecby.base.base.BaseApplication;
import com.zhouyou.http.model.HttpHeaders;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.Build.VERSION_CODES.KITKAT;

/**.
 * app中公共的一些方法
 */
public class AppUtils {


    public static Resources getResources() {
        return BaseApplication.getInstance().getResources();
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static int getColor(int resId) {
        return BaseApplication.getInstance().getResources().getColor(resId);
    }

    /**
     * 获取一个参数的hasmap对象，里面默认会放些基本参数值,这里讲一些常用的参数配置放到htttp请求的头部来处理
     */
    public static HttpHeaders getHeaderMap() {
        HttpHeaders httpHeaders = new HttpHeaders();
        //String token = CacheManager.getInstance().getUseToken();
        //httpHeaders.put(AppConstant.TOKEN, TextUtils.isEmpty(token) ? "" : token);
        httpHeaders.put(AppConstant.SOURCE, AppConstant.ANDROID);
        httpHeaders.put(AppConstant.VERSION, AppUtils.getVersion());
       /* String model = Build.MODEL;
        String release = Build.VERSION.RELEASE;
        httpHeaders.put(AppConstant.VERSION, AppUtils.getVersion());
        httpHeaders.put(AppConstant.SOURCE, AppConstant.ANDROID);
        httpHeaders.put(AppConstant.MODEL, TextUtils.isEmpty(model) ? "" : model);
        httpHeaders.put(AppConstant.SYSTEM_VERSION, TextUtils.isEmpty(release) ? "" : release);
        httpHeaders.put(AppConstant.CUSTOMER_NO, AppConstant.QB10001);*/
        return httpHeaders;

    }

    public static String getVersion() {
        try {
            PackageInfo pi =
                    BaseApplication.getInstance()
                            .getPackageManager()
                            .getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            return pi.versionName;
        } catch (Exception e) {
            return "1";
        }
    }

    /**
     * @author jerry
     * @describe 控制edtext和软键盘
     * @date 2017/10/19 0019 11:53
     */
    public static void addLayoutListener(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                main.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                if (mainInvisibleHeight > 150) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    if(srollHeight<400)
                        srollHeight=400;
                    main.scrollTo(0, srollHeight);
                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
}

    /**
     * @author jerry
     * @describe 获取edittext的字符串
     * @date 2017/10/23 0023 9:41
     */
    public static String getEditStr(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * @author jerry
     * @describe 获取text的字符串
     * @date 2017/10/23 0023 9:41
     */
    public static String getStr(TextView tv) {
        return tv.getText().toString().trim();
    }

    /**
     * @author jerry
     * @describe 检查是否数字与英文结合
     * @date 2017/10/19 0019 16:57
     */
    public static boolean checkEngAndNum(String password) {
        boolean containNum = false;
        boolean containEng = false;
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar > 47 && aChar < 58) {
                containNum = true;
            } else if ((aChar > 96 && aChar < 123) || (aChar > 64 && aChar < 91))
                containEng = true;
        }
        return !(containEng && containNum);
    }

    public static int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px( float spValue) {
        final float fontScale = AppUtils.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void showKeyboard(final EditText et, int time) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(et, 0);
                           }
                       },
                time);
    }

    @RequiresApi(api = KITKAT)
    public static boolean isNotificationEnabled(Context context) {
        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
     /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }



}
