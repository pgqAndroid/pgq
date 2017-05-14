package net.gangpeng.pgq.util;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.domain.ShareBean;

import java.util.ArrayList;
import java.util.List;

/**
 * className: PgqUtil
 * function: 与本项目相关的一些工具类
 *
 * @author pg
 *         created at 16/6/27 16:03
 */
public class PgqUtil {

    /**
     * 获取分享渠道列表
     *
     * @return
     */
    public static List<ShareBean> getShareList() {
        List<ShareBean> shareBeanList = new ArrayList<>();
        //分享渠道
        ShareBean qqShare = new ShareBean("QQ", R.drawable.share_icon_qq, "QQ", "com.tencent.mobileqq", "QQ");
        ShareBean qqZoneShare = new ShareBean("QQ空间", R.drawable.share_icon_qqzone, "QZone", "com.tencent.mobileqq", "QQ");
        ShareBean wxShare = new ShareBean("微信", R.drawable.share_icon_weixin, "Wechat", "com.tencent.mm", "微信");
        ShareBean wxFriendShare = new ShareBean("朋友圈", R.drawable.share_icon_friend, "WechatMoments", "com.tencent.mm", "微信");
        ShareBean wbShare = new ShareBean("新浪微博", R.drawable.ssdk_oks_classic_sinaweibo, "SinaWeibo", "com.sina.weibo", "新浪微博");
        //添加到列表中
        shareBeanList.add(qqShare);
        shareBeanList.add(qqZoneShare);
        shareBeanList.add(wxShare);
        shareBeanList.add(wxFriendShare);
        shareBeanList.add(wbShare);
        return shareBeanList;
    }
}
