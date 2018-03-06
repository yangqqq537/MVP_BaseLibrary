package com.mecby.base.net;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;

import com.mecby.base.base.AppConstant;
import com.mecby.base.base.BaseApplication;
import com.mecby.base.utils.Logger;
import com.mecby.base.utils.MD5Util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NativeHelper {

  /*  static {
        try {
            System.loadLibrary("native-lib");
        } catch (Throwable t) {
            if (null != t) {
                t.printStackTrace();
                Logger.d("loadLibrary failed=[%s]", String.valueOf(t.getMessage()));
            }
        }
    }*/

    private static class LazyHolder {
        private static final NativeHelper INSTANCE = new NativeHelper();
    }

    private NativeHelper() {
    }

    public static final NativeHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

    public HashMap<String, String> generateSignParams(HashMap<String, String> params, HashMap<String, String> header) {
        return generateSignParams(params, null, header);
    }

    public HashMap<String, String> generateSignParams(HashMap<String, String> params, List<String> excludeParasm, HashMap<String, String> header) {

        if (null == params) {
            params = new HashMap<>();
        }

        if (null == header) {
            header = new HashMap<>();
        }

        String timestamp = System.currentTimeMillis() + "";

        //params.put(AppConstant.TIMESTAMP, timestamp);
        //params.put(AppConstant.APPKEY, getAppKey());

        TreeMap<String, String> allParams = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return obj1.compareTo(obj2);
                    }
                });

        // 添加header参数，只有token，source，version
        allParams.put(AppConstant.VERSION, header.get(AppConstant.VERSION));
        allParams.put(AppConstant.SOURCE, header.get(AppConstant.SOURCE));
        //allParams.put(AppConstant.TOKEN, header.get(AppConstant.TOKEN));
        //allParams.put(AppConstant.CUSTOMER_NO,header.get(AppConstant.CUSTOMER_NO));

        // 添加get，post参数，excludeParasm中的参数不加入计算sign，如:fileName等
        allParams.putAll(params);
        Logger.d("allParams=[%s]", allParams.toString());

        // 将排序后的参数根据param+value拼接，过滤非法字段
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (null != entry && !TextUtils.isEmpty(entry.getKey())) {
                if (null != excludeParasm && excludeParasm.contains(entry.getKey())) {
                    continue;
                }

                String value = (TextUtils.isEmpty(entry.getValue()) || null == entry.getValue() || "null".equals(entry.getValue())) ? "" : entry.getValue();
                //Logger.d("[%s]=[%s]", entry.getKey(), entry.getValue());
                sb.append(entry.getKey() + value);
            }
        }

        // 拼接上secret
        try {
            //sb.append("04ecf46450224f41bd3805a7b19c1629");
           // String appSecurity = getAppSecurity();
           // System.out.println(appSecurity);
            //sb.append(appSecurity);
        } catch (Throwable t) {
            t.printStackTrace();
            Logger.d("get security fail=[%s]", t.getMessage());
        }
        Logger.d("pre md5=[%s]", sb.toString());

        String sign = MD5Util.MD5(sb.toString());
        Logger.d("sign=[%s]", sign);

        //params.put(AppConstant.SIGN, sign);
        Logger.d("allParams=[%s]", params.toString());

        return params;
    }

    public static String getAppKey() {
        return "a1-1-1";
    }

    public native String getAppSecurity();

    /**
     * @desc 在so反射调用该方法获取app签名
     * @author listen
     * @date 2016/11/22 17:43
     */
    public String getSignature() {
        final String packname = BaseApplication.getInstance().getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo =
                BaseApplication.getInstance()
                    .getPackageManager()
                    .getPackageInfo(packname, PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            Logger.d("signature=[%s]", sign.hashCode());
            return sign.hashCode() + "";
        } catch (Throwable t) {
            if (null != t) {
                t.printStackTrace();
            }
        }
        return "";
    }
}
