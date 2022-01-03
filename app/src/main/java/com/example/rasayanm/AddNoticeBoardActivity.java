package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rasayanm.Model.NoticeBoard;
import com.example.rasayanm.Model.QuickOffer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNoticeBoardActivity extends AppCompatActivity {

    Spinner spinCategory;
    EditText edtTitle,edtDiscription,edtLink;
    TextView tvUrl;
    Button btnSelect,btnSubmit;
    List<NoticeBoard> noticeBoardList;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String TAG = "TAGER";
    String categeryUnit;

    Intent dataFile;
    boolean fileSelected;
    StorageReference storageReference;

    String category[]={"Gov.Notification","Chemicals","Pharma","Import-Export","Packaging","Share-Market","News","Hogistics","R & D","Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice_board);

        spinCategory=findViewById(R.id.spinCategory);
        edtTitle=findViewById(R.id.edtTitle);
        edtDiscription=findViewById(R.id.edtDiscuption);
        edtLink=findViewById(R.id.edtLink);
        btnSelect=findViewById(R.id.btnSelect);
        btnSubmit=findViewById(R.id.btnSubmit);
        noticeBoardList=new ArrayList<>();
        tvUrl=findViewById(R.id.tvUrl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter arrayAdapter=new ArrayAdapter(AddNoticeBoardActivity.this,R.layout.support_simple_spinner_dropdown_item,category);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinCategory.setAdapter(arrayAdapter);

        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categeryUnit=category[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                final String discription = edtDiscription.getText().toString();
                final String link = edtLink.getText().toString();


                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss", Locale.getDefault());
                String id = dateFormat.format(date);


                if(!title.trim().isEmpty() && !discription.trim().isEmpty() && !link.trim().isEmpty()){
                    FirebaseStorage.getInstance().getReference().child("NoticeBoard").child(id).putFile(dataFile.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    NoticeBoard noticeBoard = new NoticeBoard(id, title, discription,link,uri.toString(),categeryUnit);

                                    DocumentReference documentReference = firebaseFirestore.collection("NoticeBoard").document(title);
                                    documentReference.set(noticeBoard).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent = new Intent(AddNoticeBoardActivity.this, NoticeBoardActivity.class);
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
                    Toast.makeText(AddNoticeBoardActivity.this, "Please Field Data", Toast.LENGTH_SHORT).show();
                }



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
}