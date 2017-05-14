package net.gangpeng.pgq.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.gangpeng.pgq.R;

/**
 * className: TextEditLayout
 * function: 左边textView，右边EditText自定义布局
 * <p/>
 * created at 16/7/20 09:57
 *
 * @author pg
 */
public class TextEditLayout extends LinearLayout {

    /**
     * textView相关属性
     */
    private String tvStr;

    /**
     * editView相关属性
     */
    private String etHint;
    private int inputType;

    private TextView tv;
    private EditText et;

    public TextEditLayout(Context context) {
        this(context, null);
    }

    public TextEditLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextEditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextEditLayout, defStyleAttr, 0);

        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            switch (ta.getIndex(i)) {
                case R.styleable.TextEditLayout_tv:
                    tvStr = ta.getString(ta.getIndex(i));
                    break;
                case R.styleable.TextEditLayout_etHint:
                    etHint = ta.getString(ta.getIndex(i));
                    break;
                case R.styleable.TextEditLayout_inputType:
                    inputType = ta.getInt(ta.getIndex(i), InputType.TYPE_CLASS_TEXT);
                default:
                    break;

            }
        }

        View view = LayoutInflater.from(context).inflate(R.layout.view_text_edit, this, true);
        tv = (TextView) view.findViewById(R.id.tv_text_edit);
        et = (EditText) view.findViewById(R.id.et_text_edit);

        tv.setText(tvStr);
        et.setHint(etHint);
        et.setInputType(inputType);

        ta.recycle();
    }

    public String getEditText() {
        return et.getText().toString();
    }

    public void setEditText(String str) {
        et.setText(str);
    }

    public void setEditText(int str) {
        et.setText(String.valueOf(str));
    }

    public void setEditText(float str) {
        et.setText(String.valueOf(str));
    }
}
