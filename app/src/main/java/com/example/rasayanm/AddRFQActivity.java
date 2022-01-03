package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rasayanm.Model.RFQ;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddRFQActivity extends AppCompatActivity {

    Spinner spinGrade,spinUnit;
    Button btnRegister;
    TextView tvEmail,tvMobileNumber;
    EditText edtDiscription;
    TextView tvlatitude, tvlongitude;
    EditText edtProductName,edtQuantity;
    String grade[]={"grade","Recover","Fresh","Industrial","Analitycal","Pharmapass","Other"};
    String unit[]={"ML","Kg","ltr","Ton","Gm"};
    FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseAuth auth;
    String TAG="TAGER";
    FirebaseFirestore firebaseFirestore;
    String gradeUnit,quantityUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_r_f_q);


        edtProductName=findViewById(R.id.edtProductName);
        edtQuantity=findViewById(R.id.edtQuantity);
        spinGrade=findViewById(R.id.spinGrade);
        spinUnit=findViewById(R.id.spinUnit);
        btnRegister=findViewById(R.id.btnRegister);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        tvlatitude = findViewById(R.id.tvlatitude);
        tvlongitude = findViewById(R.id.tvlongitude);
        edtDiscription=findViewById(R.id.tvDiscription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(AddRFQActivity.this);

        if (ActivityCompat.checkSelfPermission(AddRFQActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(AddRFQActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String productName = edtProductName.getText().toString();
                final String quantity=edtQuantity.getText().toString();
                final String grade = spinGrade.toString();
                final String unit = spinUnit.toString();
                final String latitude = tvlatitude.getText().toString();
                final String longitude = tvlongitude.getText().toString();
                final String discription = edtDiscription.getText().toString();


                if(! productName.trim().isEmpty() && ! quantity.trim().isEmpty() && !grade.trim().isEmpty() && !unit.trim().isEmpty() && !discription.trim().isEmpty()){
                    final String value=auth.getCurrentUser().getUid();
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss", Locale.getDefault());
                    String id = dateFormat.format(date);

                    RFQ rfq=new RFQ(productName,quantity,latitude,longitude,gradeUnit,quantityUnit,id,auth.getUid(),discription);
                    DocumentReference documentReference=firebaseFirestore.collection("Rfq").document(productName);
                    documentReference.set(rfq).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent=new Intent(AddRFQActivity.this,RFQActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: "+e.getMessage());
                        }
                    });
                }
                else{
                    Toast.makeText(AddRFQActivity.this, "Please Filed Data", Toast.LENGTH_SHORT).show();
                }


            }
        });

        ArrayAdapter arrayAdapter=new ArrayAdapter(AddRFQActivity.this,R.layout.support_simple_spinner_dropdown_item,grade);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinGrade.setAdapter(arrayAdapter);

        ArrayAdapter arrayAdapter1=new ArrayAdapter(AddRFQActivity.this,R.layout.support_simple_spinner_dropdown_item,unit);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinUnit.setAdapter(arrayAdapter1);



        spinGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gradeUnit=grade[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantityUnit=unit[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(AddRFQActivity.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        tvlatitude.setText(Html.fromHtml("" + addressList.get(0).getLocality()));
                        tvlongitude.setText(Html.fromHtml("" + addressList.get(0).getCountryName()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        
    }
}