package com.example.jobcentral;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataGet
{

    public String userID;
    public String uName;
    public String pWord;

    public DataGet()
    {

    }
    public DataGet(String uN, String uP)
    {
        uName = uN;
        pWord = uP;
    }
}



