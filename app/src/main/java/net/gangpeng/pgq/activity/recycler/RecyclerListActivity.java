package net.gangpeng.pgq.activity.recycler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.adapter.RecyclerListAdapter;
import net.gangpeng.pgq.view.DividerListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * className: RecyclerListActivity
 * function: RecyclerView测试
 * <p/>
 * created at 16/9/22 14:19
 *
 * @author pg
 */
public class RecyclerListActivity extends BaseActivity {

    /**
     * 控件
     */
    private RecyclerView rv_test;
    private RecyclerView rv_test2;

    /**
     * 数据源
     */
    private List<String> dataList;
    /**
     * 适配器
     */
    private RecyclerListAdapter recyclerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list);

        //初始化控件
        rv_test = (RecyclerView) findViewById(R.id.rv_view);
        rv_test2 = (RecyclerView) findViewById(R.id.rv_view2);
        //初始化数据
        initData();
        //初始化recycle view
        initRecyclerView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            dataList.add("" + (char) i);
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        recyclerListAdapter = new RecyclerListAdapter(this, dataList);
        //水平排序
        rv_test.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_test.setAdapter(recyclerListAdapter);
        rv_test.addItemDecoration(new DividerListItem(this, DividerListItem.HORIZONTAL_LIST));
        //垂直排序
        rv_test2.setLayoutManager(new LinearLayoutManager(this));
        rv_test2.setAdapter(recyclerListAdapter);
        rv_test2.addItemDecoration(new DividerListItem(this, DividerListItem.VERTICAL_LIST));

    }

}