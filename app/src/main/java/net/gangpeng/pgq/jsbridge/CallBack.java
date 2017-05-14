package net.gangpeng.pgq.jsbridge;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * className: net.gangpeng.pgq.jsbridge.CallBack
 * function: jsBridge回调函数
 * <p>
 * created at 16/11/9 11:18
 *
 * @author gangpeng
 */
public class CallBack {
    /**
     * 主线程的handler
     */
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 定义 调用js中的某个方法，以及参数
     */
    private static final String CALLBACK_JS_FORMAT = "javascript:JSBridge.onFinish('%s', %s);";
    private String mPort;
    /**
     * 防止内存泄露，使用弱引用
     */
    private WeakReference<WebView> mWebViewRef;

    public CallBack(WebView view, String port) {
        mWebViewRef = new WeakReference<>(view);
        mPort = port;
    }

    /**
     * 调用js方法
     *
     * @param jsonObject 数据对象
     */
    public void apply(JSONObject jsonObject) {
        final String execJs = String.format(CALLBACK_JS_FORMAT, mPort, String.valueOf(jsonObject));
        if (mWebViewRef != null && mWebViewRef.get() != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mWebViewRef.get().loadUrl(execJs);
                }
            });

        }
    }
}
