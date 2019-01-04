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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewRecruiterSignUp extends AppCompatActivity {

    private static final String TAG = "NewRecruiterSignUp";
    private FirebaseAuth mAuth;
    TextInputEditText inRFN, inRLN, inREmail, inRPW, inRCPW;
    CheckBox boxTNC;
    Button bSignUp;
    String txFN, txLN, txEmail, txPW, txCPW, txUN, txUID;
    String [] userDataInput = {"FN", "LN", "email", "pw"};
    DatabaseReference userDB = FirebaseDatabase.getInstance().getReference("USER");
    long childCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_recruiter);

        mAuth = FirebaseAuth.getInstance();

        // EditText > Input components
        inRFN = findViewById(R.id.editRFirstName);
        inRLN = findViewById(R.id.editRLastName);
        inREmail = findViewById(R.id.editREmail);
        inRPW = findViewById(R.id.editRPassword);
        inRCPW = findViewById(R.id.editRConfirmPass);

        // CheckBox and Button
        boxTNC = findViewById(R.id.rCheckBox);
        bSignUp = findViewById(R.id.btnRSignUp);


        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferToText();
            }
        });
    }

    private boolean validateForm()
    {
        boolean valid = true;
        String email = inREmail.getText().toString();


        return valid;
    }
    void checkValid()
    {
        if (!txPW.equals(txCPW)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match\nPlease Correct", Toast.LENGTH_SHORT).show();
            inRPW.setTextColor(Color.RED);
            inRCPW.setTextColor(Color.RED);
        }
        else
        {
            if (!boxTNC.isChecked())
            {
                boxTNC.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please Accept Terms and Conditions", Toast.LENGTH_SHORT).show();
            }
            else
                entryToArray();
        };


    }

    void entryToArray()
    {
        userDataInput[0] = txFN;
        userDataInput[1] = txLN;
        userDataInput[2] = txEmail;
        userDataInput[3] = txPW;
        transferToDB();
    }

    void transferToText() {
        // Get text from EditText
        txFN = inRFN.getText().toString();
        txLN = inRLN.getText().toString();
        txEmail = inREmail.getText().toString();
        txPW = inRPW.getText().toString();
        txCPW = inRCPW.getText().toString();

        mAuth.createUserWithEmailAndPassword(txEmail, txPW).addOnCompleteListener(NewRecruiterSignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Log.d(TAG,"createUserWithEmail: success" );
                    System.out.println("createUserWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    txUID = user.getUid();
                }
                else
                {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(NewRecruiterSignUp.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        checkValid();

    }

    void transferToDB()
    {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        UserSignUp userSignUp = new UserSignUp(txFN, txLN, txEmail, txPW);
        mDatabase.child("message").child(txUID).setValue(userSignUp);
    }
}

