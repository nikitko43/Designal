package com.lawdev.designal.tabs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lawdev.designal.R;
import com.lawdev.designal.helpers.FinishedTasksAdapter;
import com.lawdev.designal.helpers.PerformersAdapter;
import com.lawdev.designal.helpers.PerformersData;
import com.lawdev.designal.helpers.TasksData;

public class CompletedTasksTab extends Fragment {
    FinishedTasksAdapter ftAdapter;

    public void update() {
        if(ftAdapter != null) {
            ftAdapter.notifyDataSetChanged();
        }
    }

    public void onResume() {

        super.onResume();
        System.out.print("1233333");

        update();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.print("1233333");

        update();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.completed_tasks, container, false);

        ftAdapter = new FinishedTasksAdapter(view.getContext().getApplicationContext(), TasksData.finished_tasks);

        ListView listView = view.findViewById(R.id.lv_finished_tasks);
        listView.setAdapter(ftAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TasksData.tasks.add(TasksData.finished_tasks.get(i));
                TasksTab.task = TasksData.finished_tasks.get(i);
                TasksData.finished_tasks.remove(i);
                ftAdapter.notifyDataSetChanged();
                TasksTab.position = i;

            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
