package com.example.rasayanm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DocumentActivity extends AppCompatActivity {


    CardView cvYouTube,cvWhatapp,cvFacebook,cvInstagram,cvTwitter,cvLinkedIn,cvTeligram,cvQuora,cvPintrest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cvYouTube=findViewById(R.id.cvYoutube);
        cvWhatapp=findViewById(R.id.cvWhatapp);
        cvFacebook=findViewById(R.id.cvFacebook);
        cvInstagram=findViewById(R.id.cvInstagram);
        cvLinkedIn=findViewById(R.id.cvLinkedIn);
        cvTeligram=findViewById(R.id.cvTeligram);
        cvQuora=findViewById(R.id.cvQuotra);
        cvPintrest=findViewById(R.id.cvPintrest);
        cvTwitter=findViewById(R.id.cvTwitter);

        cvYouTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,YouTubeActivity.class);
                startActivity(intent);
            }
        });
        cvWhatapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,WhatappActivity.class);
                startActivity(intent);
            }
        });
        cvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,FacebookActivity.class);
                startActivity(intent);
            }
        });
        cvInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,InstagramActivity.class);
                startActivity(intent);
            }
        });
        cvTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,TwitterActivity.class);
                startActivity(intent);
            }
        });
        cvLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,LinkedInActivity.class);
                startActivity(intent);
            }
        });
        cvTeligram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,TeligramActivity.class);
                startActivity(intent);
            }
        });
        cvQuora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,QuoraActivity.class);
                startActivity(intent);
            }
        });
        cvPintrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DocumentActivity.this,PintrestActivity.class);
                startActivity(intent);
            }
        });








    }
}