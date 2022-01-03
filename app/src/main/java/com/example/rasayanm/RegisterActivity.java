package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rasayanm.Model.QuickOffer;
import com.example.rasayanm.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    LinearLayout linearLayoutLogin;
    Button btnRegister,btnSelect;
    String userId;
    TextView tvUploadImageUrl;

    EditText edtEmail,edtPassword,edtRePassword,edtPhoneNumber,edtName,edtAddress,edtGST,edtPanNumber;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    Intent dataFile;
    String stockUnit, priceUnit, gradeUnit;
    boolean fileSelected;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvUploadImageUrl=findViewById(R.id.tvUploadImageUrl);
        linearLayoutLogin=findViewById(R.id.linearLayoutLogin);
        btnRegister=findViewById(R.id.btnRegister);
        btnSelect=findViewById(R.id.btnSelect);
        edtGST=findViewById(R.id.edtGST);
        edtPanNumber=findViewById(R.id.edtPanNumber);
        edtName=findViewById(R.id.edtName);
        edtPhoneNumber=findViewById(R.id.edtPhoneNumber);
        edtAddress=findViewById(R.id.edtAddress);
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        edtRePassword=findViewById(R.id.edtReEnterPassword);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storageReference = FirebaseStorage.getInstance().getReference();

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        SelectFile();

            }
        });
        linearLayoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=edtEmail.getText().toString();
                final String pass=edtPassword.getText().toString();
                String cpass=edtRePassword.getText().toString();
                final String address=edtAddress.getText().toString();
                final String phonenumber=edtPhoneNumber.getText().toString();
                final String gstnumber=edtGST.getText().toString();
                final String pannumber=edtPanNumber.getText().toString();
                final String name=edtName.getText().toString();

                if(!email.trim().isEmpty() && !pass.trim().isEmpty() && !cpass.trim().isEmpty() && !address.trim().isEmpty() && !phonenumber.trim().isEmpty()
                        && !gstnumber.trim().isEmpty() && !pannumber.trim().isEmpty() && !name.trim().isEmpty()){

                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss", Locale.getDefault());
                    String id = dateFormat.format(date);

                    FirebaseStorage.getInstance().getReference().child("User").child(id).putFile(dataFile.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    if(!TextUtils.isEmpty(name)  &&  !TextUtils.isEmpty(pass)  &&  !TextUtils.isEmpty(cpass))
                                    {
                                        if(pass.equals(cpass))
                                        {
                                            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        userId = auth.getCurrentUser().getUid();

                                                        auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if(task.isSuccessful())
                                                                {

//                                                                SharedPreferences sharedPreferences=getSharedPreferences("userId",0);
//                                                                SharedPreferences.Editor editor=sharedPreferences.edit();
//                                                                editor.putString("id",userId);
//                                                                editor.apply();
                                                                    User user=new User(name,email,phonenumber,address,userId,gstnumber,pannumber,uri.toString());

                                                                    firebaseFirestore.collection("User").document(auth.getCurrentUser().getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            auth.signOut();
                                                                            Toast.makeText(RegisterActivity.this, "please check ur Email", Toast.LENGTH_SHORT).show();
                                                                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                                                            startActivity(intent);
                                                                        }
                                                                    });
                                                                }
                                                                else
                                                                {

                                                                    Toast.makeText(RegisterActivity.this, "Kindly enter valid Email  or link verify ", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(RegisterActivity.this, "Somthing Is Wrong", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                        else
                                        {
                                            Toast.makeText(RegisterActivity.this, "Please enter right password", Toast.LENGTH_SHORT).show();
                                        }
                                    }else
                                    {
                                        Toast.makeText(RegisterActivity.this, "Please enter Data!!", Toast.LENGTH_SHORT).show();
                                    }

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
                else
                {
                    Toast.makeText(RegisterActivity.this, "Please Filed Data", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    private void SelectFile() {

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
                tvUploadImageUrl.setText(selectedFileURI.getLastPathSegment());
            }

        }

    }
