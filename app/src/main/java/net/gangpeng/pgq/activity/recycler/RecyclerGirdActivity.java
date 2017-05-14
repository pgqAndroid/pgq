package net.gangpeng.pgq.activity.recycler;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.adapter.RecyclerGirdAdapter;
import net.gangpeng.pgq.view.DividerGirdItem;

import java.util.ArrayList;
import java.util.List;

/**
 * className: net.gangpeng.pgq.activity.recycler.RecyclerGirdActivity
 * function: recyclerView仿GirdActivity
 * <p/>
 * created at 16/10/25 15:45
 *
 * @author gangpeng
 */
public class RecyclerGirdActivity extends BaseActivity {
    /**
     * 控件
     */
    private RecyclerView rv_test;
    private RecyclerView rv_test2;
    private RecyclerView rv_test3;
    private TextView tvAdd;
    private TextView tvDelete;

    /**
     * 数据源
     */
    private List<String> dataList;
    /**
     * 适配器
     */
    private RecyclerGirdAdapter recyclerGirdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_gird);

        //初始化控件
        rv_test = (RecyclerView) findViewById(R.id.rv_view);
        rv_test2 = (RecyclerView) findViewById(R.id.rv_view2);
        rv_test3 = (RecyclerView) findViewById(R.id.rv_view3);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvDelete = (TextView) findViewById(R.id.tv_delete);
        //初始化数据
        initData();
        //初始化recycle view
        initRecyclerView();
        //设置点击事件
        setOnClickListener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            dataList.add("1" + (char) i);
            dataList.add("2" + (char) i);
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        recyclerGirdAdapter = new RecyclerGirdAdapter(this, dataList, R.layout.item_recycler);

        rv_test.setLayoutManager(new GridLayoutManager(this, 3));
        rv_test.setAdapter(recyclerGirdAdapter);
        rv_test.addItemDecoration(new DividerGirdItem(this));

        rv_test2.setLayoutManager(new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL));
        rv_test2.setAdapter(recyclerGirdAdapter);
        rv_test2.addItemDecoration(new DividerGirdItem(this));
        rv_test2.setItemAnimator(new DefaultItemAnimator());

        rv_test3.setLayoutManager(new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.HORIZONTAL));
        rv_test3.setAdapter(recyclerGirdAdapter);
        rv_test3.addItemDecoration(new DividerGirdItem(this));
    }

    private void setOnClickListener() {
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerGirdAdapter.addData(1);
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerGirdAdapter.removeData(1);
            }
        });
    }
}
