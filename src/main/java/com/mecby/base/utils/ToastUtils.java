package com.mecby.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 *
 * 自定义toast
 */
public class ToastUtils {

    static Toast toast;

    public static void show(Context mContext, String content) {
        if (mContext == null) {
            Logger.d("mContext is null");
            return;
        }
        if (TextUtils.isEmpty(content)) return;
        if (toast == null)
            toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        else
            toast.setText(content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    public static void cancelToast(){
        if(toast!=null)
            toast.cancel();
    }

}
