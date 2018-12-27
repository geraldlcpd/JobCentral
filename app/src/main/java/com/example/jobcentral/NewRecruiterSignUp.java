package com.example.jobcentral;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewRecruiterSignUp extends AppCompatActivity {

    EditText inRFN, inRLN, inREmail, inRPW, inRCPW;
    CheckBox boxTNC;
    Button bSignUp;
    String txFN, txLN, txEmail, txPW, txCPW, txUN;
    String [] userDataInput = {"FN", "LN", "email", "pw"};
    DatabaseReference userDB = FirebaseDatabase.getInstance().getReference("USER");
    long childCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_recruiter);

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

        checkValid();

    }

    void transferToDB()
    {
        String uID;

        userDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                childCount = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        uID = "USR0" + childCount;
        String uName = userDataInput[0].toLowerCase()+userDataInput[1].toLowerCase();
        FirebaseDatabase uDBA = FirebaseDatabase.getInstance();
        DatabaseReference dbUFN = uDBA.getReference("USER/" + uName + "/1FN");
        DatabaseReference dbULN = uDBA.getReference("USER/" + uName + "/2LN");
        DatabaseReference dbUEM= uDBA.getReference("USER/" + uName + "/3EM");
        DatabaseReference dbUPW = uDBA.getReference("USER/" + uName + "/4PW");


        dbUFN.setValue(userDataInput[0]);
        dbULN.setValue(userDataInput[1]);
        dbUEM.setValue(userDataInput[2]);
        dbUPW.setValue(userDataInput[3]);
        Toast.makeText(getApplicationContext(), "DB Executed", Toast.LENGTH_SHORT).show();
    }
}

