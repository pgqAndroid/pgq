package net.gangpeng.pgq.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gangpeng.pgframe.util.DensityUtil;
import com.gangpeng.pgframe.util.T;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.util.RvAdapterUtil;
import net.gangpeng.pgq.util.RvViewHoldUtil;
import net.gangpeng.pgq.util.SysCode;

import java.util.List;
import java.util.Random;

/**
 * className: net.gangpeng.pgq.adapter
 * function:
 * <p/>
 * created at 16/10/27 15:59
 *
 * @author gangpeng
 */
public class RecyclerGirdAdapter extends RvAdapterUtil<String> {

    public RecyclerGirdAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(RvViewHoldUtil hold, final String data, final int pos) {
        TextView tv = hold.getView(R.id.tv_name);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
        lp.height = DensityUtil.dp2px(mContext, 20 + new Random().nextInt(50));
        tv.setLayoutParams(lp);

        tv.setText(data);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showToastNotRepeat(mContext, data + ", position:" + pos, SysCode.TOAST_TIME);
            }
        });
    }

    /**
     * 添加一条数据
     *
     * @param position
     */
    public void addData(int position) {
        mData.add(position, "add one");
        notifyItemInserted(position);
    }

    /**
     * 删除一条数据
     *
     * @param position
     */
    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
