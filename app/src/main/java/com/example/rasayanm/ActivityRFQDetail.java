package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rasayanm.Adapter.UserRfqAdapter;
import com.example.rasayanm.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityRFQDetail extends AppCompatActivity {

    CircleImageView imgView;
    TextView tvProductName, tvGradeUnit, tvQuantity, tvQuantityUnit, tvLocality, tvCountryName, tvId;
    Button btnContactUs, btnIntrest;
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    List<User> users;
    String TAG = "TAGER";
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_f_q_detail);

        tvProductName = findViewById(R.id.tvProductName);
        tvGradeUnit = findViewById(R.id.tvGradeUnit);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvQuantityUnit = findViewById(R.id.tvQuantityUnit);
        tvLocality = findViewById(R.id.tvLocality);
        tvCountryName = findViewById(R.id.tvCountryName);
        tvId = findViewById(R.id.tvId);
        recyclerView = findViewById(R.id.recyclerView);
        users = new ArrayList<>();
        auth = FirebaseAuth.getInstance();

        btnContactUs = findViewById(R.id.btnContactUs);
        imgView = findViewById(R.id.imgView);
        btnIntrest = findViewById(R.id.btnIntrested);
        firebaseFirestore = FirebaseFirestore.getInstance();

        SharedPreferences getShared = getSharedPreferences("userId", 0);
        String value = getShared.getString("id", "hdh");

        firebaseFirestore.collection("User").document(getIntent().getStringExtra("uploadBy")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                User user = documentSnapshot.toObject(User.class);
                Log.d(TAG, "onSuccess: " + user.toString());

                users.add(user);

                UserRfqAdapter userRfqAdapter = new UserRfqAdapter(ActivityRFQDetail.this, users);
                recyclerView.setLayoutManager(new LinearLayoutManager(ActivityRFQDetail.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(userRfqAdapter);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        btnIntrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                String productName = bundle.getString("ProductName");
                String locality = bundle.getString("Lattitude");
                String gradeunit = bundle.getString("GradeUnit");
                String countryname = bundle.getString("Longitude");
                String quantity = bundle.getString("Quantity");
                String quantityUnit = bundle.getString("QuantityUnit");
                String id = bundle.getString("Id");
                String image = bundle.getString("Image");


                String myString = "I Am Interested ( rfq)" + "\n" + "\n" +
                        "productName :-  " + productName + " " + "\n" +
                        "Gradeunit :- " + gradeunit + " " + "\n" +
                        "Quantity :- " + quantity + " " + "\n" +
                        "Discription :- " + quantityUnit + " " + "\n" +
                        "Locality :- " + locality + " " + "\n" +
                        "CountryUnit :- " + countryname + " " + "\n";


                Intent intent = new Intent(ActivityRFQDetail.this, ChatActivity.class);
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
                if (ActivityCompat.checkSelfPermission(ActivityRFQDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(ActivityRFQDetail.this,
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

        String productName = bundle.getString("ProductName");
        String locality = bundle.getString("Lattitude");
        String gradeunit = bundle.getString("GradeUnit");
        String countryname = bundle.getString("Longitude");
        String quantity = bundle.getString("Quantity");
        String quantityUnit = bundle.getString("QuantityUnit");
        String id = bundle.getString("Id");
        String image = bundle.getString("Image");


        Glide.with(this).load(image).placeholder(R.drawable.rasayanm).into(imgView);


        tvProductName.setText(productName);
        tvId.setText(id);
        tvLocality.setText(locality);
        tvCountryName.setText(countryname);
        tvQuantity.setText(quantity);
        tvQuantityUnit.setText(quantityUnit);
        tvGradeUnit.setText(gradeunit);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}
