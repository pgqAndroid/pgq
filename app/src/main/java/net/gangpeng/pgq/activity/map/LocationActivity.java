package net.gangpeng.pgq.activity.map;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.L;
import com.gangpeng.pgframe.util.T;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.SysCode;
import net.gangpeng.pgq.view.TextEditLayout;

/**
 * className: LocationActivity
 * function: 获取定位信息
 * <p>
 * created at 16/7/22 09:09
 *
 * @author pg
 */
public class LocationActivity extends BaseActivity {

    /**
     * 控件
     */
    private TextEditLayout longtitudeTe;
    private TextEditLayout latitudeTe;
    private TextEditLayout addressTe;
    private TextEditLayout nameTe;
    private ImageView iconIv;
    private MapView locationMv;

    // 定位相关
    LocationClient mLocClient;
    private BaiduMap baiduMap;
    private GeoCoder geoCoder;
    private LatLng latLng;
    public MyLocationListener myListener = new MyLocationListener();
    // 是否首次定位
    boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationMv = (MapView) findViewById(R.id.location_mv);
        longtitudeTe = (TextEditLayout) findViewById(R.id.te_longitude);
        latitudeTe = (TextEditLayout) findViewById(R.id.te_latitude);
        iconIv = (ImageView) findViewById(R.id.iv_location_icon);
        addressTe = (TextEditLayout) findViewById(R.id.te_address);
        nameTe = (TextEditLayout) findViewById(R.id.te_name);

        initMap();
    }

    /**
     * 初始化定位信息
     */
    private void initMap() {
        baiduMap = locationMv.getMap();
//        baiduMap.setMyLocationEnabled(true);
//        baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
//                MyLocationConfiguration.LocationMode.NORMAL
//                , true, BitmapDescriptorFactory.fromResource(R.drawable.icon_location)));

        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                //初始化
                Animation translateAnimation = new TranslateAnimation(0f, 0f, 0f, -25f);
                //设置动画时间
                translateAnimation.setDuration(200);
                iconIv.startAnimation(translateAnimation);
                //获取定位信息
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(mapStatus.target));
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

            }
        });

        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        //用于经纬度与地址信息转换
        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    T.showToastNotRepeat(LocationActivity.this, "获取定位信息失败", SysCode.TOAST_TIME);
                    return;
                }

                latLng = reverseGeoCodeResult.getLocation();
                longtitudeTe.setEditText(String.valueOf(latLng.longitude));
                latitudeTe.setEditText(String.valueOf(latLng.latitude));
                addressTe.setEditText(reverseGeoCodeResult.getAddress());
                if (reverseGeoCodeResult.getPoiList() != null
                        && !reverseGeoCodeResult.getPoiList().isEmpty()) {
                    nameTe.setEditText(reverseGeoCodeResult.getPoiList().get(0).name);
                } else {
                    nameTe.setEditText("");
                }
                //reverseGeoCodeResult.getAddressDetail();可得到getAddress()的分级信息
                //reverseGeoCodeResult.getBusinessCircle();可得到附件的商业圈信息
            }
        });

    }

    public class MyLocationListener implements BDLocationListener {

        /**
         * 此方法会一直调用，如果不是特殊需要，可在第一次获取定位信息后，关闭mLocClient
         *
         * @param bdLocation
         */
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            L.d("MyLocationListener");
            if (bdLocation == null) {
                L.e("获取地图定位信息失败");
                return;
            }
//            //用户显示当前位置的信息
//            MyLocationData locationData = new MyLocationData.Builder()
//                    .accuracy(bdLocation.getRadius())
//                    .latitude(bdLocation.getLatitude())
//                    .longitude(bdLocation.getLongitude())
//                    .direction(bdLocation.getDirection())
//                    .build();
//            baiduMap.setMyLocationData(locationData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
    }

    @Override
    protected void onResume() {
        locationMv.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        locationMv.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocClient.stop();

        baiduMap.setMyLocationEnabled(false);
        locationMv.onDestroy();
        locationMv = null;
        geoCoder.destroy();

        super.onDestroy();
    }
}
