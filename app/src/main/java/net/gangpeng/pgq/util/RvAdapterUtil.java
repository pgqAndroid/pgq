package net.gangpeng.pgq.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * className: net.gangpeng.pgq.util.RvAdapterUtil
 * function: RecyclerView通用的adapter
 * <p>
 * created at 16/10/27 15:39
 *
 * @author gangpeng
 */
public abstract class RvAdapterUtil<T> extends RecyclerView.Adapter<RvViewHoldUtil> {

    /**
     * 父容器
     */
    public Context mContext;
    /**
     * 数据源
     */
    public List<T> mData;
    /**
     * 布局id
     */
    public int mLayoutId;

    public RvAdapterUtil(Context context, List<T> data, int layoutId) {
        mContext = context;
        mData = data;
        mLayoutId = layoutId;
    }

    @Override
    public RvViewHoldUtil onCreateViewHolder(ViewGroup parent, int viewType) {
        RvViewHoldUtil viewHold = RvViewHoldUtil.getViewHold(mContext, mLayoutId, parent);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(RvViewHoldUtil holder, int position) {
        convert(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract void convert(RvViewHoldUtil hold, T data, int pos);
}
