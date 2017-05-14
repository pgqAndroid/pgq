package com.gangpeng.test;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by gangpeng on 16/7/5.
 */
public class CustomView extends RelativeLayout {

    private String itemName = "";
    private int itemIcon;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            switch (ta.getIndex(i)) {
                case R.styleable.CustomView_item_name:
                    itemName = ta.getString(ta.getIndex(i));
                    break;
                case R.styleable.CustomView_item_icon:
                    itemIcon = ta.getResourceId(ta.getIndex(i), R.drawable.mine_score_icon);
                    break;
                default:
                    break;
            }
        }

        View view = LayoutInflater.from(context).inflate(R.layout.custom_view, this, true);
        TextView nameTv = (TextView) view.findViewById(R.id.tv_name);
        ImageView iconIv = (ImageView) view.findViewById(R.id.iv_icon);
        if (!"".equals(itemName)) {
            nameTv.setText(itemName);
        }
        iconIv.setImageResource(itemIcon);
        ta.recycle();
    }

}
