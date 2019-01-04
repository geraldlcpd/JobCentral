package com.google.firebase.quickstart.database.java.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
public class User
{
    String fName, lName, email, password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fName, String lName, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

}
// [END blog_user_class]
