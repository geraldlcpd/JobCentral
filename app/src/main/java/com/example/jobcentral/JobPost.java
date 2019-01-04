package com.example.jobcentral;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
public class JobPost
{
    String company, title, country, postal, contact, req, desc, address;

    public JobPost() {
        // Default constructor required for calls to DataSnapshot.getValue(JobPost.class)
    }

    public JobPost(String company, String title, String country, String postal, String contact, String req, String desc, String address)
    {
        this.company = company;
        this.title = title;
        this.country = country;
        this.postal = postal;
        this.contact = contact;
        this.req = req;
        this.desc = desc;
        this.address = address;
    }

}
// [END blog_user_class]
