package net.gangpeng.pgq.jsbridge;

import android.net.Uri;
import android.webkit.WebView;

import com.gangpeng.pgframe.util.L;
import com.gangpeng.pgframe.util.StringUtil;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * className: net.gangpeng.pgq.jsbridge.JsBridge
 * function: js与Android工具类
 * <p>
 * created at 16/11/9 10:52
 *
 * @author gangpeng
 */
public class JsBridge {

    /**
     * 用来存储反射后，IJsBridge实现类 方法
     */
    private static Map<String, Map<String, Method>> exposedMethod = new HashMap<>();

    /**
     * @param alias     类别名
     * @param className IJsBridge实现类
     */
    public static void register(String alias, Class<? extends IJsBridge> className) {
        if (exposedMethod.containsKey(alias)) {
            L.e("JSBridge: " + alias + "已经被注册");
            return;
        }

        try {
            exposedMethod.put(alias, getAllMethod(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 class的所有符合条件的方法。详见代码及IJsBridge实现类
     *
     * @param className
     * @return
     * @throws Exception
     */
    private static Map<String, Method> getAllMethod(Class className) throws Exception {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] methods = className.getDeclaredMethods();

        for (Method method : methods) {
            //方法必须是 public 和 static
            if (method.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC)
                    || StringUtil.isBlank(method.getName())) {
                continue;
            }
            Class[] parameterTypes = method.getParameterTypes();
            //参数要有三个，分别是 WebView，JsonObject（gson）， CallBack
            if (parameterTypes != null && parameterTypes.length == 3) {
                if (parameterTypes[0] == WebView.class && parameterTypes[1] == JSONObject.class
                        && parameterTypes[2] == CallBack.class) {
                    methodMap.put(method.getName(), method);
                }
            }
        }

        return methodMap;
    }

    /**
     * js调用java代码
     *
     * @param webView js所在webView
     * @param msg     js传递的信息
     * @return
     */
    public static String callJava(WebView webView, String msg) {
        //类名
        String className = "";
        //方法名
        String methodName = "";
        //端口，用于调用js代码
        String port = "";
        //js传递参数
        String params = "";
        //解析 msg
        if (StringUtil.isNotBlank(msg) && msg.startsWith("JSBridge")) {
            Uri uri = Uri.parse(msg);
            className = uri.getHost();
            port = uri.getPort() + "";
            params = uri.getQuery();
            String path = uri.getPath();
            if (StringUtil.isNotBlank(path)) {
                methodName = path.replace("/", "");
            }
        }
        //调用相关方法
        if (exposedMethod.containsKey(className)) {
            Map<String, Method> methodMap = exposedMethod.get(className);
            if (methodMap != null && methodMap.containsKey(methodName)) {
                Method method = methodMap.get(methodName);
                try {
                    method.invoke(null, webView, new JSONObject(params), new CallBack(webView, port));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
