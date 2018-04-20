package com.lawdev.designal.main_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lawdev.designal.R;

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
    }
}
