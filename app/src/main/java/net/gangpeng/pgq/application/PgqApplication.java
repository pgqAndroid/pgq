package net.gangpeng.pgq.application;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.webkit.WebView;

import com.baidu.mapapi.SDKInitializer;
import com.gangpeng.pgframe.util.L;

import net.gangpeng.pgq.greendao.DaoMaster;

import org.greenrobot.greendao.database.Database;

import cn.sharesdk.framework.ShareSDK;

/**
 * className: PgqApplication
 * function: no 解释
 * <p>
 * created at 16/12/30 09:30
 *
 * @author pg
 */
public class PgqApplication extends Application {

    /**
     * 数据库操作文件
     */
    private Database db;

    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用百度地图 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //安装内存泄露分析器
//        LeakCanary.install(this);
        //初始化Log工具
        L.init(true, "log");
        //chrome调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
        //初始化分享sdk
        ShareSDK.initSDK(this);
        //数据库设置
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "pgq-db");
        //非加密数据库
//        db = helper.getWritableDb();
        //加密数据库
        db = helper.getEncryptedWritableDb("secret");
    }

    /**
     * 获取数据库操作文件
     *
     * @return
     */
    public Database getDb() {
        return db;
    }
}
