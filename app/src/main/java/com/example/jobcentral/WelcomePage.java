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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WelcomePage extends AppCompatActivity implements View.OnClickListener {


    Button btnLogin, btnJobseeker, btnRecruiter,btnGo;
    Intent moveToLogin, moveToJobseeker, moveToRecruiter;

    private static final String TAG = "WelcomePage";
    public static final String EXTRA_POST_KEY = "post_key";
    String mPostKey;

    static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase dbJobCentral = FirebaseDatabase.getInstance();
    FirebaseDatabase dbTest = FirebaseDatabase.getInstance();
    DatabaseReference mPostRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);



        mPostRef = FirebaseDatabase.getInstance().getReference().child("jobtest").child("jobid1");

        FirebaseDatabase dbTesting = FirebaseDatabase.getInstance();
        DatabaseReference refTest = dbTesting.getReference("message/u3");
        String getData = refTest.child("1Username").toString();
        System.out.println("setTVData Variable: " + getData);

        btnJobseeker = findViewById(R.id.btnJobSeek);
        btnRecruiter = findViewById(R.id.btnRecruit);
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

    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GetJobListing getJobListing = dataSnapshot.getValue(GetJobListing.class);
                System.out.println("J:LA423 " + getJobListing.address);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mPostRef.addValueEventListener(postListener);
    }

    @Override
    public void onClick(View v) {

    }

    public static void reqSignOut()
    {
        mAuth.signOut();
    }
}
