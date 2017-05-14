package net.gangpeng.pgq.activity.view;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.T;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.SysCode;
import net.gangpeng.pgq.view.ScrollTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.view.ViewScrollActivity
 * function: view scroll相关
 * <p>
 * created at 16/10/13 17:12
 *
 * @author gangpeng
 */
public class ViewScrollActivity extends BaseActivity {
    @InjectView(R.id.tv_scroll)
    TextView tvScroll;

    @InjectView(R.id.v_test_area2)
    ScrollTextView vTestArea2;
    @InjectView(R.id.tv_anim)
    TextView tvAnim;
    @InjectView(R.id.tv_params)
    TextView tvParams;
    @InjectView(R.id.tv_anim_scroll)
    TextView tvAnimScroll;
    @InjectView(R.id.tv_handler)
    TextView tvHandler;
    @InjectView(R.id.tv_ObjectAnimation)
    TextView tvObjectAnimation;
    @InjectView(R.id.tv_ValueAnimation)
    TextView tvValueAnimation;
    @InjectView(R.id.tv_AnimationSet)
    TextView tvAnimationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scroll);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_scroll, R.id.v_test_area2, R.id.tv_anim})
    public void onClick(View view) {
        switch (view.getId()) {
            //开始滚动按钮
            case R.id.tv_scroll:
                //scroller滑动是view内容滑动,view本身不滑动
                vTestArea2.smoothScrollTo(100, 100);
                //通过动画滑动
                ObjectAnimator.ofFloat(tvAnim, "translationX", 0, 100).setDuration(1000).start();
                //通过布局滑动
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvParams.getLayoutParams();
                params.width += 100;
                params.leftMargin += 100;
                tvParams.requestLayout();
                //动画+scroller
                final int startX = 0;
                final int deltaX = 100;
                final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        tvAnimScroll.scrollTo(startX + (int) (valueAnimator.getAnimatedFraction()
                                * (deltaX)), 0);
                    }
                });
                animator.start();
                //通过handler实现滑动
                mCount = 0;
                handler.sendEmptyMessage(1);
                //ObjectAnimation
                ObjectAnimator.ofFloat(tvObjectAnimation, "translationY", -tvObjectAnimation.getHeight()).start();
                //ValueAnimation
                ValueAnimator colorAnimation = ObjectAnimator.ofInt(tvValueAnimation, "backgroundColor"
                        , 0xffff8080, 0xff8080ff);
                colorAnimation.setDuration(3000);
                colorAnimation.setEvaluator(new ArgbEvaluator());
                colorAnimation.setRepeatCount(ValueAnimator.INFINITE);
                colorAnimation.setRepeatMode(ValueAnimator.REVERSE);
                colorAnimation.start();
                //AnimationSet
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        ObjectAnimator.ofFloat(tvAnimationSet, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(tvAnimationSet, "rotationY", 0, 180),
                        ObjectAnimator.ofFloat(tvAnimationSet, "rotation", 0, -90),
                        ObjectAnimator.ofFloat(tvAnimationSet, "scaleX", 1, 1.5f),
                        ObjectAnimator.ofFloat(tvAnimationSet, "scaleY", 1, 0.5f),
                        ObjectAnimator.ofFloat(tvAnimationSet, "alpha", 1, 0.25f, 1)
                );
                animatorSet.setDuration(5000).start();
                break;
            case R.id.v_test_area2:
                T.showToastNotRepeat(ViewScrollActivity.this, "点击scroller", SysCode.TOAST_TIME);
                break;
            case R.id.tv_anim:
                T.showToastNotRepeat(ViewScrollActivity.this, "点击动画", SysCode.TOAST_TIME);
                break;
        }
    }


    private static int mCount = 0;
    private static int FRAME_COUNT = 30;
    private static int DELAYED_TIME = 30;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        int scrollX = (int) ((mCount * 1.0 / FRAME_COUNT) * 100);
                        tvHandler.scrollTo(scrollX, 0);
                        handler.sendEmptyMessageDelayed(1, DELAYED_TIME);
                    }
                    break;
                default:
                    break;
            }
        }
    };

}
