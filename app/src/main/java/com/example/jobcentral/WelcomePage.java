package com.example.jobcentral;

import android.content.Intent;
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


    Button btnLogin, btnSRecruit, btnSJobSeeker;
    Intent moveToLogin, moveToJobseeker, moveToRecruiter;


    FirebaseDatabase dbJobCentral = FirebaseDatabase.getInstance();
    FirebaseDatabase dbTest = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        FirebaseDatabase dbTesting = FirebaseDatabase.getInstance();
        DatabaseReference refTest = dbTesting.getReference("message/u3");

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


        btnSRecruit = findViewById(R.id.btnRecruit);
        btnSJobSeeker = findViewById(R.id.btnJobSeek);
        btnLogin = findViewById(R.id.loginBtn);
        moveToLogin = new Intent(this, NewLoginActivity.class);
        moveToJobseeker = new Intent(this, NewJobseekerSignUp.class);
        moveToRecruiter = new Intent(this, NewRecruiterSignUp.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moveToLogin);
            }
        });

        btnSRecruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moveToRecruiter);
            }
        });

        btnSJobSeeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moveToJobseeker);
            }
        });
    }

}
