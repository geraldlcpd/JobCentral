package com.example.jobcentral;

public class CVDBPost {

    String firstName, lastName, eMail, phoneNum, jobPref, cv, detail;

    public CVDBPost()
    {

    }

    public CVDBPost(String firstName, String lastName, String eMail, String phoneNum, String jobPref, String cv , String detail)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNum = phoneNum;
        this.jobPref = jobPref;
        this.cv = cv;
        this.detail = detail;
    }
}
