package com.xoksis.mygrocerystore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.xoksis.mygrocerystore.R;

import com.xoksis.mygrocerystore.databinding.ActivityHomeBinding;



public class HomeActivity extends AppCompatActivity {

    FirebaseAuth auth;

    ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();

        homeBinding.progressbar.setVisibility(View.GONE);
        if (auth.getCurrentUser() != null) {
            homeBinding.progressbar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            Toast.makeText(this, "Please wait you are already logged in", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void login(View view) {

        startActivity(new Intent(HomeActivity.this, LoginActivity.class));

    }

    public void registration(View view) {

        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));

    }


}