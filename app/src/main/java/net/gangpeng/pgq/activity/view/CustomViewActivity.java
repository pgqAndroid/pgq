package net.gangpeng.pgq.activity.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.view.CircleView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.view.CustomViewActivity
 * function: 自定义控件
 * <p/>
 * created at 17/2/23 09:01
 *
 * @author gangpeng
 */
public class CustomViewActivity extends BaseActivity implements View.OnTouchListener {
    @InjectView(R.id.cv_touch)
    CircleView cvTouch;

    private Button mFloatingButton;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.inject(this);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    @OnClick(R.id.cv_touch)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_touch:
                mFloatingButton = new Button(this);
                mFloatingButton.setText("click me");
                mLayoutParams = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
                        PixelFormat.TRANSPARENT);
                mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
                mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                mLayoutParams.x = 100;
                mLayoutParams.y = 300;
                mFloatingButton.setOnTouchListener(this);
                mWindowManager.addView(mFloatingButton, mLayoutParams);
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mLayoutParams.x = rawX - 100;
                mLayoutParams.y = rawY - 300;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeView(mFloatingButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
