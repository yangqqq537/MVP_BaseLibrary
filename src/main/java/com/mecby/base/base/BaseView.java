package com.mecby.base.base;

import com.zhouyou.http.subsciber.IProgressDialog;

/**
 * Created by jerry on 2017/9/21 0021.
 * View层的基接口
 */

public interface BaseView {
    IProgressDialog getProgressDialog();

    void showToast(String msg);

    void loginAgain();

    void finishActivity();
}
