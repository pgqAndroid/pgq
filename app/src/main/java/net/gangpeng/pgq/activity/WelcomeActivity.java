package net.gangpeng.pgq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.SysCode;

/**
 * className: net.gangpeng.pgq.activity.WelcomeActivity
 * function: 欢迎界面
 * <p/>
 * created at 16/12/5 15:12
 *
 * @author gangpeng
 */
public class WelcomeActivity extends BaseActivity implements Handler.Callback {

    /**
     * 用于处理线程的handler
     */
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //防止第一个安装时打开，再从图标打开的问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(R.layout.activity_welcome);
        //初始化变量
        handler = new Handler(this);
        //两秒后跳转至首页
        handler.sendEmptyMessageDelayed(SysCode.HANDLE_MSG.HANDLER_HOME, 500);
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            //跳转至首页
            case SysCode.HANDLE_MSG.HANDLER_HOME:
                Intent toHome = new Intent(this, HomeActivity.class);
                startActivity(toHome);
                break;
            default:
                break;
        }
        return false;
    }
}
