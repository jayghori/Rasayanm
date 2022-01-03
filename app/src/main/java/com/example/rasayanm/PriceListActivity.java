package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rasayanm.Adapter.PriceListAdapter;
import com.example.rasayanm.Model.PriceList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PriceListActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    List<PriceList> priceList;
    String TAG = "TAGER";
    EditText edtSearch;
    PriceListAdapter priceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list);

        edtSearch = findViewById(R.id.edtSearch);
        recyclerView = findViewById(R.id.recyclerView);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        priceList = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                Log.d("TAGG", "onTextChanged: " + s);

                if (priceList.size() > 0) {
                    if (text.equals("")) {
                        priceListAdapter.setPriceList(priceList);
                    } else {
                        List<PriceList> matchedList = new ArrayList<>();
                        for (PriceList offer : priceList) {
                            if (offer.getCountryName().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getDiscription().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getGradeUnit().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getId().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getLocality().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getPrice().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getPriceUnit().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getStock().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getStockUnit().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getTitle().toLowerCase().contains(text))
                                matchedList.add(offer);
                        }
                        priceListAdapter.setPriceList(matchedList);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        firebaseFirestore.collection("PriceList").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        priceList.add(snapshot.toObject(PriceList.class));
                    }
                    priceListAdapter = new PriceListAdapter(PriceListActivity.this, priceList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PriceListActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(priceListAdapter);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });

    }
}