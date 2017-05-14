package net.gangpeng.pgq.activity.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.HorizontalScrollView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * className: net.gangpeng.pgq.activity.view
 * function: 滑动冲突测试
 * <p/>
 * created at 17/2/21 16:21
 *
 * @author gangpeng
 */
public class ViewMergeActivity extends BaseActivity {

    @InjectView(R.id.lv_temp1)
    ListView lvTemp1;
    @InjectView(R.id.lv_temp2)
    ListView lvTemp2;
    @InjectView(R.id.hs_layout)
    HorizontalScrollView hsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_merge);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("name" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_auto_complete
                , R.id.tv_auto_complete, data);
        lvTemp1.setAdapter(adapter);
        lvTemp2.setAdapter(adapter);
    }
}
