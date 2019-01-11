package com.example.jobcentral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * JobListing page for business owner to submit a job offer
 */


public class JobPosting extends AppCompatActivity {


    Button btnSubmit;
    Intent mToConfirm;
    static String txtComp, txtTitle, txtDesc, txtCountry, txtAddress, txtPostal, txtContact, txtReq;
    EditText edComp, edTitle, edDesc, edCountry, edAddress, edPostal, edContact, edReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting);

        mToConfirm = new Intent(this, JobPostingConfirm.class);

        // EditText Components
        edComp = findViewById(R.id.jpEditCompany);
        edTitle = findViewById(R.id.jpEditTitle);
        edDesc = findViewById(R.id.jpEditDetails);
        edCountry = findViewById(R.id.jpEditCountry);
        edAddress = findViewById(R.id.jpEditAddress);
        edPostal = findViewById(R.id.jpEditCode);
        edContact = findViewById(R.id.jpEditPhone);
        edReq = findViewById(R.id.jpEditReqs);

        btnSubmit = findViewById(R.id.btnJobPostApply);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    textExtract();
                }

                catch (NullPointerException a)
                {

                }
                startActivity(mToConfirm);
            }
        });
    }

    protected void textExtract()
    {
        txtComp = edComp.getText().toString();
        txtTitle = edTitle.getText().toString();
        txtDesc = edDesc.getText().toString();
        txtCountry = edCountry.getText().toString();
        txtAddress = edAddress.getText().toString();
        txtPostal = edPostal.getText().toString();
        txtContact = edContact.getText().toString();
        txtReq = edReq.getText().toString();
    }
}
