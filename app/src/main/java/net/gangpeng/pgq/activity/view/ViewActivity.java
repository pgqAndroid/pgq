package net.gangpeng.pgq.activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.view.ViewActivity
 * function: 测试关于view的一些方法
 * <p>
 * created at 16/10/8 17:26
 *
 * @author gangpeng
 */
public class ViewActivity extends BaseActivity {
    /**
     * view相关参数，方法
     */
    @InjectView(R.id.tv_param)
    TextView tvParam;
    /**
     * touch相关
     */
    @InjectView(R.id.tv_touch)
    TextView tvTouch;
    /**
     * scroll相关
     */
    @InjectView(R.id.tv_scroll)
    TextView tvScroll;
    /**
     * 滑动冲突
     */
    @InjectView(R.id.tv_merge)
    TextView tvMerge;
    /**
     * 定义控件
     */
    @InjectView(R.id.tv_custom_view)
    TextView tvCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //获取当前view对象
        View view = ((ViewGroup) (getWindow().getDecorView().findViewById(android.R.id.content))).getChildAt(0);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_param, R.id.tv_touch, R.id.tv_scroll, R.id.tv_merge, R.id.tv_custom_view})
    public void onClick(View view) {
        switch (view.getId()) {
            //view参数
            case R.id.tv_param:
                Intent intent = new Intent(ViewActivity.this, ViewParamActivity.class);
                startActivity(intent);
                break;
            //view touch相关
            case R.id.tv_touch:
                intent = new Intent(ViewActivity.this, ViewTouchActivity.class);
                startActivity(intent);
                break;
            //view scroll相关
            case R.id.tv_scroll:
                intent = new Intent(ViewActivity.this, ViewScrollActivity.class);
                startActivity(intent);
                break;
            //滑动冲突
            case R.id.tv_merge:
                intent = new Intent(ViewActivity.this, ViewMergeActivity.class);
                startActivity(intent);
                break;
            //自动以控件
            case R.id.tv_custom_view:
                intent = new Intent(this, CustomViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
