package com.example.jobcentral;

import android.content.Intent;
import android.icu.text.MessagePattern;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomePage extends AppCompatActivity {


    Button btnLogin, btnJobseeker, btnRecruiter, btnGo;
    Intent moveToLogin, moveToJobseeker, moveToRecruiter, moveToGo;


    String [] userTests = {"1Username", "2Password"};
    String [] uNames = {"johnsmith", "john123"};
    FirebaseDatabase dbJobCentral = FirebaseDatabase.getInstance();
    FirebaseDatabase dbTest = FirebaseDatabase.getInstance();
    DatabaseReference dbUser = dbJobCentral.getReference("USER");
    DatabaseReference dbCV = dbJobCentral.getReference("CV");
    DatabaseReference dbJob = dbJobCentral.getReference("JOB");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        FirebaseDatabase dbTesting = FirebaseDatabase.getInstance();
        DatabaseReference refTest = dbTesting.getReference("message/u3");
        String getData = refTest.child("1Username").toString();
        System.out.println("getData Variable: " + getData);

        // Sample code
        DatabaseReference dbTestVal;
        String firstPath = "message/u3";
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

        // End of Sample data submission

        dbUser.setValue("");
        dbCV.setValue("");
        dbJob.setValue("");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationRef = rootRef.child("message").child("u3").child("1Username");
        DatabaseReference dbGet = dbTest.getReference("message/u3/1Username");
        ValueEventListener vEL = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {

                String key = snap.getValue(String.class);
                System.out.println("dsKeyN Value: " + key);
                /*for (DataSnapshot ds : snap.getChildren())
                {
                    String key = ds.getValue(String.class);
                    Log.d("TAG", key);
                    System.out.println("dsKeyN Value: " + key);
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        locationRef.addListenerForSingleValueEvent(vEL);


        btnJobseeker = findViewById(R.id.btnJobseeker);
        btnRecruiter = findViewById(R.id.btnRecruiter);
        btnLogin = findViewById(R.id.loginBtn);
        btnGo = findViewById(R.id.btn_go);

        moveToLogin = new Intent(this, NewLoginActivity.class);
        moveToJobseeker = new Intent(this, SignUpJobseeker.class);
        moveToRecruiter = new Intent(this, SignUpRecruiter.class);
        moveToGo = new Intent(this,JobListing.class);

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

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moveToGo);
            }
        });
    }



}
