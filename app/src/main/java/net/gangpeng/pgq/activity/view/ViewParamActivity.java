package net.gangpeng.pgq.activity.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.view.TextEditLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.view.ViewParamActivity
 * function: View参数相关
 * <p/>
 * created at 16/10/8 19:44
 *
 * @author gangpeng
 */
public class ViewParamActivity extends BaseActivity {
    /**
     * top，left，right，bottom，x，y
     * <p/>
     * x = left + translationX
     * y = top + translationY
     */
    @InjectView(R.id.te_left)
    TextEditLayout teLeft;
    @InjectView(R.id.te_top)
    TextEditLayout teTop;
    @InjectView(R.id.te_right)
    TextEditLayout teRight;
    @InjectView(R.id.te_bottom)
    TextEditLayout teBottom;
    @InjectView(R.id.te_x)
    TextEditLayout teX;
    @InjectView(R.id.te_y)
    TextEditLayout teY;

    @InjectView(R.id.tv_param1)
    TextView tvParam1;
    @InjectView(R.id.tv_param2)
    TextView tvParam2;

    @InjectView(R.id.ll_whole)
    LinearLayout llWhole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_param);
        ButterKnife.inject(this);

        RelativeLayout rl = new RelativeLayout(this);
        rl.setBackgroundColor(Color.parseColor("#888888"));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100,  100);
        rl.setLayoutParams(lp);
        llWhole.addView(rl);
    }

    @OnClick({R.id.tv_param1, R.id.tv_param2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_param1:
            case R.id.tv_param2:
                teLeft.setEditText("" + view.getLeft());
                teRight.setEditText("" + view.getRight());
                teTop.setEditText("" + view.getTop());
                teBottom.setEditText("" + view.getBottom());
                teX.setEditText("" + view.getX());
                teY.setEditText("" + view.getY());
                break;
            default:
                break;
        }
    }
}
