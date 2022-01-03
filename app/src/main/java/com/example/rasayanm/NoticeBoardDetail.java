package com.example.rasayanm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class

NoticeBoardDetail extends AppCompatActivity {

    CircleImageView imageView;
    TextView tvTitle, tvSpinCategory, tvDiscription, tvLink, tvId;
    Button btnContactUs, btnIntrested;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board_detail);

        imageView = findViewById(R.id.imgView);
        tvTitle = findViewById(R.id.tvTitle);
        tvSpinCategory = findViewById(R.id.tvSpinCategory);
        tvDiscription = findViewById(R.id.tvDiscription);
        tvLink = findViewById(R.id.tvLink);
        tvId = findViewById(R.id.tvId);
        btnContactUs = findViewById(R.id.btnContactUs);
        btnIntrested = findViewById(R.id.btnIntrested);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnIntrested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();

                String titile = bundle.getString("Title");
                String discription = bundle.getString("Discription");
                String link = bundle.getString("Link");
                String spinUnit = bundle.getString("SpinUnit");
                String image = bundle.getString("Image");
                String id = bundle.getString("Id");

                String myString = "I Am Interested (Notice Board)" + "\n" + "\n" +
                        "Tittle :-  " + titile + " " + "\n"
                        + "Discription :- " + discription + " " +
                        "Link :- " + link + " " + "\n"
                        + "SpinUnit :- " + spinUnit + " " + "\n" ;


                Intent intent = new Intent(NoticeBoardDetail.this, ChatActivity.class);
                intent.putExtra("string", myString);
                startActivity(intent);
            }
        });


        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
                callIntent.setData(Uri.parse("tel:9328784278"));    //this is the phone number calling
                //check permission
                //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
                //the system asks the user to grant approval.
                if (ActivityCompat.checkSelfPermission(NoticeBoardDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(NoticeBoardDetail.this,
                            new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                            10);
                    return;
                } else {     //have got permission
                    try {
                        startActivity(callIntent);  //call activity and make phone call
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();

        String titile = bundle.getString("Title");
        String discription = bundle.getString("Discription");
        String link = bundle.getString("Link");
        String spinUnit = bundle.getString("SpinUnit");
        String image = bundle.getString("Image");
        String id = bundle.getString("Id");

        Glide.with(this).load(image).into(imageView);

        tvTitle.setText(titile);
        tvSpinCategory.setText(spinUnit);
        tvDiscription.setText(discription);
        tvLink.setText(link);
        tvId.setText(id);

    }
}