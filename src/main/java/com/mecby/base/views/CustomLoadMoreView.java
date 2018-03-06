package com.mecby.base.views;


import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.mecby.base.R;

/**
* @author jerry
* @describe 加载更多
* @date  2017/11/10 0010 13:59
*/

public final class CustomLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
