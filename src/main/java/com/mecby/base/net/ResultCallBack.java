package com.mecby.base.net;

import com.mecby.base.base.BaseListener;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;

import io.reactivex.disposables.Disposable;

/**
 * Created by jerry on 2017/9/21 0021.
 * 请求返回的结果,可对错误码进行处理
 */

public abstract class ResultCallBack<T> extends ProgressDialogCallBack<T> {
    private BaseListener mBaseListener;

    public ResultCallBack(BaseListener baseListener){
        this(baseListener.getProgressDialog(),true,baseListener);
    }
    public ResultCallBack(BaseListener baseListener,boolean isShowProgress){
        this(baseListener.getProgressDialog(),isShowProgress,baseListener);
    }

    public ResultCallBack(IProgressDialog progressDialog, boolean isShowProgress, BaseListener baseListener) {
        this(progressDialog,isShowProgress,false,baseListener);
    }

    public ResultCallBack(IProgressDialog progressDialog, boolean isShowProgress, boolean isOutSideCancel, BaseListener baseListener) {
        super(progressDialog, isShowProgress, isOutSideCancel);
        mBaseListener=baseListener;
    }

    @Override
    public void onError(ApiException e) {
        super.onError(e);
        if(mBaseListener==null)
            return;
        if(e.getCode()==401){
            mBaseListener.loginAgain();
            return;
        }
        mBaseListener.onRequestFail(e.getMessage());
    }

    @Override
    public void subscription(Disposable disposed) {
        super.subscription(disposed);
        if(mBaseListener==null)
            return;
        mBaseListener.getDisposable(disposed);
    }
}
