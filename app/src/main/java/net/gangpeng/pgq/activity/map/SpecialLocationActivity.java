package net.gangpeng.pgq.activity.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.DensityUtil;
import com.gangpeng.pgframe.util.L;
import com.gangpeng.pgframe.util.StringUtil;
import com.gangpeng.pgframe.util.T;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.SysCode;
import net.gangpeng.pgq.view.TextEditLayout;

/**
 * Created by gangpeng on 16/7/19.
 */
public class SpecialLocationActivity extends BaseActivity implements View.OnClickListener {

    private TextEditLayout longitudeTe;
    private TextEditLayout latitudeTe;
    private TextEditLayout addressTe;
    private Button button;
    private MapView mapView;

    private BaiduMap baiduMap;
    private LatLng latLng;
    private MarkerOptions markerOptions;

    private float longitude;
    private float latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_location);

        latitudeTe = (TextEditLayout) findViewById(R.id.te_latitude);
        longitudeTe = (TextEditLayout) findViewById(R.id.te_longitude);
        addressTe = (TextEditLayout) findViewById(R.id.te_address);
        button = (Button) findViewById(R.id.btn_query);
        mapView = (MapView) findViewById(R.id.mv_map);

        button.setOnClickListener(this);

        mapView.showZoomControls(false);
        baiduMap = mapView.getMap();


    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                if (isCorrect()) {
                    latLng = new LatLng(latitude, longitude);
                    markerOptions = new MarkerOptions().position(latLng)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location));
                    baiduMap.addOverlay(markerOptions);
                    baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(latLng, 18.0f));
                    showAddress();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 显示地址
     */
    private void showAddress() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_address_layout, null, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_address);
        tv.setText(addressTe.getEditText());

        InfoWindow infoWindow = new InfoWindow(view, latLng
                , DensityUtil.dp2px(SpecialLocationActivity.this, -30));
        baiduMap.showInfoWindow(infoWindow);
    }

    private boolean isCorrect() {
        if (StringUtil.isBlank(longitudeTe.getEditText())) {
            T.showToastNotRepeat(SpecialLocationActivity.this, "请输入经度", SysCode.TOAST_TIME);
            return false;
        }
        if (StringUtil.isBlank(latitudeTe.getEditText())) {
            T.showToastNotRepeat(SpecialLocationActivity.this, "请输入纬度", SysCode.TOAST_TIME);
            return false;
        }
        if (StringUtil.isBlank(addressTe.getEditText())) {
            T.showToastNotRepeat(SpecialLocationActivity.this, "请输入名称", SysCode.TOAST_TIME);
            return false;
        }

        try {
            longitude = Float.parseFloat(longitudeTe.getEditText());
            latitude = Float.parseFloat(latitudeTe.getEditText());
            if (longitude > 1000) {
                T.showToastNotRepeat(SpecialLocationActivity.this, "请输入正确的经度", SysCode.TOAST_TIME);
                return false;
            }
            if (latitude > 1000) {
                T.showToastNotRepeat(SpecialLocationActivity.this, "请输入正确的纬度", SysCode.TOAST_TIME);
                return false;
            }
        } catch (Exception e) {
            L.e("经纬度转换出错");
            T.showToastNotRepeat(SpecialLocationActivity.this, "请输入正确的纬度", SysCode.TOAST_TIME);
            return false;
        }

        return true;
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        mapView = null;
        super.onDestroy();
    }

}
