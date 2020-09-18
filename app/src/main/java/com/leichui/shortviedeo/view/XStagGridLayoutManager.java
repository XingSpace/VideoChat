package com.leichui.shortviedeo.view;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

public class XStagGridLayoutManager extends StaggeredGridLayoutManager {
    public XStagGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public XStagGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    /**
     * 垂直方向
     * @return
     */
    @Override
    public boolean canScrollVertically() {
        return false;
    }

    /**
     * 水平方向
     * @return
     */
    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
