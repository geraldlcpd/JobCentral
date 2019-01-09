package com.example.jobcentral;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START blog_user_class]
@IgnoreExtraProperties
public class UserSignUp
{
    String firstName, lastName, email, password;
    String kind;
    public Map<String, Boolean> jobs = new HashMap<>();

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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("email", email);
        result.put("password", password);
        result.put("kind", kind);

        return result;
    }

}
// [END blog_user_class]
