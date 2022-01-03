package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.rasayanm.Adapter.ChatAdapter;
import com.example.rasayanm.Model.Chat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    String TAG = "chat_actv_tager";
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setSendChatButton();
//        toolbar=findViewById(R.id.toolbar);
        imageView2=findViewById(R.id.imageView2);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ChatActivity.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

//        Bundle bundle = getIntent().getExtras();
//        String st1r= bundle.getString("string");
//        if (str!= null) {

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        getSupportActionBar().hide();


        if (b != null) {
            String str = (String) b.get("string");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
            String id = sdf.format(new Date());

            ArrayList<String> person = new ArrayList<>();
            person.add(FirebaseAuth.getInstance().getUid());
            person.add("admin");

            Chat chat = new Chat(id, person, FirebaseAuth.getInstance().getUid(), str);
            FirebaseFirestore.getInstance().collection("Chat").document(id).set(chat);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.nevigation);
//        bottomNavigationView.setSelectedItemId(R.id.chat);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);

                    case R.id.chat:
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getChatMessages();
    }

    @Override
    protected void onPause() {
        super.onPause();
        registration.remove();
    }


    private void setSendChatButton() {
        EditText edtMsg = findViewById(R.id.edtMsg);
        ImageView btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                String id = sdf.format(new Date());

                ArrayList<String> person = new ArrayList<>();
                person.add(FirebaseAuth.getInstance().getUid());
                person.add("admin");
                Chat chat = new Chat(id, person, FirebaseAuth.getInstance().getUid(), edtMsg.getText().toString());
                if (!edtMsg.getText().toString().isEmpty())
                    FirebaseFirestore.getInstance().collection("Chat").document(id).set(chat);

                edtMsg.setText("");
            }
        });
    }

    ListenerRegistration registration;

    private void getChatMessages() {
        List<String> a = new ArrayList<>();
        a.add("admin");

        registration = FirebaseFirestore.getInstance().collection("Chat").orderBy("id", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                Log.d(TAG, "onChange: ");
                if (value != null) {
                    Log.d(TAG, "onEvent: " + value.toString());
                    List<Chat> chatList = new ArrayList<>();
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Chat chat = snapshot.toObject(Chat.class);
                        if (chat.getUserToken().equals(FirebaseAuth.getInstance().getUid()))
                            chatList.add(chat);
                    }
                    intRecyclerView(chatList);
                } else {
                    Log.d("AsD", "onEvent: null ");
                }
            }
        });

//        FirebaseFirestore.getInstance().collection("Chat").whereEqualTo("senderId", FirebaseAuth.getInstance().getUid()).whereEqualTo("receiverId", FirebaseAuth.getInstance().getUid()).orderBy("id").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                if (!queryDocumentSnapshots.isEmpty()) {
//                    List<Chat> chatList = new ArrayList<>();
//                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
//                        chatList.add(snapshot.toObject(Chat.class));
//                    }
//                    intRecyclerView(chatList);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
    }

    private void intRecyclerView(List<Chat> chatList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setAdapter(new ChatAdapter(this, chatList));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(false);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        finish();
//        return super.onSupportNavigateUp();
//    }
}