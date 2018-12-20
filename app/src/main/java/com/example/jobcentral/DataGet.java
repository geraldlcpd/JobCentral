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

    public DataGet(String uID, String uN)
    {
        final FirebaseDatabase dbTesting = FirebaseDatabase.getInstance();
        DatabaseReference refTest = dbTesting.getReference("message/newUser");
        refTest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataGet newProg = dataSnapshot.getValue(DataGet.class);
                System.out.println(newProg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}



