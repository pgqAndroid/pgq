package net.gangpeng.pgq.activity.html;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.L;

import net.gangpeng.pgq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.html.HtmlJsActivity
 * function: 原生js交互
 * <p/>
 * created at 16/11/2 15:44
 *
 * @author gangpeng
 */
public class HtmlJsActivity extends BaseActivity {
    @InjectView(R.id.tv_str)
    TextView tvStr;
    @InjectView(R.id.wb_html)
    WebView wbHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_js);
        ButterKnife.inject(this);

        WebSettings webSettings = wbHtml.getSettings();
        webSettings.setDisplayZoomControls(false);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);

        wbHtml.addJavascriptInterface(new JavaScriptInterface(), "demo");
        wbHtml.setWebChromeClient(new MyWebChromeClient());

        wbHtml.loadUrl("file:///android_asset/page/html/hello.html");
    }


    class JavaScriptInterface {

        public JavaScriptInterface() {

        }

        @JavascriptInterface
        public void changeStr() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvStr.setText("ahhhh");
                }
            });
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            L.d(message);
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            result.confirm("ss");
            return true;
        }
    }

    @OnClick({R.id.tv_str})
    public void onClick(View view) {
        switch (view.getId()) {
            //原生js
            case R.id.tv_str:
                wbHtml.loadUrl("JavaScript:wave()");
                break;
        }
    }
}
