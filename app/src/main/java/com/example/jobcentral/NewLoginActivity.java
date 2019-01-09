package com.example.jobcentral;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

        txUsername = mUsername.getText().toString();
        txPassword = mPassword.getText().toString();


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

        mAuth.signInWithEmailAndPassword(txUsername, txPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    //TODO : Create a Home page for the user to navigate after sucessfully log in
                }
                else
                {
                    Log.w(TAG, "signInWithEmail: Fail ", task.getException());
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}

