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

import com.example.rasayanm.Adapter.NoticeBoardAdapter;
import com.example.rasayanm.Model.NoticeBoard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardActivity extends AppCompatActivity {

    Button btnAdd;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String TAG = "TAGER";
    EditText edtSearch;
    List<NoticeBoard> noticeBoardList;
    NoticeBoardAdapter noticeBoardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtSearch = findViewById(R.id.edtSearch);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        noticeBoardList = new ArrayList<>();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                Log.d("TAGG", "onTextChanged: " + s);

                if (noticeBoardList.size() > 0) {
                    if (text.equals("")) {
                        noticeBoardAdapter.setNoticeBoardList(noticeBoardList);
                    } else {
                        List<NoticeBoard> matchedList = new ArrayList<>();
                        for (NoticeBoard noticeBoard : noticeBoardList) {
                            if (noticeBoard.getCategeryUnit().toLowerCase().contains(text))
                                matchedList.add(noticeBoard);
                            else if (noticeBoard.getLink().toLowerCase().contains(text))
                                matchedList.add(noticeBoard);
                            else if (noticeBoard.getDiscription().toLowerCase().contains(text))
                                matchedList.add(noticeBoard);
                            else if (noticeBoard.getTitle().toLowerCase().contains(text))
                                matchedList.add(noticeBoard);
                            else if (noticeBoard.getId().toLowerCase().contains(text))
                                matchedList.add(noticeBoard);

                        }
                        noticeBoardAdapter.setNoticeBoardList(matchedList);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        firebaseFirestore.collection("NoticeBoard").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        noticeBoardList.add(snapshot.toObject(NoticeBoard.class));
                    }
                    noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, noticeBoardList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(NoticeBoardActivity.this));
                    recyclerView.setAdapter(noticeBoardAdapter);
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

                Intent intent = new Intent(NoticeBoardActivity.this, AddNoticeBoardActivity.class);
                startActivity(intent);
            }
        });
    }
}