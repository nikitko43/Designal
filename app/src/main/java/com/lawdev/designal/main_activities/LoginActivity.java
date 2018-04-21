package com.lawdev.designal.main_activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lawdev.designal.R;

import java.util.HashMap;
import java.util.Map;

/*
   Форма входа.

   На нем располагаются:

   1. Иконка приложения.
   2. Название приложения.
   3. Два TextEdit'а. перед каждым из них есть иконка (пользователь, либо замок).
   4. Кнопка Login.

*/
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //вызывается при создании формы
        super.onCreate(savedInstanceState); //передача параметров для создания при вызове метода родительского класса
        setContentView(R.layout.activity_login);    //устанавливает содержимое Activity из layout-файла

        mAuth = FirebaseAuth.getInstance(); //получение экземпляра класса FirebaseAuth, для работы с разграничение доступа
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void onClickLogIn(View v) {  //вызывает при нажатии на кнопку входа
        //считывание данных из полей для ввода
        EditText etEmail = findViewById(R.id.et_password);
        EditText etPassword = findViewById(R.id.et_surname);
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.equals("")){
            Toast.makeText(LoginActivity.this, "Введите e-mail", Toast.LENGTH_SHORT).show();
        }
        else if (password.equals("")){
            Toast.makeText(LoginActivity.this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(LoginActivity.this, GroupsActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Неверный логин/пароль", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }

//    public void addUserToDataBase(String name, String surname, String email) {
//        // Sign in success, update UI with the signed-in user's information
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Alan");
//        user.put("last", "Turing");
//        user.put("email", "");
//
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
//    }

    public void onClickToRegistration(View v) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
}