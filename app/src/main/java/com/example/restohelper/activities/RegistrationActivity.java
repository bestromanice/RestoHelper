package com.example.restohelper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.restohelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {

            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            finish();
        }

        name = findViewById(R.id.sign_up_name_edit_text);
        email = findViewById(R.id.sign_up_email_edit_text);
        password = findViewById(R.id.sign_up_password_edit_text);

    }

    public void signUp(View view) {

        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userName)) {

            Toast.makeText(this, "Введите имя!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userEmail)) {

            Toast.makeText(this, "Введите email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {

            Toast.makeText(this, "Введите пароль!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length() < 6) {

            Toast.makeText(this, "Слишком короткий пароль, введите минимум 6 символов!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Успешно зарегистрирован", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Ошибка регистрации: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signIn(View view) {

        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}