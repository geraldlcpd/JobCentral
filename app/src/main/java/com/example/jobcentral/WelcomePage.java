package com.example.jobcentral;

import android.content.Intent;
import android.icu.text.MessagePattern;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomePage extends AppCompatActivity {


    Button btnLogin, btnJobseeker, btnRecruiter;
    Intent moveToLogin, moveToJobseeker, moveToRecruiter;


    String [] userTests = {"Name", "Username", "Password"};
    String [] uNames = {"John", "johnsmith", "john123"};
    FirebaseDatabase dbJobCentral = FirebaseDatabase.getInstance();
    FirebaseDatabase dbTest = FirebaseDatabase.getInstance();
    DatabaseReference dbUser = dbJobCentral.getReference("USER");
    DatabaseReference dbCV = dbJobCentral.getReference("CV");
    DatabaseReference dbJob = dbJobCentral.getReference("JOB");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);


        // Sample code
        DatabaseReference dbTestVal;
        String firstPath = "message/newUser";
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

        DatabaseReference dbGet = dbTest.getReference("message/newUser");
        dbGet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                snap.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
