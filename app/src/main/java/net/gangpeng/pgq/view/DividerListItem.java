package net.gangpeng.pgq.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * className: net.gangpeng.pgq.view.DividerListItem
 * function: 用于RecyclerView分割线
 * <p/>
 * created at 16/10/25 11:24
 *
 * @author gangpeng
 */
public class DividerListItem extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    private Drawable mDivider;
    private int mOrientation;

    public DividerListItem(Context context, int orientation) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (VERTICAL_LIST != orientation && HORIZONTAL_LIST != orientation) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView view) {
        int left = view.getPaddingLeft();
        int right = view.getWidth() - view.getPaddingRight();

        int childCount = view.getChildCount();
        //绘制最上面的一条线
        if (childCount > 0) {
            mDivider.setBounds(left, view.getChildAt(0).getTop() - mDivider.getIntrinsicHeight()
                    , right, view.getChildAt(0).getTop());
            mDivider.draw(c);
        }
        for (int i = 0; i < childCount; i++) {
            View childView = view.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int top = childView.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView view) {
        int top = view.getPaddingTop();
        int bottom = view.getHeight() - view.getPaddingBottom();

        int childCount = view.getChildCount();
        //绘制最上面的一条线
        if (childCount > 0) {
            mDivider.setBounds(view.getChildAt(0).getLeft() - mDivider.getIntrinsicHeight(), top
                    , view.getChildAt(0).getLeft(), bottom);
            mDivider.draw(c);
        }
        for (int i = 0; i < childCount; i++) {
            View childView = view.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicHeight(), 0);
        }
    }
}
