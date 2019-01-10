package com.example.jobcentral;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewJobseekerSignUp extends AppCompatActivity {

    private static final String TAG = "NewRecruiterSignUp";
    FirebaseAuth mAuthJob;
    TextInputEditText inRFN, inRLN, inREmail, inRPW, inRCPW;
    CheckBox boxTNC;
    Button bSignUp;
    String txFN, txLN, txEmail, txPW, txCPW, txUN;
    String txUID;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_jobseeker);


        mAuthJob = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // EditText > Input components
        inRFN = findViewById(R.id.editJFirstName);
        inRLN = findViewById(R.id.editJLastName);
        inREmail = findViewById(R.id.editJEmail);
        inRPW = findViewById(R.id.editJPassword);
        inRCPW = findViewById(R.id.editJConfirmPass);

        // CheckBox and Button
        boxTNC = findViewById(R.id.JcheckBox);
        bSignUp = findViewById(R.id.btnJSignUp);


        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValid();
                transferToDB();
            }
        });
    }


    void checkValid()
    {
        txFN = inRFN.getText().toString();
        txLN = inRLN.getText().toString();
        txEmail = inREmail.getText().toString();
        txPW = inRPW.getText().toString();
        txCPW = inRCPW.getText().toString();
        if (!txPW.equals(txCPW)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match\nPlease Correct", Toast.LENGTH_SHORT).show();
            inRPW.setTextColor(Color.RED);
            inRCPW.setTextColor(Color.RED);
        }
        else
        {
            if (!boxTNC.isChecked()) {
                boxTNC.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please Accept Terms and Conditions", Toast.LENGTH_SHORT).show();
            }
            else {

            }
        }
    }

    void transferToDB()
    {
        mAuthJob.createUserWithEmailAndPassword(txEmail, txPW).addOnCompleteListener(NewJobseekerSignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Log.d(TAG,"createUserWithEmail: success" );
                    System.out.println("createUserWithEmail: success");
                    dbInput();
                }
                else
                {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(NewJobseekerSignUp.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    void dbInput()
    {
        FirebaseUser currentUser = mAuthJob.getCurrentUser();
        txUID = currentUser.getUid();
        System.out.println("txUID2: " + txUID);
        UserSignUp userSignUp = new UserSignUp(txFN, txLN, txEmail, txPW, "jobseeker");
        mDatabase.child("user").child(txUID).setValue(userSignUp);
        Toast.makeText(NewJobseekerSignUp.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mAuthJob.signOut();
    }

}
