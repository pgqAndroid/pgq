package net.gangpeng.pgq.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.domain.ShareBean;
import net.gangpeng.pgq.util.AdapterUtil;
import net.gangpeng.pgq.util.ViewHoldUtil;

import java.util.List;

/**
 * Created by gangpeng on 16/6/13.
 */
public class ShareDialogAdapter extends AdapterUtil<ShareBean> {

    /**
     * 构造方法，初始化成员变量
     *
     * @param mContext
     * @param list
     * @param layoutId
     */
    public ShareDialogAdapter(Context mContext, List list, int layoutId) {
        super(mContext, list, layoutId);
    }

    @Override
    public void convert(ShareBean data, ViewHoldUtil viewHold, int position) {
        TextView name = viewHold.getView(R.id.tv_share_name);
        ImageView image = viewHold.getView(R.id.iv_share_img);

        name.setText(data.getName());
        image.setImageResource(data.getPictureId());
    }
}
