package com.example.jobcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {


    Button btnLogin, btnJobseeker, btnRecruiter;
    Intent moveToLogin, moveToJobseeker, moveToRecruiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);


        btnJobseeker = findViewById(R.id.btnJobseeker);
        btnRecruiter = findViewById(R.id.btnRecruiter);
        btnLogin = findViewById(R.id.loginBtn);
        moveToLogin = new Intent(this, Login.class);
        moveToJobseeker = new Intent(this, SignUpJobseeker.class);
        moveToRecruiter = new Intent(this, SignUpRecruiter.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moveToLogin);
            }
        });

        btnJobseeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moveToJobseeker);
            }
        });

        btnRecruiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moveToRecruiter);
            }
        });
    }
}
