package com.mecby.base.base;

import com.zhouyou.http.subsciber.IProgressDialog;

import io.reactivex.disposables.Disposable;

public interface BaseListener<T>{

    void loginAgain();

    void onRequestFail(String errMsg);

    void onRequestSussess(String string);

    void onRequestSussess();

    IProgressDialog getProgressDialog();

    void getDisposable(Disposable disposable);

    void onRequestSussess(T data);

}
