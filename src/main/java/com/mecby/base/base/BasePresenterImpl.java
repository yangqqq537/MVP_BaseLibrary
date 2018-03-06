package com.mecby.base.base;

import com.zhouyou.http.subsciber.IProgressDialog;

import io.reactivex.disposables.Disposable;

/**
 * Created by jerry on 2017/9/21 0021.
 * Present的实现基类
 * 要得到View层使用getView,要得到Mode层使用getModeImpl
 */

public abstract class BasePresenterImpl<T extends BaseView,M extends BaseModeImpl> implements BasePresenter,BaseListener{
    private T mBaseView;
    private M mBaseMode;

    public BasePresenterImpl(T baseView) {
        mBaseView=baseView;
        mBaseMode=createBaseModeImpl();
    }

    protected abstract M createBaseModeImpl();

    public M getModeImpl(){
        return mBaseMode;
    }
    public T getView(){
        return mBaseView;
    }


    @Override
    public IProgressDialog getProgressDialog() {
        if(mBaseView!=null)
            return mBaseView.getProgressDialog();
        else
            return null;
    }

    @Override
    public void loginAgain() {
        if(mBaseView!=null)
            mBaseView.loginAgain();
    }

    @Override
    public void onRequestFail(String errMsg) {
        if(mBaseView!=null)
            mBaseView.showToast(errMsg);
    }

    @Override
    public void onRequestSussess(String string) {

    }

    @Override
    public void onRequestSussess() {

    }

    @Override
    public void onRequestSussess(Object data) {

    }

    private Disposable disposed;

    @Override
    public void getDisposable(Disposable disposable) {
        this.disposed=disposable;
    }

    @Override
    public void cancelRequest() {
        if (disposed != null && !disposed.isDisposed()) {
            disposed.dispose();
        }
    }

    @Override
    public void requestdata() {

    }
}
