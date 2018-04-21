package com.lawdev.designal.main_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.lawdev.designal.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Designal – Добавление задачи");
    }

    public boolean onOptionsItemSelected(MenuItem item) {   //вызывается при выборе элемента из Action bar
        switch (item.getItemId()) { //выбор действия в соответствии с конкретным элементом меню
            case android.R.id.home: //выход из приложения
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
