package com.lawdev.designal.helpers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lawdev.designal.R;
import com.lawdev.designal.entities.Task;

import java.util.ArrayList;
import java.util.Collections;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ItemViewHolder>
        implements RVHAdapter {

    public class ItemViewHolder extends RecyclerView.ViewHolder implements RVHViewHolder {

        TextView tv_name;
        TextView tv_description;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_description = itemView.findViewById(R.id.tv_description);
        }

        @Override
        public void onItemClear() {

        }

        @Override
        public void onItemSelected(int actionstate) {

        }
    }

    private final ArrayList<Task> items = new ArrayList<>();

    public TasksAdapter(ArrayList<Task> tasks) {
        items.addAll(tasks);
    }

    @Override
    public int getItemCount() {
        System.out.println(items.size());
        return items.size();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tv_name.setText(items.get(position).getName());
        holder.tv_description.setText(items.get(position).getDescription());
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onItemDismiss(int position, int direction) {
        remove(position);
    }

    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        swap(fromPosition, toPosition);
        return false;
    }

    public void add(Task o) {
        items.add(items.size(), o);
        notifyItemInserted(items.size());
        notifyDataSetChanged();
    }

    public void remove(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    private void swap(int firstPosition, int secondPosition) {
        Collections.swap(items, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }
}