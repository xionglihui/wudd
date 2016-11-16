package com.xiong.dandan.wudd.common.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wangyy on 2015/11/6.
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private OnLoadMoreListener mListener;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setListener(OnLoadMoreListener mListener) {
        this.mListener = mListener;
    }

    private void init() {
        this.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0 && mListener != null) {
                    mListener.loadMore();
                }
            }
        });
    }

    public interface OnLoadMoreListener {
        void loadMore();
    }
}
