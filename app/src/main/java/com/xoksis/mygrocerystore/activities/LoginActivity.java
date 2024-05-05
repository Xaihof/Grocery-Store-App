package com.xoksis.mygrocerystore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.xoksis.mygrocerystore.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding loginBinding;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        auth = FirebaseAuth.getInstance();

        loginBinding.progressbar.setVisibility(View.GONE);

        loginBinding.signUp.setOnClickListener(v -> {

            startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

        });

        loginBinding.loginBtn.setOnClickListener(v -> {

            loginBinding.progressbar.setVisibility(View.VISIBLE);
            loginUser();

        });

    }

    private void loginUser() {

        String userEmail = loginBinding.emailLogin.getText().toString();
        String userPassword = loginBinding.passwordLogin.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 7) {
            Toast.makeText(this, "Password Length must be greater than 6 letters", Toast.LENGTH_SHORT).show();
            return;
        }
        // login user.

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loginBinding.progressbar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            loginBinding.progressbar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}