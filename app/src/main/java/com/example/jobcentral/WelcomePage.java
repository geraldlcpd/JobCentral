package com.example.jobcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomePage extends AppCompatActivity {


    Button btnLogin, btnJobseeker, btnRecruiter;
    Intent moveToLogin, moveToJobseeker, moveToRecruiter;


    String [] userTests = {"User 1", "User 2", "User 3"};
    String [] uNames = {"Name 1", "Name 2", "Name 3"};
    FirebaseDatabase dbJobCentral = FirebaseDatabase.getInstance();
    FirebaseDatabase dbTest = FirebaseDatabase.getInstance();
    DatabaseReference dbUser = dbJobCentral.getReference("USER");
    DatabaseReference dbCV = dbJobCentral.getReference("CV");
    DatabaseReference dbJob = dbJobCentral.getReference("JOB");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        DatabaseReference dbTestVal;
        String firstPath = "message";
        String secondPath, userName;
        String finalPath = "";
        for (int i = 0 ; i < userTests.length; i ++)
        {

            secondPath = userTests[i];
            userName = uNames[i];
            finalPath = firstPath + "/" + secondPath;
            dbTestVal = dbTest.getReference(finalPath);
            dbTestVal.setValue(userName);

        }


        dbUser.setValue("");
        dbCV.setValue("");
        dbJob.setValue("");



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
