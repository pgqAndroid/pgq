package net.gangpeng.pgq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.gangpeng.pgframe.util.AppUtil;
import com.gangpeng.pgframe.util.FileUtil;
import com.gangpeng.pgframe.util.L;
import com.gangpeng.pgframe.util.StringUtil;
import com.gangpeng.pgframe.util.T;
import com.google.gson.Gson;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.adapter.ShareDialogAdapter;
import net.gangpeng.pgq.domain.ShareBean;
import net.gangpeng.pgq.domain.ShareContentBean;
import net.gangpeng.pgq.util.PgqUtil;
import net.gangpeng.pgq.util.SysCode;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * className: ShareDialogActivity
 * function: 分享弹出框
 * <p/>
 * created at
 *
 * @author pg
 */
public class ShareDialogActivity extends BaseActivity {

    /**
     * 分享弹出框布局
     */
    private GridView shareGv;
    /**
     * 整体布局，设置点击背景消失效果
     */
    private View globalLayout;

    /**
     * 分享adapter
     */
    private ShareDialogAdapter shareDialogAdapter;

    /**
     * 分享参数
     */
    private ShareContentBean shareContentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_share);

        //获取分享参数
        Intent intent = getIntent();
        String data = intent.getStringExtra(SysCode.INTENT_PARAM.DATA);
        if (StringUtil.isNotBlank(data)) {
            shareContentBean = new Gson().fromJson(data, ShareContentBean.class);
        }

        //初始化控件变量
        shareGv = (GridView) findViewById(R.id.gv_share);
        globalLayout = findViewById(R.id.global_layout);

        //设置点击背景，此弹出框消失
        globalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //设置分享列表
        shareDialogAdapter = new ShareDialogAdapter(this, PgqUtil.getShareList(), R.layout.item_share);
        shareGv.setAdapter(shareDialogAdapter);

        //设置分享渠道点击事件
        shareGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShareBean shareBean = PgqUtil.getShareList().get((int) id);
                share(shareBean);
                onBackPressed();
            }
        });
    }

    /**
     * 分享
     *
     * @param bean
     */
    private void share(ShareBean bean) {
        if (bean.getAppPackage() != null) {
            if (!AppUtil.isInstalledApp(ShareDialogActivity.this, bean.getAppPackage())) {
                T.showToastNotRepeat(ShareDialogActivity.this
                        , "您未安装" + bean.getAppName() + "客户端，请先安装", SysCode.TOAST_TIME);
                return;
            }
        }
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setSilent(true);
        oks.setPlatform(bean.getPlatform());
        //设置标题
        if (shareContentBean != null && StringUtil.isNotBlank(shareContentBean.getTitle())) {
            oks.setTitle(shareContentBean.getTitle());
        } else {
            oks.setTitle(SysCode.SHARE_TITLE);
        }
        //设置标题连接
        if (shareContentBean != null && StringUtil.isNotBlank(shareContentBean.getTitleUrl())) {
            oks.setTitleUrl(shareContentBean.getTitleUrl());
        } else {
            oks.setTitleUrl(SysCode.SHARE_TITLE_URL);
        }
        //设置内容
        if (shareContentBean != null && StringUtil.isNotBlank(shareContentBean.getText())) {
            oks.setText(shareContentBean.getText());
        } else {
            oks.setText(SysCode.SHARE_TEXT);
        }
        //设置链接
        if (shareContentBean != null && StringUtil.isNotBlank(shareContentBean.getUrl())) {
            oks.setUrl(shareContentBean.getUrl());
        } else {
            oks.setUrl(SysCode.SHARE_URL);
        }
        //设置图片地址
        if (shareContentBean != null && StringUtil.isNotBlank(shareContentBean.getImageUrl())) {
            oks.setImageUrl(shareContentBean.getImageUrl());
        } else {
            oks.setImageUrl(SysCode.SHARE_IMAGE_URL);
        }
        //微信，朋友圈可用。ImagePath可放本地路径
//        oks.setImagePath(FileUtil.getSdPath()+"bbb.jpg");
        oks.setCallback(new ShareCallback());
        oks.show(this);
    }

    private class ShareCallback implements PlatformActionListener {

        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            T.showToastNotRepeat(ShareDialogActivity.this, "分享成功", SysCode.TOAST_TIME);
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            L.e(throwable.toString());
            T.showToastNotRepeat(ShareDialogActivity.this, "分享失败", SysCode.TOAST_TIME);
        }

        @Override
        public void onCancel(Platform platform, int i) {

        }
    }

}
