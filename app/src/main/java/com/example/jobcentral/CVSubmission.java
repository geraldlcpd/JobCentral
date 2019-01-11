package com.example.jobcentral;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class CVSubmission extends AppCompatActivity {

    FirebaseDatabase mDB;
    DatabaseReference mPostRef;
    EditText mFN, mLN, mEmail, mPhone, mPref, mCV, mDetail;
    Button btnSubmit;
    String txFirst, txLast, txEmail, txPhone, txPref, txCV, txDetail;
    String cvID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvsubmission);

        loadComponent();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveText();
                cvID = generateCVID();
                transferDB();
            }
        });
    }

    private void transferDB()
    {
        mDB = FirebaseDatabase.getInstance();
        CVDBPost cvdbPost = new CVDBPost(txFirst, txLast ,txEmail, txPhone, txPref, txCV, txDetail);
        mPostRef = mDB.getReference().child("CV").child(cvID);
        mPostRef.setValue(cvdbPost);
    }
    private void retrieveText()
    {
        txFirst = mFN.getText().toString();
        txLast = mLN.getText().toString();
        txEmail = mEmail.getText().toString();
        txPhone = mPhone.getText().toString();
        txPref = mPref.getText().toString();
        txCV = mCV.getText().toString();
        txDetail = mDetail.getText().toString();
    }
    private void loadComponent() {
        mFN = findViewById(R.id.cvEditFN);
        mLN = findViewById(R.id.cvEditLN);
        mEmail = findViewById(R.id.cvEditEmail);
        mPhone = findViewById(R.id.cvEditPhone);
        mPref = findViewById(R.id.cvEditPrefJob);
        mCV = findViewById(R.id.cvEditAddInfo);
        mDetail = findViewById(R.id.cvEditDetails);
        btnSubmit = findViewById(R.id.btnCVSubmit);
    }

    String generateCVID() {
        int leftLimit = 65; // letter '0'
        int rightLimit = 90; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = "CV_ID-" + buffer.toString();

        return generatedString;
    }
}
