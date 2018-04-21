package com.lawdev.designal.main_activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lawdev.designal.R;
import com.lawdev.designal.entities.Group;
import com.lawdev.designal.helpers.TasksData;
import com.lawdev.designal.tabs.CompletedTasksTab;
import com.lawdev.designal.tabs.PerformersTab;
import com.lawdev.designal.tabs.TasksTab;

import java.util.ArrayList;
import java.util.List;

/*
   Главное окно приложения.
   Включает в себя две формы, которые можно свайпать.

   На нем располагаются:

   1. Нижний таскбар:
       Кнопка Добавить.
       Кнопка Изменить.
       Кнопка Удалить.

   2. Первая форма:
       List из задач. Их обязательно двигать.
       Поиск по названиям и тексту заметок.
       Кнопка назад.

   3. Вторая форма:
       List из людей в той группе, которая открыта.
       Поиск по людям. (Опционально)

*/

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance(); //получение экземпляра класса FirebaseAuth, для работы с разграничение доступа
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate your main_menu into the menu
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    TasksTab tasksTab = new TasksTab();
                    return tasksTab;

                case 1:
                    CompletedTasksTab completedTasksTab = new CompletedTasksTab();
                    return completedTasksTab;

                case 2:
                    PerformersTab performersTab = new PerformersTab();
                    return performersTab;

                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

