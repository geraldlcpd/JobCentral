package com.example.jobcentral;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobPostingConfirm extends AppCompatActivity {

    static String gComp, gTitle, gCountry, gPostal, gContact, gReq, gDesc, gAddress;
    static TextView tCompany, tTitle, tCountry, tPostal, tContact, tReq, tDesc, tAddress;
    DatabaseReference mDatabase;
    Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting_confirm);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        tCompany = findViewById(R.id.tcCompany);
        tTitle = findViewById(R.id.tcJobTitle);
        tCountry = findViewById(R.id.tcCountry);
        tPostal= findViewById(R.id.tcPostal);
        tContact = findViewById(R.id.tcContact);
        tReq = findViewById(R.id.tcRequirements);
        tDesc = findViewById(R.id.tcDescription);
        tAddress = findViewById(R.id.tcAddress);
        getString();
        setTVData();
        btnConfirm = findViewById(R.id.btnConfirmJobPost);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobPost jobPost = new JobPost(gComp, gTitle, gCountry, gPostal, gContact, gReq, gDesc, gAddress);
                mDatabase.child("msg").child("jobsample").setValue(jobPost);
                // TODO : Change the message sample to real path after testing is done
            }
        });

        ;

    }
    public static void setTVData()
    {
        tCompany.setText(JobPosting.txtComp);
        tTitle.setText(JobPosting.txtTitle);
        tCountry.setText(JobPosting.txtCountry);
        tPostal.setText(JobPosting.txtPostal);
        tContact.setText(JobPosting.txtContact);
        tReq.setText(JobPosting.txtReq);
        tDesc.setText(JobPosting.txtDesc);
        tAddress.setText(JobPosting.txtAddress);
    }

    public void getString()
    {
        gComp = JobPosting.txtComp;
        gTitle = JobPosting.txtTitle;
        gCountry = JobPosting.txtCountry;
        gPostal = JobPosting.txtPostal;
        gContact = JobPosting.txtContact;
        gReq = JobPosting.txtReq;
        gDesc = JobPosting.txtDesc;
        gAddress = JobPosting.txtAddress;
    }
}
