package com.lawdev.designal.main_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lawdev.designal.R;
import com.lawdev.designal.helpers.GroupsAdapter;
import com.lawdev.designal.helpers.GroupsData;
import com.lawdev.designal.helpers.PerformersAdapter;
import com.lawdev.designal.helpers.PerformersData;

/*
   Окно групп.

   На нем располагаются:

   1. ListView со списком групп.
        Отображаются все группы, к которым у юзера есть права.
        Желательно реализовать D&D. Проверить, есть ли встроенная
        в android функция.

   2. Кнопка с картинкой юзера.
        Открывает список с действиями, такими, как переход
        к профилю пользователя, log out

   3. Нижний таскбар:
       Кнопка Добавить (нет у юзера)
       Кнопка Изменить (нет у юзера)
       Кнопка Удалить (нет у юзера)

*/

public class GroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        GroupsAdapter groupsAdapter;

        groupsAdapter = new GroupsAdapter(getApplicationContext(), new GroupsData().getGroups());

        ListView listView = findViewById(R.id.GroupListView);
        listView.setAdapter(groupsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), this.getClass());
                intent.putExtra("Selected", i);
                startActivityForResult(intent, 112);
            }
        });

    }
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_groups, container, false);*/


       /* });

        return view;*/

}
