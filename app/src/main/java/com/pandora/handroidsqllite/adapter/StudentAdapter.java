package com.pandora.handroidsqllite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pandora.handroidsqllite.R;
import com.pandora.handroidsqllite.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    /**
     * 数据集
     */
    private List<Student> mStudents;

    /**
     * 点击事件
     */
    private OnItemClickListener mOnItemClickListener;

    public StudentAdapter() {

    }

    public StudentAdapter(List<Student> students) {
        this.mStudents = students;
    }

    /**
     * 更新列表
     *
     * @param data
     */
    public void update(List<Student> data) {
        this.mStudents = data;
        notifyDataSetChanged();
    }

    /**
     * 添加新的Item
     */
    public void addStudent(Student student) {
        if (mStudents == null) {
            mStudents = new ArrayList<>();
        }
        mStudents.add(0, student);
        notifyItemInserted(0);
    }

    /**
     * 删除Item
     */
    public void deleteItem(Student student) {
        if (mStudents == null || mStudents.isEmpty()) {
            return;
        }
        //mStudents.remove(0);
        //notifyItemRemoved(0);
        mStudents.remove(student);
        notifyDataSetChanged();
    }

    /**
     * 设置点击事件监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);

        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        Student student = mStudents.get(position);
        holder.mTvStu.setText(student.getName() + "," + student.getSex() + "," + student.getAge() + "岁");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudents == null ? 0 : mStudents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvStu;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvStu = (TextView) itemView.findViewById(R.id.tv_stu);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
