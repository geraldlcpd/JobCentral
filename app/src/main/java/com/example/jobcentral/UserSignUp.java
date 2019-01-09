package com.example.jobcentral;

// [START blog_user_class]
public class UserSignUp
{
    String firstName, lastName, email, password, kind;

    public UserSignUp() {
        // Default constructor required for calls to DataSnapshot.getValue(JobPost.class)
    }

    public UserSignUp(String firstName, String lastName, String email, String password, String kind) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.kind = kind;
    }

}
// [END blog_user_class]
