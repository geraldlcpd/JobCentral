package com.example.jobcentral;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Contact page is for user to check the FAQ and contact with JobCentral Team.
 */


public class Contact extends AppCompatActivity {


    //send email button
    EditText Et_message;
    ImageButton linkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Et_message = (EditText)findViewById(R.id.EtMessage);
        linkButton = (ImageButton)findViewById(R.id.linktoGmail);
    }

    // button to send message by E-mail

    private void linktoGoogle(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/ServiceLogin/signinchooser?flowName=GlifWebSignIn&flowEntry=ServiceLogin"));
        startActivity(browserIntent);
    }
}
