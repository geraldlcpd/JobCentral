package com.example.jobcentral;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class GetJobListing {
    // DBParameter :
    // company, title, country, postal, contact, require, description, address
    // address, company, contact, country, description, postal, require, title


    public String address, company, contact, country, description, postal, require, title;
    public Map<String, Boolean> jobs = new HashMap<>();

    GetJobListing() {

    }

    GetJobListing(String address, String company, String contact, String country, String description, String postal, String require, String title) {
        this.address = address;
        this.company = company;
        this.contact = contact;
        this.country = country;
        this.description = description;
        this.postal = postal;
        this.require = require;
        this.title = title;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("address", address);
        result.put("company", company);
        result.put("contact", contact);
        result.put("country", country);
        result.put("description", description);
        result.put("postal", postal);
        result.put("require", require);
        result.put("title", title);

        return result;
    }
}