package net.gangpeng.pgq.util;

/**
 * className: SysCode
 * function: 系统中的常量
 * <p/>
 * created at 16/7/7 15:29
 *
 * @author pg
 */
public class SysCode {

    /**
     * 分享相关
     */
    public static String SHARE_TITLE = "title";
    public static String SHARE_TITLE_URL = "http://www.baidu.com/";
    public static String SHARE_TEXT = "text";
    public static String SHARE_URL = "http://www.pgqzone.com/";
    public static String SHARE_IMAGE_URL = "http://pic25.nipic.com/20121112/5955207_224247025000_2.jpg";

    /**
     * toast显示时间
     */
    public static int TOAST_TIME = 2000;

    /**
     * intent之间的跳转参数
     */
    public interface INTENT_PARAM {
        String DATA = "data";
        String TITLE = "title";
        String URL = "url";
    }

    /**
     * handler消息
     */
    public interface HANDLE_MSG {
        int HANDLER_HOME = 1000;
    }
}
