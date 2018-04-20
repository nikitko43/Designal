package com.lawdev.designal.helpers;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawdev.designal.R;
import com.lawdev.designal.entities.Performer;
import com.lawdev.designal.entities.Task;

import java.util.ArrayList;

public class FinishedTasksAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Task> tasks;

    public FinishedTasksAdapter(Context context, ArrayList<Task> tasks) {
        this.tasks = tasks;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.completed_task_item, parent, false);
        }

        Task task = getPerson(position);

        TextView tv = ((TextView) view.findViewById(R.id.tv_name_finished));
        tv.setText(task.getName());
        ((TextView) view.findViewById(R.id.tv_description_finished)).setText(task.getDescription());
        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        return view;
    }

    private Task getPerson(int position) {
        return ((Task) getItem(position));
    }
}
