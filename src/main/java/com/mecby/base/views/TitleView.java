package com.mecby.base.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mecby.base.R;


/**
 * Created by jerry on 2017/9/15 0015.
 * 标题栏
 */

public class TitleView extends FrameLayout {
    private ImageView mIvLeft;
    private ImageView mIvMid;
    private ImageView mIvRight;
    private TextView mTvMid;
    private TextView mTvRight;
    private TextView mTvLeft;
    private FrameLayout mRightFlcontainer;
    private FrameLayout mLeftFlContainer;
    private Context mContext;

    public TitleView(@NonNull Context context) {
        this(context,null);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView();
    }

    private void initView() {
        View view =View.inflate(mContext, R.layout.title_view,this);
        mIvLeft = (ImageView) view.findViewById(R.id.iv_left);
        mTvLeft= (TextView) view.findViewById(R.id.tv_left);
        mIvMid = (ImageView)view. findViewById(R.id.iv_mid);
        mIvRight = (ImageView) view.findViewById(R.id.iv_right);
        mTvMid = (TextView) view.findViewById(R.id.tv_mid);
        mTvRight = (TextView) view.findViewById(R.id.tv_right);
        mRightFlcontainer = (FrameLayout)view. findViewById(R.id.right_fl);
        mLeftFlContainer= (FrameLayout) view.findViewById(R.id.left_fl);
    }


    public void setLeftIv(int res){
        mTvLeft.setVisibility(GONE);
        mIvLeft.setVisibility(View.VISIBLE);
        mIvLeft.setImageResource(res);
    }

    public void setLeftTv(String str){
        mIvLeft.setVisibility(View.GONE);
        mTvLeft.setVisibility(View.VISIBLE);
        mTvLeft.setText(str);
    }

    public void hideLeftIv(){
        mIvLeft.setVisibility(View.GONE);
    }

    public void setMidIv(int res){
        mIvMid.setVisibility(View.VISIBLE);
        mTvMid.setVisibility(View.GONE);
        mIvMid.setImageResource(res);
    }
    public void setRightIv(int res){
        mIvRight.setVisibility(View.VISIBLE);
        mTvRight.setVisibility(View.GONE);
        mIvRight.setImageResource(res);
    }

    public void setMidTv(String str){
        mIvMid.setVisibility(View.GONE);
        mTvMid.setVisibility(View.VISIBLE);
        mTvMid.setText(str);
    }
    public void setRightTv(String str){
        mIvRight.setVisibility(View.GONE);
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(str);
    }

    public TextView getMidTv(){
        return mTvMid;
    }
    public TextView getTvRight(){
        return mTvRight;
    }

    public void setRightViewClickListener(OnClickListener listener){
        mRightFlcontainer.setOnClickListener(listener);
    }

    public void setLeftViewClickListener(OnClickListener listener){
        mLeftFlContainer.setOnClickListener(listener);
    }
}
