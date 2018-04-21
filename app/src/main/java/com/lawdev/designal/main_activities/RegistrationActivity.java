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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lawdev.designal.R;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance(); //получение экземпляра класса FirebaseAuth, для работы с разграничение доступа
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void onClickRegistration(View v){
        EditText et_surname = findViewById(R.id.et_surname);
        EditText et_name = findViewById(R.id.et_fname);
        EditText et_email =  findViewById(R.id.et_email_r);
        EditText et_password = findViewById(R.id.et_password_r);
        if (et_surname.getText().toString() == ""){
            Toast.makeText(RegistrationActivity.this, "Введите фамилию",
                    Toast.LENGTH_SHORT).show();
        }
        if (et_name.getText().toString() == ""){
            Toast.makeText(RegistrationActivity.this, "Введите имя",
                    Toast.LENGTH_SHORT).show();
        }
        if (et_email.getText().toString() == ""){
            Toast.makeText(RegistrationActivity.this, "Введите e-mail",
                    Toast.LENGTH_SHORT).show();
        }
        if (et_password.getText().toString() == ""){
            Toast.makeText(RegistrationActivity.this, "Введите пароль",
                    Toast.LENGTH_SHORT).show();
        }

        final String name = et_name.getText().toString();
        final String surname = et_surname.getText().toString();
        final String email = et_email.getText().toString();
        final String password = et_password.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(RegistrationActivity.this, SecondLoginActivity.class);
                            intent.putExtra("email", email);
                            intent.putExtra("name", name);
                            intent.putExtra("surname", surname);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}
