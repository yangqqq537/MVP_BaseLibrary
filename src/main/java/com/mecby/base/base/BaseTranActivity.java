package com.mecby.base.base;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mecby.base.R;
import com.mecby.base.views.TitleView;

/**
 * Created by jerry on 2017/9/26 0026.
 */

public abstract class BaseTranActivity <T extends BasePresenter>  extends BaseActivity<T> {
    private TitleView mTitleview;
    protected ViewGroup mRootTitleView;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        /** 初始化titleView */
        mRootTitleView = (ViewGroup) View.inflate(this, R.layout.activity_base, null);
        mTitleview = (TitleView)mRootTitleView.findViewById(R.id.base_titleview);
        /** 初始化内容区域 */
        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootTitleView.addView(view, 0, lp);
        super.setContentView(mRootTitleView);
        setStateBarNormal();
        //initContainerView();
    }

    public void setStateBarNormal(){
        mImmersionBar.titleBar(mTitleview).statusBarDarkFont(false).init();
    }

    public void setStateBarDark(){
        mImmersionBar.titleBar(mTitleview).statusBarDarkFont(true,0.3f).init();
    }

    public void setTitleBg(int res){
        mTitleview.setBackgroundResource(res);
    }
    public void setLeftIv(int res){
        mTitleview.setLeftIv(res);
    }
    public void setLeftTv(String string){
        mTitleview.setLeftTv(string);
    }
    public void hideLeftIv(){
        mTitleview.hideLeftIv();
    }
    public void setMidIv(int res){
        mTitleview.setMidIv(res);
    }
    public void setMidTv(String str){
        mTitleview.setMidTv(str);
    }
    public void setMidColor(int color){
        mTitleview.getMidTv().setTextColor(color);
    }
    public void setRightIv(int res){
        mTitleview.setRightIv(res);
    }
    public void setRightTv(String str){
        mTitleview.setRightTv(str);
    }
    public void setRightColor(int color){
        mTitleview.getTvRight().setTextColor(color);
    }
    public void setRightViewClickListener(View.OnClickListener listener){
        mTitleview.setRightViewClickListener(listener);
    }
    public void setLeftViewClickListener(View.OnClickListener listener){
        mTitleview.setLeftViewClickListener(listener);
    }
}
