package net.gangpeng.pgq.activity.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;

/**
 * Created by gangpeng on 16/7/19.
 */
public class MapIndexActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 当前定位信息
     */
    private TextView locationTv;
    private TextView specailLocationTv;
    private TextView searchLocationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_index);
        locationTv = (TextView) findViewById(R.id.tv_local_location);
        specailLocationTv = (TextView) findViewById(R.id.tv_special_location);
        searchLocationTv = (TextView) findViewById(R.id.tv_search_location);

        locationTv.setOnClickListener(this);
        specailLocationTv.setOnClickListener(this);
        searchLocationTv.setOnClickListener(this);
    }

    /**
     * 处理所有的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //获取当前定位信息
            case R.id.tv_local_location:
                Intent intent = new Intent(MapIndexActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            //定位特定经纬度信息
            case R.id.tv_special_location:
                intent = new Intent(MapIndexActivity.this, SpecialLocationActivity.class);
                startActivity(intent);
                break;
            //搜索特定区域
            case R.id.tv_search_location:
                intent = new Intent(MapIndexActivity.this, SearchLocationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
