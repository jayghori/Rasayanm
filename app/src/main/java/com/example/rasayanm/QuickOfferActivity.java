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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rasayanm.Adapter.QuickOfferAdapter;
import com.example.rasayanm.Adapter.RFQAdapter;
import com.example.rasayanm.Model.QuickOffer;
import com.example.rasayanm.Model.RFQ;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuickOfferActivity extends AppCompatActivity {

    Button btnAdd;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    List<QuickOffer> offerList;
    String TAG = "TAGER";
    EditText edtSearch;
    QuickOfferAdapter quickOfferAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_offer);

        edtSearch = findViewById(R.id.edtSearch);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        offerList = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString().toLowerCase();
                Log.d("TAGG", "onTextChanged: " + s);

                if (offerList.size() > 0) {
                    if (text.equals("")) {
                        quickOfferAdapter.setOfferList(offerList);
                    } else {
                        List<QuickOffer> matchedList = new ArrayList<>();
                        for (QuickOffer offer : offerList) {
                            if (offer.getTitle().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getCountryName().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getLocality().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getDiscription().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getId().toLowerCase().contains(text))
                                matchedList.add(offer);
                            else if (offer.getPrice().toLowerCase().contains(text))
                                matchedList.add(offer);

                        }
                        quickOfferAdapter.setOfferList(matchedList);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        firebaseFirestore.collection("QuickOffer").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        offerList.add(snapshot.toObject(QuickOffer.class));
                    }
                    quickOfferAdapter = new QuickOfferAdapter(offerList, QuickOfferActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QuickOfferActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(quickOfferAdapter);
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
                Intent intent = new Intent(QuickOfferActivity.this, AddQuickOfferActivity.class);
                startActivity(intent);

            }
        });

    }
}