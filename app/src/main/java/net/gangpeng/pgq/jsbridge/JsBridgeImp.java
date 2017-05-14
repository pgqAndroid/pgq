package net.gangpeng.pgq.jsbridge;

import android.webkit.WebView;

import com.gangpeng.pgframe.util.T;

import net.gangpeng.pgq.util.SysCode;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * className: net.gangpeng.pgq.jsbridge
 * function:
 * <p>
 * created at 16/11/9 15:03
 *
 * @author gangpeng
 */
public class JsBridgeImp extends IJsBridge {

    public static void showToast(WebView webView, JSONObject jsonObject, CallBack callBack) {
        String msg = jsonObject.optString("msg");
        T.showToastNotRepeat(webView.getContext(), msg, SysCode.TOAST_TIME);
        if (null != callBack) {
            JSONObject params = new JSONObject();
            try {
                params.put("name", "kangkang");
                params.put("age", 12);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            callBack.apply(getJSONObject(0, "ok", params));
        }
    }

    public static void testThread(WebView webView, JSONObject param, final CallBack callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    JSONObject object = new JSONObject();
                    object.put("key", "value");
                    callback.apply(getJSONObject(0, "ok", object));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 统一返回参数
     *
     * @param code   状态码
     * @param msg    状态名称
     * @param result 数据
     * @return
     */
    private static JSONObject getJSONObject(int code, String msg, JSONObject result) {
        JSONObject object = new JSONObject();
        try {
            object.put("code", code);
            object.put("msg", msg);
            object.putOpt("result", result);
            return object;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
