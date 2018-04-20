package com.lawdev.designal.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lawdev.designal.R;
import com.lawdev.designal.helpers.PerformersAdapter;
import com.lawdev.designal.helpers.PerformersData;
import com.lawdev.designal.main_activities.MainActivity;

public class PerformersTab extends Fragment {
    PerformersAdapter performersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.performers, container, false);

        performersAdapter = new PerformersAdapter(view.getContext().getApplicationContext(), new PerformersData().getGroup());

        ListView listView = view.findViewById(R.id.mainListView);
        listView.setAdapter(performersAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                intent.putExtra("Selected", i);
                startActivityForResult(intent, 112);
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
