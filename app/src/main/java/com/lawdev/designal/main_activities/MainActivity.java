package com.lawdev.designal.main_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lawdev.designal.R;
import com.lawdev.designal.tabs.CompletedTasksTab;
import com.lawdev.designal.tabs.PerformersTab;
import com.lawdev.designal.tabs.TasksTab;

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
    TasksTab tasksTab;
    CompletedTasksTab completedTasksTab;

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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(tasksTab != null) tasksTab.update();
                if(tasksTab != null) completedTasksTab.update();
                System.out.print("lll");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate your main_menu into the menu
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
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
                    tasksTab = new TasksTab();
                    tasksTab.update();
                    return tasksTab;

                case 1:
                    completedTasksTab = new CompletedTasksTab();
                    tasksTab.update();
                    return completedTasksTab;

                case 2:
                    PerformersTab performersTab = new PerformersTab();
                    return performersTab;

                default:
                    return null;
            }

        }


        @Override
        public int getCount() {
            return 3;
        }
    }
}
