package com.lawdev.designal.main_activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lawdev.designal.R;
import com.lawdev.designal.entities.Group;
import com.lawdev.designal.helpers.GroupsAdapter;
import com.lawdev.designal.helpers.GroupsData;
import com.lawdev.designal.helpers.PerformersAdapter;
import com.lawdev.designal.helpers.PerformersData;
import com.lawdev.designal.helpers.TasksData;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        final ArrayList<Group> groups = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance(); //получение экземпляра класса FirebaseAuth, для работы с разграничение доступа
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        final GroupsAdapter groupsAdapter;

        groupsAdapter = new GroupsAdapter(getApplicationContext(), groups);

        db.collection("groups")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                          for (QueryDocumentSnapshot item : task.getResult()) {
                              if (((List)item.getData().get("users")).contains(currentUser.getEmail())){
                                  groups.add(new Group(item.get("name").toString(), item.get("main_task").toString()));
                              }
                              groupsAdapter.notifyDataSetChanged();
                          }
                    }
                });

        ListView listView = findViewById(R.id.GroupListView);
        listView.setAdapter(groupsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TasksData.tasks = new ArrayList<>();

                db.collection("tasks")
                        .whereEqualTo("group", getIntent().getStringExtra("group_name"))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot item : task.getResult()) {
                                        TasksData.tasks.add(new com.lawdev.designal.entities.Task(
                                                item.get("name").toString(), item.get("description").toString()));
                                    }

                                }
                            }
                        });

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("Selected", i);
                intent.putExtra("group_name", groups.get(i).getName());
                startActivityForResult(intent, 112);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate your main_menu into the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
                return true;
//            case R.id.help:
//                showHelp();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
