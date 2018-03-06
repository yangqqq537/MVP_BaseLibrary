package com.mecby.base.base;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mecby.base.R;
import com.mecby.base.utils.AppUtils;
import com.mecby.base.views.TitleView;

/**
 * Created by jerry on 2017/9/26 0026.
 */

public abstract class BaseTitleActivity<T extends BasePresenter>  extends BaseActivity<T> {

    protected TitleView mTitleview;
    protected ViewGroup mRootTitleView;
    //private View line;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        /** 初始化titleView */
        mRootTitleView = (ViewGroup) View.inflate(this, R.layout.activity_base, null);
        mTitleview = (TitleView)mRootTitleView.findViewById(R.id.base_titleview);
        mTitleview.setBackgroundColor(AppUtils.getColor(R.color.yellow_bg));
        mTitleview.setLeftIv(R.drawable.btn_topbar_back);
        mTitleview.setLeftViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTitleview.setElevation(AppUtils.dp2px(3));
        }*/
        //line=mRootTitleView.findViewById(R.id.base_line);
        /** 初始化内容区域 */
        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.base_titleview);
        mRootTitleView.addView(view, 0, lp);
        super.setContentView(mRootTitleView);
        mImmersionBar.titleBar(mTitleview).init();
        //initContainerView();
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
    public void hideLeftIv(){
        mTitleview.hideLeftIv();
    }
    public void setMidIv(int res){
        mTitleview.setMidIv(res);
    }
    public void setMidTv(String str){
        mTitleview.setMidTv(str);
    }
    public void setRightIv(int res){
        mTitleview.setRightIv(res);
    }
    public void setRightTv(String str){
        mTitleview.setRightTv(str);
    }
    public void setRightViewClickListener(View.OnClickListener listener){
        mTitleview.setRightViewClickListener(listener);
    }
    public void setLeftViewClickListener(View.OnClickListener listener){
        mTitleview.setLeftViewClickListener(listener);
    }

    /*protected void setLineVisible(){
        line.setVisibility(View.VISIBLE);
    }*/


    public void setMiddleTitleColor(int color){
        mTitleview.getMidTv().setTextColor(color);
    }

}
