package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rasayanm.Adapter.RFQAdapter;
import com.example.rasayanm.Model.QuickOffer;
import com.example.rasayanm.Model.RFQ;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RFQActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    Button btnAdd;
    String TAG = "TAGER";
    List<RFQ> rfqList;
    RecyclerView recyclerView;
    EditText edtSearch;
    RFQAdapter rfqAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_f_q);

        btnAdd = findViewById(R.id.btnAdd);
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        rfqList = new ArrayList<>();
        edtSearch = findViewById(R.id.edtSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                Log.d("TAGG", "onTextChanged: " + s);

                if (rfqList.size() > 0) {
                    if (text.equals("")) {
                        rfqAdapter.setRFQList(rfqList);
                    } else {
                        List<RFQ> matchedList = new ArrayList<>();
                        for (RFQ rfq : rfqList) {
                            if (rfq.getRfqId().toLowerCase().contains(text))
                                matchedList.add(rfq);
                            else if (rfq.getGradeUnit().toLowerCase().contains(text))
                                matchedList.add(rfq);
                            else if (rfq.getLatitude().toLowerCase().contains(text))
                                matchedList.add(rfq);
                            else if (rfq.getLongitude().toLowerCase().contains(text))
                                matchedList.add(rfq);
                            else if (rfq.getQuantity().toLowerCase().contains(text))
                                matchedList.add(rfq);
                            else if (rfq.getQuantityUnit().toLowerCase().contains(text))
                                matchedList.add(rfq);
                            else if (rfq.getProductName().toLowerCase().contains(text))
                                matchedList.add(rfq);

                        }
                        rfqAdapter.setRFQList(matchedList);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        firebaseFirestore.collection("Rfq").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        rfqList.add(snapshot.toObject(RFQ.class));
                    }
                    rfqAdapter = new RFQAdapter(rfqList, RFQActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(RFQActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(rfqAdapter);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RFQActivity.this, AddRFQActivity.class);
                startActivity(intent);
            }
        });


    }


}