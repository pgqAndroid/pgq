package net.gangpeng.pgq.jsbridge;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * className: net.gangpeng.pgq.jsbridge.PgWebChromeClient
 * function: 自定义ChromeClient，借助prompt完成js与Android通信
 * <p>
 * created at 16/11/9 10:48
 *
 * @author gangpeng
 */
public class PgWebChromeClient extends WebChromeClient {
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.confirm(JsBridge.callJava(view, message));
        return true;
    }
}
