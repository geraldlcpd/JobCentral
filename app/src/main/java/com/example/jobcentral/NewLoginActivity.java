package com.example.jobcentral;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.ui.phone.CountryListSpinner;
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

public class NewLoginActivity extends AppCompatActivity implements View.OnClickListener {


    static final String TAG = "NewLoginActivity";
    DatabaseReference mDB;
    FirebaseAuth mAuth;
    EditText mEmail, mPassword;
    Button btnLogin;
    String txEmail, txPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        mDB = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.editLEmail);
        mPassword = findViewById(R.id.editLPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txEmail = mEmail.getText().toString();
                txPassword = mPassword.getText().toString();
                checkLogin(txEmail, txPassword);


            }
        });


    }

    public void checkLogin(final String email, final String password)
    {
        // final String dEmail, dPass;
        final FirebaseDatabase dbTesting = FirebaseDatabase.getInstance();
        final DatabaseReference refTest = dbTesting.getReference("message/u3");
        String getData = refTest.child("message/u3").toString();
        System.out.println(getData);
        refTest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // DataGet newProgram = dataSnapshot.getValue(DataGet.class);
                DatabaseReference newProgram = refTest.getRoot();
                FirebaseDatabase newDB = refTest.getDatabase();
                System.out.println(newProgram);
                //String dEmail = newProgram.uName;
                //String dPass = newProgram.pWord;

                //System.out.println("Email : " + dEmail);
                //System.out.println("Password : " + dPass);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onClick(View v) {

    }
}

