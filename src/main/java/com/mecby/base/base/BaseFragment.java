package com.mecby.base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mecby.base.utils.message.BindEventBus;
import com.mecby.base.utils.message.Event;
import com.mecby.base.utils.message.EventBusUtil;
import com.zhouyou.http.subsciber.IProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by jerry on 2017/8/8 0008.
 */

public abstract class BaseFragment<T extends BasePresenter>  extends Fragment implements BaseView{

    private Handler mHandler;
    private IProgressDialog mProgressDialog ;
    private T mBasePresent;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtil.register(this);
        }
       /* if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            System.out.println(this.getClass().getSimpleName()+"===="+isSupportHidden);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }*/
    }
  /*  @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }*/


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

 /*   protected BaseActivity getMyactivity(){
        if(getActivity() instanceof BaseActivity){
            BaseActivity activity = (BaseActivity) mActivity;
            return activity;
        }
        return null;
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity=null;
    }
    public  Activity  getMyActivity(){
        return mActivity;
    }

    protected T getPresenter() {
        if (null == mBasePresent) {
            mBasePresent = createPresenter();
        }
        return mBasePresent;
    }


    protected abstract T createPresenter();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {
    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {
    }

    public Handler getHandler() {
        if (null == mHandler) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    @Override
    public IProgressDialog getProgressDialog() {
        if(getActivity() instanceof BaseActivity){
            BaseActivity activity = (BaseActivity) mActivity;
            mProgressDialog=activity.getProgressDialog();
            return mProgressDialog;
        }
        return null;
    }

    @Override
    public void showToast(String msg) {
        if(getActivity() instanceof BaseActivity){
            BaseActivity activity = (BaseActivity) mActivity;
            activity.showToast(msg);
        }
    }

    @Override
    public void loginAgain() {
        if(getActivity() instanceof BaseActivity){
            BaseActivity activity = (BaseActivity) mActivity;
            activity.loginAgain();
        }
    }

    @Override
    public void finishActivity() {
        if(getActivity() instanceof BaseActivity){
            BaseActivity activity = (BaseActivity) mActivity;
            activity.finishActivity();
        }
    }


    @Override
    public void onDestroyView() {
        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtil.unregister(this);
        }
        //ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
        //Dialog消失的时候请求自动取消,如果没有dialog则手动拦截请求
        if(null!=mProgressDialog&&mProgressDialog.getDialog().isShowing()){
            mProgressDialog.getDialog().cancel();
        }
        if(mBasePresent!=null)
            mBasePresent.cancelRequest();
        super.onDestroyView();

    }
}
