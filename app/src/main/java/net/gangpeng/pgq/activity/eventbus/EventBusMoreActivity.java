package net.gangpeng.pgq.activity.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.T;

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
public class EventBusMoreActivity extends BaseActivity {
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
        EventBus.getDefault().register(this);
    }

    /**
     * 测试event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void eventTest(String str) {
        T.showToastNotRepeat(this, str, SysCode.TOAST_TIME);
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
                EventBus.getDefault().post("a");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
