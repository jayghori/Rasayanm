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
import android.net.Uri;
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

import com.example.rasayanm.Model.PriceList;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddPriceListActivity extends AppCompatActivity {

    Spinner spinQuality, spinGrade, spinQuality1;
    String grade[] = {"grade", "Recover", "Fresh", "Industrial", "Analitycal", "Pharmapass", "Other"};
    String quality[] = {"ML", "Kg", "ltr", "Ton", "Gm"};
    String quality1[] = {"ML", "Kg", "ltr", "Ton", "Gm"};
    FusedLocationProviderClient fusedLocationProviderClient;
    EditText edtTitle, edtStock, edtDiscription, edtPrice;
    TextView tvLocality, tvCountryname, tvUrl;
    Button btnSubmit, btnSelect;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String TAG = "TAGER";

    Intent dataFile;
    String stockUnit, priceUnit, gradeUnit;
    boolean fileSelected;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_price_list);

        edtTitle = findViewById(R.id.edtTitle);
        edtStock = findViewById(R.id.edtStock);
        edtDiscription = findViewById(R.id.edtDiscuption);
        edtPrice = findViewById(R.id.edtPrice);
        spinGrade = findViewById(R.id.spinGrade);
        spinQuality = findViewById(R.id.spinQuality);
        spinQuality1 = findViewById(R.id.spinQyality1);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvLocality = findViewById(R.id.tvLocality);
        tvCountryname = findViewById(R.id.tvCountryName);
        btnSelect = findViewById(R.id.btnSelect);
        tvUrl = findViewById(R.id.tvUrl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(AddPriceListActivity.this);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = edtTitle.getText().toString();
                final String stock = edtStock.getText().toString();
                final String discription = edtDiscription.getText().toString();
                final String price = edtPrice.getText().toString();
                final String locality = tvLocality.getText().toString();
                final String countryName = tvCountryname.getText().toString();

                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-YYYY-dd/hh:mm:ss", Locale.getDefault());
                String id = dateFormat.format(date);


                if(!title.trim().isEmpty() && !stock.trim().isEmpty() && !discription.trim().isEmpty() && !price.trim().isEmpty()){
                    FirebaseStorage.getInstance().getReference().child("PriceList").child(id).putFile(dataFile.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    PriceList priceList = new PriceList(id, title, stock, discription, locality, countryName, price, uri.toString(),stockUnit,priceUnit,gradeUnit);

                                    DocumentReference documentReference = firebaseFirestore.collection("PriceList").document(title);
                                    documentReference.set(priceList).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(AddPriceListActivity.this, "added", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(AddPriceListActivity.this, PriceListActivity.class);
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure: " + e.getMessage());
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        }
                    });

                }
                else {
                    Toast.makeText(AddPriceListActivity.this, "Please Field Data", Toast.LENGTH_SHORT).show();
                }


            }
        });

        if (ActivityCompat.checkSelfPermission(AddPriceListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(AddPriceListActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(AddPriceListActivity.this, R.layout.support_simple_spinner_dropdown_item, grade);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinGrade.setAdapter(arrayAdapter);

        ArrayAdapter arrayAdapter1 = new ArrayAdapter(AddPriceListActivity.this, R.layout.support_simple_spinner_dropdown_item, quality);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinQuality.setAdapter(arrayAdapter1);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(AddPriceListActivity.this, R.layout.support_simple_spinner_dropdown_item, quality1);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinQuality1.setAdapter(arrayAdapter2);


        spinGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gradeUnit=grade[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stockUnit = quality[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinQuality1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priceUnit = quality[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void selectFile() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("*/*");
        startActivityForResult(i, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Toast.makeText(this, "Succesfully Selected", Toast.LENGTH_SHORT).show();
            dataFile = data;
            fileSelected = true;
            Uri selectedFileURI = data.getData();
            tvUrl.setText(selectedFileURI.getLastPathSegment());
        }

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
                    Geocoder geocoder = new Geocoder(AddPriceListActivity.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1


                        );
                        tvLocality.setText(Html.fromHtml("" + addressList.get(0).getLocality()));
                        tvCountryname.setText(Html.fromHtml("" + addressList.get(0).getCountryName()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}