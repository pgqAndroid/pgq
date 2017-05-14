package net.gangpeng.pgq.activity.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.T;
import com.google.gson.Gson;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.domain.EventBean;
import net.gangpeng.pgq.util.SysCode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * className: net.gangpeng.pgq.activity.eventbus.EventBusActivity
 * function: EventBus
 * <p/>
 * created at 17/1/11 15:58
 *
 * @author gangpeng
 */
public class EventBusActivity extends BaseActivity {
    @InjectView(R.id.tv_post)
    TextView tvPost;
    @InjectView(R.id.tv_main)
    TextView tvMain;
    @InjectView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.inject(this);
        //eventInheritance表示是否只在本类中有效
        EventBus.getDefault().register(this);
    }

    /**
     * 测试event
     * 1. 可以有return，但无作用
     * 2. 可以有两个配置相当的方法，都会进入（及时在两个类中）
     * 3. 无对应的方法是，post也不会报错
     * 4. 只能有一个参数
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventTest(EventBean eventBean) {
        T.showToastNotRepeat(this, new Gson().toJson(eventBean), SysCode.TOAST_TIME);
    }

    @OnClick({R.id.tv_post, R.id.tv_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_post:
                EventBean eventBean = new EventBean();
                eventBean.setName("name");
                eventBean.setAuthor("pg");
                final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
                String comment = df.format(new Date());
                eventBean.setTime(comment);
                EventBus.getDefault().post(eventBean);
                break;
            case R.id.tv_main:
                Intent intent = new Intent(this, EventBusMoreActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
