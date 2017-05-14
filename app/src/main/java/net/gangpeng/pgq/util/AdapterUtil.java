package net.gangpeng.pgq.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 类名：通用adapter
 * 作者：gangpeng
 * 主要功能：adapter的通用适配器，简化adapter的调用方式
 * 创建日期：2015-07-10
 *
 * @param <T> 数据格式
 */
public abstract class AdapterUtil<T> extends BaseAdapter {
    /**
     * 数据列表，用来存放所要展示的数据
     */
    protected List<T> list;
    /**
     * 该adapter所依赖的父容器
     */
    protected Context mContext;
    /**
     * 用于解析XML布局文件的工具类
     */
    protected LayoutInflater inflater;
    /**
     * 每个子项所需展示的XML布局文件的id
     */
    protected final int layoutId;

    /**
     * 构造方法，初始化成员变量
     *
     * @param list
     * @param mContext
     * @param layoutId
     */
    public AdapterUtil(Context mContext, List<T> list, int layoutId) {
        super();
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < 0 || position >= getCount()) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //得到viewHold
        final ViewHoldUtil viewHold = getViewHold(mContext, convertView, parent);
        //处理数据
        convert(list.get(position), viewHold, position);

        return viewHold.getConvertView();
    }

    /**
     * 得到一个包含布局控件的类
     *
     * @param mContext
     * @param convertView
     * @param parent
     * @return
     */
    private ViewHoldUtil getViewHold(Context mContext, View convertView, ViewGroup parent) {
        return ViewHoldUtil.getInstance(mContext, convertView, parent, layoutId);
    }

    /**
     * 用于处理数据的抽象方法
     *
     * @param data     一条数据
     * @param viewHold 布局控件类
     * @param position 数据所在列表的位置
     */
    public abstract void convert(T data, ViewHoldUtil viewHold, int position);
}
