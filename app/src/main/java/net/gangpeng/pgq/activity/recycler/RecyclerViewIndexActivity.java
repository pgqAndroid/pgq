package net.gangpeng.pgq.activity.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.recycler.RecyclerViewIndexActivity
 * function: recycleView功能列表页
 * <p/>
 * created at 16/10/25 15:39
 *
 * @author gangpeng
 */
public class RecyclerViewIndexActivity extends BaseActivity {
    @InjectView(R.id.tv_list)
    TextView tvList;
    @InjectView(R.id.tv_gird)
    TextView tvGird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_index);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_list, R.id.tv_gird})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_list:
                Intent intent = new Intent(RecyclerViewIndexActivity.this, RecyclerListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_gird:
                intent = new Intent(RecyclerViewIndexActivity.this, RecyclerGirdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
