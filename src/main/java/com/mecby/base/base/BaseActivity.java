package com.mecby.base.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.mecby.base.utils.ActivityUtil;
import com.mecby.base.utils.ToastUtils;
import com.mecby.base.utils.message.BindEventBus;
import com.mecby.base.utils.message.Event;
import com.mecby.base.utils.message.EventBusUtil;
import com.zhouyou.http.subsciber.IProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by jerry on 2017/8/8 0008.
 * Activity的基类,沉浸式状态栏,吐司,eventbus,handler,ProgressDialog创建,自动取消网络加载
 * 要得到P层使用getPresenter
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    private Handler mHandler;
    private IProgressDialog mProgressDialog;
    private T mBasePresent;
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.setScreenVertical(this);
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtil.register(this);
        }
        mImmersionBar = ImmersionBar.with(this);
        //regiest();检测网络状况并弹框的
    }

    protected T getPresenter() {
        if (null == mBasePresent) {
            mBasePresent = createPresenter();
        }
        return mBasePresent;
    }

    protected abstract T createPresenter();
/*    private IntentFilter mIntentFilter;
    private NetworkChangeReciver network;
    public void  regiest(){
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        network=new NetworkChangeReciver();
        registerReceiver(network, mIntentFilter);
    }
    class NetworkChangeReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub'
            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable())
                System.out.println("当前网络正常！"+this.getClass().getSimpleName());
            else
                showError();
        }
    }
    private void showError() {
        if(getClass().equals(BaseApplication.getInstance().getCurrentActivity().getClass())){
            DialogUtil.showConfirmDialog(BaseApplication.getInstance().getCurrentActivity(), "网络断了", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            });
        }

    }*/

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

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
    public void showToast(String msg) {
        ToastUtils.show(getApplicationContext(), msg);
    }

    @Override
    public void loginAgain() {
        
        CacheManager.getInstance().clearUserInfoData();
        ARouter.getInstance().build(AppConstant.LOGIN_ACTIVITY).withBoolean("loginAgain", true).greenChannel().navigation();
        showToast("请重新登录");
        //finish();
    }

    @Override
    public void finishActivity() {
        finish();
    }


    @Override
    public IProgressDialog getProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new IProgressDialog() {
                @Override
                public Dialog getDialog() {
                    /*MaterialDialog loadingDailog = DialogUtil.createLoadingDailog(BaseActivity.this, "Loading...");
                    return loadingDailog;*/
                    ProgressDialog dialog = new ProgressDialog(BaseActivity.this);
                    dialog.setMessage("加载中...");

                    return dialog;
                }
            };
        }
        return mProgressDialog;
    }

    @Override
    protected void onDestroy() {
        //unregisterReceiver(network);
        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtil.unregister(this);
        }
        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        //Dialog消失的时候请求自动取消,如果没有dialog则手动拦截请求
        if (null != mProgressDialog && mProgressDialog.getDialog().isShowing()) {
            mProgressDialog.getDialog().cancel();
            mProgressDialog = null;
        }
        if (mBasePresent != null)
            mBasePresent.cancelRequest();
        super.onDestroy();
    }

}
