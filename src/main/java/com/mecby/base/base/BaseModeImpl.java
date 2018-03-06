package com.mecby.base.base;

/**
 * Created by jerry on 2017/9/21 0021.
 * 基类mode实现,
 * 要得到listener使用getListener
 */

public  class  BaseModeImpl<M extends BaseListener>  {
    protected M mBaseListener;
    public BaseModeImpl(M baseListener) {
        mBaseListener=baseListener;
    }

    public M getListener(){
        return mBaseListener;
    }

}
