package net.gangpeng.pgq.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 用于解析adapter中的布局文件，并把控件的id与控件的view对应起来
 */
public class ViewHoldUtil {
    /**
     * 用于存放控件的列表，SparseArray类似与HashMap,但占用内存较少
     */
    private SparseArray<View> as;
    /**
     * 布局view
     */
    private View mConvertView;

    private ViewHoldUtil(Context context, int layoutId, ViewGroup root) {
        as = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, root, false);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHoldUtil的方法
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static ViewHoldUtil getInstance(Context context, View convertView, ViewGroup parent, int layoutId) {
        //如果没有初始化布局，则创建一个；否则直接从converView中获取
        if (convertView == null) {
            return new ViewHoldUtil(context, layoutId, parent);
        }
        return (ViewHoldUtil) convertView.getTag();
    }

    /**
     * 根据控件id获取对应的view对象
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int id) {
        View view = as.get(id);
        if (null == view) {
            view = mConvertView.findViewById(id);
            as.put(id, view);
        }

        return (T) view;
    }

    /**
     * 返回布局view对象
     *
     * @return
     */
    public View getConvertView() {
        return mConvertView;
    }
}
