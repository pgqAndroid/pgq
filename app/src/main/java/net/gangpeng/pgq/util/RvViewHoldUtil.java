package net.gangpeng.pgq.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * className: net.gangpeng.pgq.util.RvViewHoldUtil
 * function: RecyclerView通用ViewHold
 * <p>
 * created at 16/10/27 14:50
 *
 * @author gangpeng
 */
public class RvViewHoldUtil extends RecyclerView.ViewHolder {

    /**
     * 父容器
     */
    private Context mContext;
    /**
     * RecyclerView的item布局
     */
    private View mConvertView;
    /**
     * 存储item布局里面元素的数组
     */
    private SparseArray<View> mSparseArray;

    /**
     * 构造方法 初始化变量
     *
     * @param context  父容器
     * @param itemView item布局
     */
    private RvViewHoldUtil(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mSparseArray = new SparseArray<>();
    }

    /**
     * 获取ViewHold
     *
     * @param context  父容器
     * @param itemView item布局
     * @return
     */
    public static RvViewHoldUtil getViewHold(Context context, View itemView) {
        return new RvViewHoldUtil(context, itemView);
    }

    /**
     * 获取ViewHold
     *
     * @param context  父容器
     * @param layoutId 布局id
     * @param parent   父布局
     * @return
     */
    public static RvViewHoldUtil getViewHold(Context context, int layoutId, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RvViewHoldUtil(context, view);
    }

    /**
     * 通过id获取View对象
     *
     * @param viewId id
     * @param <T>    view对象
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mSparseArray.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取item布局文件
     *
     * @return
     */
    public View getConvertView() {
        return mConvertView;
    }
}
