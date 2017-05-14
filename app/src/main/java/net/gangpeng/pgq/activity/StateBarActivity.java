package net.gangpeng.pgq.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.StatusBarUtils;

/**
 * className: net.gangpeng.pgq.activity.StateBarActivity
 * function:状态栏
 * <p/>
 * created at 16/10/22 10:14
 *
 * @author gangpeng
 */
public class StateBarActivity extends BaseActivity implements AbsListView.OnScrollListener {

    private LinearLayout llTitle;
    private RelativeLayout rlData;

    private int mTopAlpha;
    private boolean mTopBgIsDefault = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_bar);
        llTitle = (LinearLayout) findViewById(R.id.ll_title);
        rlData = (RelativeLayout) findViewById(R.id.rl_data);
        StatusBarUtils.from(this)
                .setTransparentStatusbar(true)//状态栏是否透明
                .setTransparentNavigationbar(false)//Navigationbar是否透明
                .setActionbarView(llTitle)//view是否透明
                .setLightStatusBar(true)//状态栏字体是否为亮色
                .process();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    updateImageBgUI();
                    break;
            }
        }
    };

    /**
     * 更新图片背景
     */
    private void updateImageBgUI() {
        int[] location = new int[2];
        rlData.getLocationOnScreen(location);
        if (location[1] < 255) {
            mTopBgIsDefault = false;

            if (location[1] < 0) {
                if (mTopAlpha != 255) {
                    llTitle.setBackgroundColor(Color.argb(255, 66, 66, 66));
                    mTopAlpha = 255;
                }
            } else {
                mTopAlpha = 255 - location[1];
                llTitle.setBackgroundColor(Color.argb(mTopAlpha, 66, 66, 66));
            }
        } else {
            if (!mTopBgIsDefault) {
                mTopBgIsDefault = true;
                llTitle.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == SCROLL_STATE_IDLE) {
            mHandler.removeMessages(1);
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        mHandler.removeMessages(1);
        mHandler.sendEmptyMessage(1);
    }
}
