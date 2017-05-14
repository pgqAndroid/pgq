package net.gangpeng.pgq.activity.map;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.StringUtil;
import com.gangpeng.pgframe.util.T;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.PoiOverlay;
import net.gangpeng.pgq.util.SysCode;
import net.gangpeng.pgq.view.TextEditLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gangpeng on 16/7/22.
 */
public class SearchLocationActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 控件相关
     */
    private AutoCompleteTextView keywordActv;
    private TextEditLayout cityTe;
    private MapView mapView;
    private Button queryBtn;

    /**
     * 百度地图相关
     */
    private BaiduMap baiduMap;
    private GeoCoder geoCoder;
    private PoiSearch poiSearch;

    /**
     * 搜索建议相关
     */
    private SuggestionSearch suggestionSearch;
    private ArrayAdapter<String> sugAdapter = null;
    private List<String> suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        keywordActv = (AutoCompleteTextView) findViewById(R.id.actv_keyword);
        mapView = (MapView) findViewById(R.id.mv_map);
        queryBtn = (Button) findViewById(R.id.btn_query);
        cityTe = (TextEditLayout) findViewById(R.id.te_city);

        queryBtn.setOnClickListener(this);

        initMap();
    }

    /**
     * 初始化百度地图相关变量
     */
    private void initMap() {
        keywordActv.setThreshold(1);

        baiduMap = mapView.getMap();
        geoCoder = GeoCoder.newInstance();
        poiSearch = PoiSearch.newInstance();
        suggestionSearch = SuggestionSearch.newInstance();

        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        });

        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult result) {
                if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                    Toast.makeText(SearchLocationActivity.this, "未找到结果", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                    baiduMap.clear();
                    PoiOverlay overlay = new MyPoiOverlay(baiduMap);
                    baiduMap.setOnMarkerClickListener(overlay);
                    overlay.setData(result);
                    overlay.addToMap();
                    overlay.zoomToSpan();

                    return;
                }

                if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

                    // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
                    String strInfo = "在";
                    for (CityInfo cityInfo : result.getSuggestCityList()) {
                        strInfo += cityInfo.city;
                        strInfo += ",";
                    }
                    strInfo += "找到结果";
                    Toast.makeText(SearchLocationActivity.this, strInfo, Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult result) {
                if (result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(SearchLocationActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(SearchLocationActivity.this, result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });

        suggestionSearch.setOnGetSuggestionResultListener(new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult res) {
                if (res == null || res.getAllSuggestions() == null) {
                    return;
                }
                suggest = new ArrayList<>();
                for (SuggestionResult.SuggestionInfo suggestionInfo : res.getAllSuggestions()) {
                    if (StringUtil.isNotBlank(suggestionInfo.key)) {
                        suggest.add(suggestionInfo.key);
                    }
                }
                sugAdapter = new ArrayAdapter<String>(SearchLocationActivity.this, R.layout.item_auto_complete, R.id.tv_auto_complete, suggest);
                keywordActv.setAdapter(sugAdapter);
                sugAdapter.notifyDataSetChanged();
            }
        });

        keywordActv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    return;
                }
                suggestionSearch.requestSuggestion(new SuggestionSearchOption().location(new LatLng(31.74727848459027, 117.29431058870502))
                        .city(cityTe.getEditText()).keyword(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                if (isCorrect()) {
                    poiSearch.searchInCity(new PoiCitySearchOption().city(cityTe.getEditText())
                            .keyword(keywordActv.getText().toString())
                            .pageCapacity(20).pageNum(0));
                }
                break;
            default:
                break;
        }
    }

    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            poiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));
            // }
            return true;
        }
    }

    /**
     * 判断输入是否合法
     *
     * @return
     */
    private boolean isCorrect() {
        if (StringUtil.isBlank(cityTe.getEditText())) {
            T.showToastNotRepeat(SearchLocationActivity.this, "请输入城市", SysCode.TOAST_TIME);
            return false;
        }
        if (StringUtil.isBlank(keywordActv.getText().toString())) {
            T.showToastNotRepeat(SearchLocationActivity.this, "请输入关键字", SysCode.TOAST_TIME);
            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        suggestionSearch.destroy();
        poiSearch.destroy();
        super.onDestroy();
    }
}
