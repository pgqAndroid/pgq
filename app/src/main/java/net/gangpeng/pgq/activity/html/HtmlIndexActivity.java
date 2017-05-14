package net.gangpeng.pgq.activity.html;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.jsbridge.JsBridgeActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.html.HtmlIndexActivity
 * function: activity加载html目录
 * <p>
 * created at 16/11/2 15:37
 *
 * @author gangpeng
 */
public class HtmlIndexActivity extends BaseActivity {

    @InjectView(R.id.tv_js)
    TextView tvJs;
    @InjectView(R.id.tv_js_bridge)
    TextView tvJsBridge;
    @InjectView(R.id.tv_mui)
    TextView tvMui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_index);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_js, R.id.tv_js_bridge, R.id.tv_mui})
    public void onClick(View view) {
        switch (view.getId()) {
            //原生js
            case R.id.tv_js:
                Intent intent = new Intent(HtmlIndexActivity.this, HtmlJsActivity.class);
                startActivity(intent);
                break;
            //jsBridge
            case R.id.tv_js_bridge:
                intent = new Intent(HtmlIndexActivity.this, JsBridgeActivity.class);
                startActivity(intent);
                break;
            //MUI
            case R.id.tv_mui:
                intent = new Intent(HtmlIndexActivity.this, HtmlMuiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
