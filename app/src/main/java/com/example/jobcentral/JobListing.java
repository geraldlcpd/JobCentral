package com.example.jobcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class JobListing extends AppCompatActivity {

    ListView job_listing;
    Button bNavChat, bNavBoard, bNavCVBox;
    Intent mChat, mBoard, mCV;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);
        asnNavBarBtns();

        job_listing = findViewById(R.id.search_job);



        //Job Array list

        ArrayList<String> arrayJob = new ArrayList<>();
        arrayJob.addAll(Arrays.asList(getResources().getStringArray(R.array.my_jobs)));

        adapter = new ArrayAdapter<String>(
                JobListing.this,
                android.R.layout.simple_list_item_1,
                arrayJob
        );
        job_listing.setAdapter(adapter);

        // Job listing can click and go to job detail page

        job_listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent jobintent = new Intent(view.getContext(),JobDetails.class);
                    startActivityForResult(jobintent,0);
                }
                if(position ==1){
                    Intent jobintent = new Intent(view.getContext(),JobDetails.class);
                    startActivityForResult(jobintent,1);
                }
                if (position ==2){
                    Intent jobintent = new Intent(view.getContext(),JobDetails.class);
                    startActivityForResult(jobintent,2);
                }
                if (position ==3){
                    Intent jobintent = new Intent(view.getContext(),JobDetails.class);
                    startActivityForResult(jobintent,3);
                }
                if (position ==4){
                    Intent jobintent = new Intent(view.getContext(),JobDetails.class);
                    startActivityForResult(jobintent,4);
                }
            }
        });

    }


    // Menu bar for searching
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_job);
        SearchView searchView=(SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    void asnNavBarBtns()
    {
        mChat = new Intent(this, ChatActivity.class);
        mBoard = new Intent(this, JobListing.class);
        mCV = new Intent(this, CVSubmission.class);

        bNavChat = findViewById(R.id.bNavChat);
        bNavBoard = findViewById(R.id.bNavBillboard);
        bNavCVBox = findViewById(R.id.bNavCVBox);

        bNavChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mChat);
            }
        });

        bNavCVBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mCV);
            }
        });

        bNavBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mBoard);
            }
        });
    }

}
