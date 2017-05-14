package net.gangpeng.pgq.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * className: net.gangpeng.pgq.view.ScrollView
 * function: 测试滚动相关
 * <p>
 * created at 16/10/14 11:26
 *
 * @author gangpeng
 */
public class ScrollTextView extends TextView {

    private Scroller scroller;

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void smoothScrollTo(int destX, int dextY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int deltaX = destX - scrollX;
        int deltaY = dextY - scrollY;
        scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 2000);
        invalidate();
    }
}
