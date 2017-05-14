package net.gangpeng.pgq.activity.html;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gangpeng.pgframe.base.BaseActivity;

import java.util.Calendar;

/**
 * className: net.gangpeng.pgq.activity.html.HtmlMuiActivity
 * function: mui框架
 * <p>
 * created at 16/11/21 10:26
 *
 * @author gangpeng
 */
public class HtmlMuiActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        setContentView(webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/mui/html/nav_transparent.html");

    }
}
