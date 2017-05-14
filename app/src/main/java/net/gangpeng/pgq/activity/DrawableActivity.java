package net.gangpeng.pgq.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.DrawableActivity
 * function: 自定义图片
 * <p/>
 * created at 17/2/24 14:33
 *
 * @author gangpeng
 */
public class DrawableActivity extends BaseActivity {
    @InjectView(R.id.iv_level)
    ImageView ivLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.iv_level)
    public void onClick(View view) {
        switch (view.getId()) {
            //src可以，background不行
            case R.id.iv_level:
                ivLevel.setImageLevel(15);
                break;
        }
    }
}
