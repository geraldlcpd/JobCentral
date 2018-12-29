package com.example.jobcentral;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class JobDetails extends AppCompatActivity {


    private TextView
            tooolbar_text,
            tool_job_name,
            txt_job_description,
            txt_job_post_date,
            txt_job_end_date,
            txt_establishment_name;

    private ImageView img_banner;

    private AppBarLayout appBarLayout;

    private String STR_PAGE_TITLE ="", STR_BANNER_URL="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
    }
}
