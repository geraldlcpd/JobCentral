package com.example.jobcentral;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewLoginActivity extends AppCompatActivity implements View.OnClickListener {


    static final String TAG = "NewLoginActivity";
    DatabaseReference mDB;
    FirebaseAuth mAuth;
    EditText mUsername, mPassword;
    Button btnLogin;
    String txUsername, txPassword;
    String getUname, getPW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        mDB = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mUsername = findViewById(R.id.editLUsername);
        mPassword = findViewById(R.id.editLPassword);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txUsername = mUsername.getText().toString();
                txPassword = mPassword.getText().toString();
                checkLogin();
            }
        });


    }

    public void checkLogin()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationRef = rootRef.child("USER").child(txUsername).child("4PW");
        ValueEventListener vEL = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {

                String key = snap.getValue(String.class);
                getPW = key;
                System.out.println("uPW: " + key);
                // Toast.makeText(getApplicationContext(), "uName : " + txUsername + "\nPW: " + getPW, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        locationRef.addListenerForSingleValueEvent(vEL);
        try {
            boolean checkPW = (txPassword.equals(getPW));
            if (checkPW) {
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            }
            else if (txPassword.length() != 0)
                Toast.makeText(getApplicationContext(),"Password Incorrect", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"Password Field is Empty", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException a)
        {
            Toast.makeText(getApplicationContext(), "Password Field is Empty", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {

    }
}

