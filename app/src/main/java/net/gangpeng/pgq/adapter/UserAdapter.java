package net.gangpeng.pgq.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.gangpeng.pgq.R;
import net.gangpeng.pgq.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.NoteViewHolder> {

    private NoteClickListener clickListener;
    private List<User> dataset;

    public interface NoteClickListener {
        void onNoteClick(int position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextView comment;
        public TextView age;

        public NoteViewHolder(View itemView, final NoteClickListener clickListener) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_name);
            age = (TextView) itemView.findViewById(R.id.tv_age);
            comment = (TextView) itemView.findViewById(R.id.tv_comment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public UserAdapter(NoteClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<User>();
    }

    public void setNotes(@NonNull List<User> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public User getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public UserAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(UserAdapter.NoteViewHolder holder, int position) {
        User note = dataset.get(position);
        holder.text.setText(note.getName());
        holder.age.setText(String.valueOf(note.getAge()));
        holder.comment.setText(note.getComment());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
