package net.gangpeng.pgq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.frame.ActivityTask;
import com.google.gson.Gson;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.activity.eventbus.EventBusActivity;
import net.gangpeng.pgq.activity.green.GreenIndexActivity;
import net.gangpeng.pgq.activity.html.HtmlIndexActivity;
import net.gangpeng.pgq.activity.map.MapIndexActivity;
import net.gangpeng.pgq.activity.recycler.RecyclerViewIndexActivity;
import net.gangpeng.pgq.activity.view.ViewActivity;
import net.gangpeng.pgq.domain.ShareContentBean;
import net.gangpeng.pgq.util.SysCode;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: HomeActivity
 * function: 主activity
 * <p>
 * created at
 *
 * @author pg
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 状态栏相关
     */
    @InjectView(R.id.tv_state_bar)
    TextView tvStateBar;
    /**
     * 分享按钮
     */
    @InjectView(R.id.tv_share)
    TextView tvShare;
    /**
     * 地图按钮
     */
    @InjectView(R.id.tv_map)
    TextView tvMap;
    /**
     * recycler view
     */
    @InjectView(R.id.tv_recycler)
    TextView tvRecyclerView;
    /**
     * view相关测试
     */
    @InjectView(R.id.tv_view)
    TextView tvView;
    /**
     * html相关测试
     */
    @InjectView(R.id.tv_html)
    TextView tvHtml;
    /**
     * picasso测试
     */
    @InjectView(R.id.tv_picasso)
    TextView tvPicasso;
    /**
     * okHttp测试
     */
    @InjectView(R.id.tv_okhttp)
    TextView tvOkHttp;
    /**
     * greenDAo
     */
    @InjectView(R.id.tv_greenDao)
    TextView tvGreenDao;
    /**
     * eventBus
     */
    @InjectView(R.id.tv_eventBus)
    TextView tvEventBus;
    /**
     * drawable
     */
    @InjectView(R.id.tv_drawable)
    TextView tvDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //注解框架
        ButterKnife.inject(this);
    }

    /**
     * 返回按钮
     */
    @Override
    public void onBackPressed() {
        ActivityTask.getInstance().quit();
    }

    /**
     * 处理控件点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_share, R.id.tv_map, R.id.tv_recycler, R.id.tv_view, R.id.tv_state_bar, R.id.tv_html
            , R.id.tv_picasso, R.id.tv_okhttp, R.id.tv_greenDao, R.id.tv_eventBus, R.id.tv_drawable})
    public void onClick(View view) {
        switch (view.getId()) {
            //分享按钮
            case R.id.tv_share:
                Intent intent = new Intent(HomeActivity.this, ShareDialogActivity.class);
                //分享内容，可在shareContentBean内设置
                ShareContentBean shareContentBean = new ShareContentBean();
                shareContentBean.setTitle("sss");
                intent.putExtra(SysCode.INTENT_PARAM.DATA, new Gson().toJson(shareContentBean));
                startActivity(intent);
                break;
            //地图按钮
            case R.id.tv_map:
                intent = new Intent(HomeActivity.this, MapIndexActivity.class);
                startActivity(intent);
                break;
            //recycler view按钮
            case R.id.tv_recycler:
                intent = new Intent(HomeActivity.this, RecyclerViewIndexActivity.class);
                startActivity(intent);
                break;
            //view按钮
            case R.id.tv_view:
                intent = new Intent(HomeActivity.this, ViewActivity.class);
                startActivity(intent);
                break;
            //导航栏按钮
            case R.id.tv_state_bar:
                intent = new Intent(HomeActivity.this, StateBarActivity.class);
                startActivity(intent);
                break;
            //html
            case R.id.tv_html:
                intent = new Intent(HomeActivity.this, HtmlIndexActivity.class);
                startActivity(intent);
                break;
            //picasso
            case R.id.tv_picasso:
                intent = new Intent(HomeActivity.this, PicassoActivity.class);
                startActivity(intent);
                break;
            //picasso
            case R.id.tv_okhttp:
                intent = new Intent(HomeActivity.this, OkHttpActivity.class);
                startActivity(intent);
                break;
            //greenDao
            case R.id.tv_greenDao:
                intent = new Intent(HomeActivity.this, GreenIndexActivity.class);
                startActivity(intent);
                break;
            //eventBus
            case R.id.tv_eventBus:
                intent = new Intent(HomeActivity.this, EventBusActivity.class);
                startActivity(intent);
                break;
            //drawable
            case R.id.tv_drawable:
                intent = new Intent(HomeActivity.this, DrawableActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
