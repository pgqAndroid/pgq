package net.gangpeng.pgq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gangpeng.pgframe.util.DensityUtil;

import net.gangpeng.pgq.R;

import java.util.List;
import java.util.Random;

/**
 * className: RecyclerListAdapter
 * function: recyclerView适配器
 * <p/>
 * created at 16/9/22 15:35
 *
 * @author pg
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.RecyclerHolder> {

    /**
     * 父容器
     */
    private Context context;
    /**
     * 数据对象
     */
    private List<String> dataList;

    private IClickListener iClickListener;

    /**
     * 实现点击事件的接口
     */
    public interface IClickListener {
        void onItemClick(View view, int pos);
    }

    public RecyclerListAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler
                , parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, final int position) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.tvName.getLayoutParams();
        lp.height = DensityUtil.dp2px(context, 20 + new Random().nextInt(50));

        holder.tvName.setLayoutParams(lp);
        holder.tvName.setText(dataList.get(position));
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iClickListener != null) {
                    iClickListener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public RecyclerHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    /**
     * 添加一条数据
     *
     * @param position
     */
    public void addData(int position) {
        dataList.add(position, "add one");
        notifyItemInserted(position);
    }

    /**
     * 删除一条数据
     *
     * @param position
     */
    public void remeveData(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public void setClickListener(IClickListener iClickListener) {
        this.iClickListener = iClickListener;
    }
}
