package com.mecby.base.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 *
 */

public class DialogUtil {

    public static final String OK = "确定";
    public static final String CANCEL = "取消";
    public static final String LOADING ="加载中...";

    public static MaterialDialog showConfirmDialog(Context context, String message,
        MaterialDialog.SingleButtonCallback okCallback) {
        return showConfirmDialog(context, "", message, CANCEL, OK, null, okCallback);
    }

    public static MaterialDialog showConfirmDialog(Context context, String title, String message, String okText,
        MaterialDialog.SingleButtonCallback okCallback) {
        return showConfirmDialog(context, title, message, CANCEL, okText, null, okCallback);
    }

    public static MaterialDialog showConfirmDialog(Context context, String message, String okText, MaterialDialog.SingleButtonCallback okCallback) {
        return showConfirmDialog(context, "", message, "", okText, null, okCallback);
    }

    public static MaterialDialog showConfirmDialog(Context context, String title, String message, String cancelText,
        String okText, MaterialDialog.SingleButtonCallback cancelCallback, MaterialDialog.SingleButtonCallback okCallback) {
        MaterialDialog.Builder builder =
            new MaterialDialog.Builder(context).content(message)
                .positiveText(TextUtils.isEmpty(okText) ? OK : okText)
                .negativeText(TextUtils.isEmpty(cancelText) ? CANCEL : cancelText)
                .titleColorRes(android.R.color.black)
                .contentColorRes(android.R.color.black)
                .backgroundColorRes(android.R.color.white)
                    .negativeColor(Color.BLUE)
                    .positiveColor(Color.BLUE)
                .canceledOnTouchOutside(false)
                .cancelable(true)
                .onPositive(okCallback)
                .onNegative(null != cancelCallback ? cancelCallback : new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });

        if (!TextUtils.isEmpty(title)) {
            builder.title(title);
        }

        MaterialDialog dialog = builder.build();
        dialog.show();
        return dialog;
    }

    public static MaterialDialog showTipsDialog(Context context, String message) {
        return showTipsDialog(context, "", message, OK, null);
    }

    public static MaterialDialog showTipsDialog(Context context, String title, String message) {
        return showTipsDialog(context, title, message, OK, null);
    }


    public static MaterialDialog showTipsDialog(Context context, String message, MaterialDialog.SingleButtonCallback okCallback) {
        return showTipsDialog(context, "", message, OK, okCallback);
    }

    public static MaterialDialog showTipsDialog(Context context, String title, String message, String okText,
        MaterialDialog.SingleButtonCallback okCallback) {
        MaterialDialog.Builder builder =
            new MaterialDialog.Builder(context).content(message)
                .positiveText(TextUtils.isEmpty(okText) ? OK : okText)
                .titleColorRes(android.R.color.black)
                .contentColorRes(android.R.color.black)
                .backgroundColorRes(android.R.color.white)
                .canceledOnTouchOutside(false)
                .cancelable(true)
                    .negativeColor(Color.BLUE)
                    .positiveColor(Color.BLUE)
                .onPositive(null != okCallback ? okCallback : new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });

        if (!TextUtils.isEmpty(title)) {
            builder.title(title);
        }

        MaterialDialog dialog = builder.build();
        dialog.show();
        return dialog;
    }

    public static MaterialDialog createLoadingDailog(Context context, String text) {
        return new MaterialDialog.Builder(context).content(TextUtils.isEmpty(text) ? LOADING : text)
            .progress(true, 0)
            //.canceledOnTouchOutside(false)
            .progressIndeterminateStyle(false)
            .build();
    }


}
