package com.lawdev.designal.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lawdev.designal.R;
import com.lawdev.designal.helpers.TasksAdapter;
import com.lawdev.designal.helpers.TasksData;

import java.util.Collection;

import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

public class TasksTab extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tasks, container, false);
    }

    TasksData taskData = new TasksData();
    final TasksAdapter adapter = new TasksAdapter(TasksData.tasks);

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    public static Collection tasks = null;
    public void update() {
        System.out.print("lol");
        adapter.notifyDataSetChanged();

            if(tasks != null) {
                adapter.add(tasks);
                tasks = null;
            }

    }

    public void onResume() {

        super.onResume();

        update();
    }


    @Override
    public void onPause() {
        super.onPause();


        update();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView myrecyclerview = view.findViewById(R.id.recycler_view);

        mAuth = FirebaseAuth.getInstance(); //получение экземпляра класса FirebaseAuth, для работы с разграничение доступа
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        final TasksAdapter adapter = new TasksAdapter(TasksData.tasks);
        myrecyclerview.hasFixedSize();
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        myrecyclerview.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(adapter,
                true, true, false);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(myrecyclerview);

        myrecyclerview.addItemDecoration(
                new RVHItemDividerDecoration(getContext(), LinearLayoutManager.VERTICAL));

        // Set On Click
        myrecyclerview.addOnItemTouchListener(
                new RVHItemClickListener(getContext(), new RVHItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TasksData.finished_tasks.add(TasksData.tasks.get(position));
                        TasksData.tasks.remove(position);
                        adapter.remove(position);
                    }
                }));


    }

}
