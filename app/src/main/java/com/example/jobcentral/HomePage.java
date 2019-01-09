package com.example.jobcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    private Button cBillboard, cCVBox, cChat, cQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        cBillboard =(Button) findViewById(R.id.btnBod);
        cCVBox = (Button) findViewById(R.id.btnCV);
        cChat =(Button) findViewById(R.id.btnCV);
        cQuit = (Button) findViewById(R.id.btnChat);

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
                Signout();
            }
        });


    }

    private void Signout() {
        Intent intent = new Intent(this, WelcomePage.class);
        Signout();
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
