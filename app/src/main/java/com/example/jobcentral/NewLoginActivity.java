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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewLoginActivity extends AppCompatActivity implements View.OnClickListener {


    static final String TAG = "NewLoginActivity";
    DatabaseReference mDB;
    ValueEventListener userListen;
    private FirebaseAuth mAuthLogin;
    EditText mUsername, mPassword;
    Button btnLogin;
    String txUsername, txPassword;
    String userID;
    String userKind;
    int uKindInt;
    FirebaseUser userSign;

    //DBAttrib
    String dbFN, dbLN, dbPW, dbEM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        mAuthLogin = FirebaseAuth.getInstance();

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
                checkLogin(txUsername, txPassword);
                getUID();
                getUserData();
            }
        });


    }

    void checkLogin(String email, String password)
    {
        mAuthLogin.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Log.d(TAG, "signInWithEmail:success");
                        }
                        else
                        {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(NewLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart()
    {
        super.onStart();
        userListen = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataGet dataGet = dataSnapshot.getValue(DataGet.class);
                userKind = dataGet.kind;
                dbFN = dataGet.firstName;
                dbLN = dataGet.lastName;
                dbEM = dataGet.email;
                dbPW = dataGet.password;
                System.out.println("DG_log " + userKind);
                Toast.makeText(NewLoginActivity.this,"UserKind Retrieve>> " + userKind, Toast.LENGTH_SHORT).show();
                checkUserKind();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost: onCancelled", databaseError.toException());
                System.out.println("loadPost: onCancelled " + databaseError.toException());

            }
        };


    }

    void getUID()
    {
        FirebaseUser login = mAuthLogin.getCurrentUser();
        userID = login.getUid();
    }
    public void getUserData()
    {

        mDB = FirebaseDatabase.getInstance().getReference().child("user").child(userID);
        mDB.addValueEventListener(userListen);

    }

    void checkUserKind()
    {
        if(userKind.equals("recruiter"))
        {
            System.out.println("MoveToRecruiter Intent REQ_01 >> uKind = " + userKind);
            Toast.makeText(NewLoginActivity.this, "Move to R", Toast.LENGTH_SHORT).show();
            Toast.makeText(NewLoginActivity.this, dbFN, Toast.LENGTH_SHORT).show();
            Toast.makeText(NewLoginActivity.this, dbLN, Toast.LENGTH_SHORT).show();
            Toast.makeText(NewLoginActivity.this, dbEM, Toast.LENGTH_SHORT).show();
            //TODO: Move to Recruiter Home page
        }
        else
        {
            System.out.println("MoveToJS Intent REQ_02");
            Toast.makeText(NewLoginActivity.this, "Move to J", Toast.LENGTH_SHORT).show();
            //TODO : Move to JobSeeker Home Page
        }
    }
}

