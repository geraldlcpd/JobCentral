package com.example.jobcentral;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
/**
 * Data for users' sign up infos
 */


@IgnoreExtraProperties
public class DataGet
{

    public String firstName;
    public String lastName;
    public String email;
    public String kind;
    public String password;
    public Map<String, Boolean> user = new HashMap<>();

    public DataGet()
    {

    }
    public DataGet(String email, String firstName, String kind, String lastName, String password)
    {
        this.email = email;
        this.firstName = firstName;
        this.kind = kind;
        this.lastName = lastName;
        this.kind = kind;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("email", email);
        result.put("firstName", firstName);
        result.put("kind", kind);
        result.put("lastName", lastName);
        result.put("password", password);

        return result;
    }
}



