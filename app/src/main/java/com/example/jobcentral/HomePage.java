package com.example.jobcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    private Button cBillboard, cCVBox, cChat, cQuit;
    static String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(type.equals("j"))
            setContentView(R.layout.activity_home_page_jobseeker);
        else
            setContentView(R.layout.activity_home_page_recruiter);

        cBillboard =(Button) findViewById(R.id.btnBod);
        cCVBox = (Button) findViewById(R.id.btnCV);
        cChat =(Button) findViewById(R.id.btnChat);
        cQuit = (Button) findViewById(R.id.btnQuit);

        cBillboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBillBoard();
            }
        });
        cCVBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCVBOX();
            }
        });
        cChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
            }
        });
        cQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


    }

    private void signOut() {
        Intent intent = new Intent(HomePage.this, NewLoginActivity.class);
        WelcomePage.reqSignOut();
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }

    private void openChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        openChat();
    }

    private void openCVBOX() {
        Intent intent = new Intent(this, CVSubmission.class);
        openCVBOX();
    }

    private void openBillBoard() {
        Intent intent = new Intent(this, JobListing.class);
        openBillBoard();
    }
}
