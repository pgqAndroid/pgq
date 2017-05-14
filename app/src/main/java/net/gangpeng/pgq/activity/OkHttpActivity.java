package net.gangpeng.pgq.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.pgframe.base.BaseActivity;

import net.gangpeng.pgq.R;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * className: net.gangpeng.pgq.activity.OkHttpActivity
 * function: 测试okHttp
 * <p/>
 * created at 16/12/30 09:18
 *
 * @author gangpeng
 */
public class OkHttpActivity extends BaseActivity {

    @InjectView(R.id.tv_get)
    TextView tvGet;
    @InjectView(R.id.tv_post)
    TextView tvPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_get, R.id.tv_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get:
                getInfo();
                break;
            case R.id.tv_post:
                postInfo();
                break;

        }
    }

    /**
     * get请求
     */
    private void getInfo() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.pgqzone.com/").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                int i = 0;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int i = 0;
            }
        });
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * post请求
     */
    private void postInfo() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, "{\"userId\":\"11111\"}");
        Request request = new Request.Builder().url("http://192.168.59.97:8099/ipsp-rest/rest/authUser/findUserCertify")
                .post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                int i = 0;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int i = 0;
            }
        });
    }
}
