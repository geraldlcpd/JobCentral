package com.example.jobcentral;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CVSubmission extends AppCompatActivity {

    FirebaseDatabase mDB;
    DatabaseReference mPostRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvsubmission);
    }
}
