package net.gangpeng.pgq.activity.view;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.L;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.view.TextEditLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * className: net.gangpeng.pgq.activity.view.ViewTouchActivity
 * function: view的touch相关
 * <p>
 * created at 16/10/13 10:33
 *
 * @author gangpeng
 */
public class ViewTouchActivity extends BaseActivity {

    @InjectView(R.id.te_sts)
    TextEditLayout teSts;
    @InjectView(R.id.te_action)
    TextEditLayout teAction;
    @InjectView(R.id.te_x)
    TextEditLayout teX;
    @InjectView(R.id.te_y)
    TextEditLayout teY;
    @InjectView(R.id.te_rawX)
    TextEditLayout teRawX;
    @InjectView(R.id.te_rawY)
    TextEditLayout teRawY;
    @InjectView(R.id.te_velocityX)
    TextEditLayout teVelocityX;
    @InjectView(R.id.te_velocityY)
    TextEditLayout teVelocityY;
    @InjectView(R.id.v_test_area1)
    RelativeLayout vTestArea1;
    @InjectView(R.id.v_test_area2)
    TextView vTestArea2;

    /**
     * 用于计算滑动速度
     */
    VelocityTracker velocityTracker;
    /**
     * 手势监听，用于判断单击，双击，滑动，等点击事件
     */
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_touch);
        ButterKnife.inject(this);
        //初始化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //手势监听
        setGestureDetector();
        //设置TouchSlop
        teSts.setEditText("" + ViewConfiguration.get(this).getScaledTouchSlop());
        //监听区域触摸事件
        vTestArea1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        L.i("OnTouchListener action down");
                        teAction.setEditText("down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        L.i("OnTouchListener action move");
                        teAction.setEditText("move");
                        break;
                    case MotionEvent.ACTION_UP:
                        L.i("OnTouchListener action up");
                        teAction.setEditText("up");
                        break;
                }
                //计算速度
                velocityTracker = VelocityTracker.obtain();
                velocityTracker.addMovement(motionEvent);
                velocityTracker.computeCurrentVelocity(1000);
                teVelocityX.setEditText(velocityTracker.getXVelocity());
                teVelocityY.setEditText(velocityTracker.getYVelocity());
                velocityTracker.clear();
                velocityTracker.recycle();

                teX.setEditText(motionEvent.getX());
                teY.setEditText(motionEvent.getY());
                teRawX.setEditText(motionEvent.getRawX());
                teRawY.setEditText(motionEvent.getRawY());
                //down返回true，才会触发相应的 move，up
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        //监听区域触摸事件
        vTestArea2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //计算速度
                velocityTracker = VelocityTracker.obtain();
                velocityTracker.addMovement(motionEvent);
                velocityTracker.computeCurrentVelocity(1000);
                teVelocityX.setEditText(velocityTracker.getXVelocity());
                teVelocityY.setEditText(velocityTracker.getYVelocity());
                velocityTracker.clear();
                velocityTracker.recycle();

                teX.setEditText(motionEvent.getX());
                teY.setEditText(motionEvent.getY());
                teRawX.setEditText(motionEvent.getRawX());
                teRawY.setEditText(motionEvent.getRawY());
                return false;
            }
        });
    }

    /**
     * 设置手势监听
     */
    private void setGestureDetector() {
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            /**
             * 由一次 ACTION_DOWN 触发。
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                L.i("GestureDetector OnGestureListener onDown()");
                return true;
            }

            /**
             * 由一次 ACTION_DOWN 触发。强调没有松开或者拖动
             * @param motionEvent
             */
            @Override
            public void onShowPress(MotionEvent motionEvent) {
                L.i("GestureDetector OnGestureListener onShowPress()");
            }

            /**
             * 由一次 ACTION_UP 触发。 奇数次点击触发
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                L.i("GestureDetector OnGestureListener onSingleTapUp()");
                return false;
            }

            /**
             * 由一次 ACTION_UP 和 多次 ACTION_MOVE 触发
             * @param motionEvent
             * @param motionEvent1
             * @param v
             * @param v1
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                L.i("GestureDetector OnGestureListener onScroll()");
                return false;
            }

            /**
             * 长按触发
             * @param motionEvent
             */
            @Override
            public void onLongPress(MotionEvent motionEvent) {
                L.i("GestureDetector OnGestureListener onLongPress()");
            }

            /**
             * 由一次 ACTION_UP 和 多次 ACTION_MOVE 和 一个 ACTION_UP触发
             * @param motionEvent
             * @param motionEvent1
             * @param v
             * @param v1
             * @return
             */
            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                L.i("GestureDetector OnGestureListener onFling()");
                return false;
            }
        });
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            /**
             * 严格点击事件 和 onDoubleTap不会同时触发。奇数次的最后一次触发
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                L.i("GestureDetector OnDoubleTapListener onSingleTapConfirmed()");
                return false;
            }

            /**
             * 连续两次点击触发。偶数次触发
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onDoubleTap(MotionEvent motionEvent) {
                L.i("GestureDetector OnDoubleTapListener onDoubleTap()");
                return false;
            }

            /**
             * 双击期间，ACTION_UP 和 多次 ACTION_MOVE 和 一个 ACTION_UP都会触发此控件
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                L.i("GestureDetector OnDoubleTapListener onDoubleTapEvent()");
                return false;
            }
        });
    }
}
