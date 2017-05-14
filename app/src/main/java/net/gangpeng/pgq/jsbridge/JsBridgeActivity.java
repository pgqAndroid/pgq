package net.gangpeng.pgq.jsbridge;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * className: net.gangpeng.pgq.jsbridge.JsBridgeActivity
 * function: JSBridge探究
 * <p>
 * created at 16/11/9 10:40
 *
 * @author gangpeng
 */
public class JsBridgeActivity extends BaseActivity {
    /**
     * WebView
     */
    @InjectView(R.id.wv_js_bridge)
    WebView wvJsBridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_bridge);
        ButterKnife.inject(this);

        WebSettings webSettings = wvJsBridge.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvJsBridge.setWebChromeClient(new PgWebChromeClient());
        wvJsBridge.loadUrl("file:///android_asset/page/html/js-bridge.html");
        JsBridge.register("bridge", JsBridgeImp.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wvJsBridge.destroy();
    }
}
