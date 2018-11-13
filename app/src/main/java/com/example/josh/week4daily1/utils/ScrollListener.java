package com.example.josh.week4daily1.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class ScrollListener extends RecyclerView.OnScrollListener {
    LinearLayoutManager layoutManager;
    public static final String TAG = "_TAG";

    public ScrollListener(LinearLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (!isLoading()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }


    }

    protected abstract void loadMoreItems();
    public abstract boolean isLoading();



}
