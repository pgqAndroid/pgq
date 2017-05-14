package net.gangpeng.pgq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.RelativeLayout;

/**
 * className: net.gangpeng.pgq.view
 * function:
 * <p>
 * created at 16/10/22 17:06
 *
 * @author gangpeng
 */
public class ScrollRelative extends RelativeLayout implements AbsListView.OnScrollListener {

    private AbsListView.OnScrollListener mOnScrollListener;

    public ScrollRelative(Context context) {
        super(context);
    }

    public ScrollRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener) {
        this.mOnScrollListener = paramOnScrollListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScroll(absListView, i, i1, i2);
        }
    }
}
