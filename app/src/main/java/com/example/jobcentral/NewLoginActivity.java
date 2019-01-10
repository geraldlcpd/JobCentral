package com.example.jobcentral;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    static FirebaseAuth mAuthLogin;
    TextInputEditText mEmail, mPassword;
    Button btnLogin;
    String txUsername, txPassword;
    String userID;
    String userKind;
    Intent moveR, moveJ;
    FirebaseUser userSign;
    boolean isLoggedin, isBlank;
    //DBAttrib
    String dbFN, dbLN, dbPW, dbEM;
    TextInputLayout mE, mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        isLoggedin = false;

        mAuthLogin = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.editLUsername);
        mPassword = findViewById(R.id.editLPassword);
        btnLogin = findViewById(R.id.btnLogin);
        mE = findViewById(R.id.editTIE);
        mP = findViewById(R.id.editTIP);

        txUsername = mEmail.getText().toString();
        txPassword = mPassword.getText().toString();

        moveR = new Intent(this, HomePage.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txUsername = mEmail.getText().toString();
                txPassword = mPassword.getText().toString();

                try {
                    checkLogin(txUsername, txPassword);
                }
                catch (IllegalArgumentException a)
                {
                    setBlank();
                }
                getUID();
                if (isLoggedin)
                    getUserData();

            }
        });


    }

    void setBlank()
    {
        mEmail.setError("Blank");
        mPassword.setError("Blank");

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
                            isLoggedin = true;
                        }
                        else
                        {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(NewLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            mEmail.setError("Check email");
                            mPassword.setError("Check Password");
                            isLoggedin = false;
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
        isLoggedin = false;
        userListen = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataGet dataGet = dataSnapshot.getValue(DataGet.class);
                userKind = dataGet.kind;
                dbFN = dataGet.firstName;
                dbLN = dataGet.lastName;
                dbEM = dataGet.email;
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
        System.out.println("uID GET >> " + userID);
    }

    void getUserData()
    {
        mDB = FirebaseDatabase.getInstance().getReference().child("user").child(userID);
        mDB.addValueEventListener(userListen);
    }

    void checkUserKind()
    {
        AlertDialog.Builder builderR = new AlertDialog.Builder(NewLoginActivity.this, android.R.style.Theme_Material_Dialog);
        builderR.setTitle("Login Success").setMessage("Welcome, " + dbFN);
        builderR.setCancelable(false);
        builderR.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HomePage.type = "r";
                startActivity(moveR);
                dialog.dismiss();
            }
        });

        AlertDialog.Builder builderJ = new AlertDialog.Builder(NewLoginActivity.this, android.R.style.Theme_Material_Dialog);
        builderJ.setTitle("Login Success").setMessage("Welcome, " + dbFN);
        builderJ.setCancelable(false);
        builderJ.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HomePage.type = "j";
                startActivity(moveR);
                dialog.dismiss();
            }
        });
        if(userKind.equals("recruiter"))
        {
            System.out.println("MoveToRecruiter Intent REQ_01 >> uKind = " + userKind);
            AlertDialog alertR = builderR.create();
            alertR.show();
            //TODO: Move to Recruiter Home page
        }
        else
        {
            System.out.println("MoveToJS Intent REQ_02");
            AlertDialog alertJ = builderJ.create();
            alertJ.show();
            Toast.makeText(NewLoginActivity.this, "Move to J", Toast.LENGTH_SHORT).show();
            //TODO : Move to JobSeeker Home Page
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }

    public static void reqLogOut()
    {

    }
}

