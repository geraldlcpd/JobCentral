package com.example.jobcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Home page is shown the functions for user to to go another page.
 */


public class HomePage extends AppCompatActivity {

    private Button cCVList, cCVBox, cJChat, cJSignOut;
    private Button cPostJob, cBoard, cRSignOut, cRChat;
    Intent mToJobPost;
    static String type;

    // R: btnBod, btnJobPost, btnChat, btnSignOut

    // !!Two layout for two users

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(type.equals("j")) {
            setContentView(R.layout.activity_home_page_jobseeker);
            loadJobSeeker();
        }
        else {
            setContentView(R.layout.activity_home_page_recruiter);
            loadRecruiter();
        }

    }

    private void loadRecruiter()
    {
        cCVList = findViewById(R.id.btnCVList);
        cPostJob = findViewById(R.id.btnJobPost);
        cRSignOut = findViewById(R.id.btnRSignOut);
        cRChat = findViewById(R.id.btnRChat);

        cCVList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBillBoard();
            }
        });
        cPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToJobPost();
            }
        });
        cRChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
            }
        });
        cRSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }


    private void loadJobSeeker()
    {
        cBoard = findViewById(R.id.btnBoard);
        cCVBox = (Button) findViewById(R.id.btnCVBox);
        cJChat =(Button) findViewById(R.id.btnJChat);
        cJSignOut = (Button) findViewById(R.id.btnJSignOut);

        cJChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
            }
        });
        cJSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        cCVBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCVBOX();
            }
        });
        cBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBillBoard();
            }
        });

    }

    //button move to JobPosting page
    private void moveToJobPost()
    {
        mToJobPost = new Intent(HomePage.this, JobPosting.class);
        startActivity(mToJobPost);

    }

    //button move to New Login page
    private void signOut() {
        Intent intent = new Intent(HomePage.this, NewLoginActivity.class);
        WelcomePage.reqSignOut();
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }

    //button move to Contact page

    private void openChat() {
        Intent intent = new Intent(HomePage.this, Contact.class);
        startActivity(intent);
    }

    //button move to CV submition page
    private void openCVBOX() {
        Intent intent = new Intent(this, CVSubmission.class);
        startActivity(intent);
    }


    //button to joblisting
    private void openBillBoard() {
        Intent intent = new Intent(this, JobListing.class);
        startActivity(intent);
    }
}
