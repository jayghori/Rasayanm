package com.example.rasayanm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    CardView cvRasayanm, cvFindy, cvFoundation, cvJob, cvNoticeBoard, cvQuickOffer, cvPriceList, cvRFQ, cvDocument;
    ViewFlipper viewFlipper;
    int images[] = {R.drawable.picture22, R.drawable.picture1, R.drawable.picture33, R.drawable.picture44, R.drawable.picture55};
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = findViewById(R.id.viewFlipper);
        for (int image : images) {
            flipperiew(image);
        }

        auth = FirebaseAuth.getInstance();
        cvRasayanm = findViewById(R.id.cvRasayanm);
        cvFindy = findViewById(R.id.cvFindy);
        cvFoundation = findViewById(R.id.cvFoundation);
        cvJob = findViewById(R.id.cvJob);
        cvNoticeBoard = findViewById(R.id.cvNoticeBoard);
        cvQuickOffer = findViewById(R.id.cvQuickOffer);
        cvRFQ = findViewById(R.id.cvRFQ);
        cvPriceList = findViewById(R.id.cvPriceList);
        cvDocument = findViewById(R.id.cvDocument);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nevigation);
//        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.chat:
                        Intent i = new Intent(getBaseContext(), ChatActivity.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(0, 0);

                }

                return false;
            }
        });

        cvRasayanm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RasayanmActivity.class);
                startActivity(intent);
            }
        });

        cvFindy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindyActivity.class);
                startActivity(intent);
            }
        });

        cvJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JobActivity.class);
                startActivity(intent);
            }

        });

        cvNoticeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeBoardActivity.class);
                startActivity(intent);
            }
        });

        cvQuickOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuickOfferActivity.class);
                startActivity(intent);
            }
        });

        cvPriceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PriceListActivity.class);
                startActivity(intent);
            }
        });

        cvFoundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoundationActivity.class);
                startActivity(intent);
            }
        });

        cvRFQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RFQActivity.class);
                startActivity(intent);
            }
        });

        cvDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DocumentActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.my_menu_sign, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (R.id.homeAsUp == item.getItemId()) {
            onBackPressed();
        }

        if (item.getItemId() == R.id.signout) {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Sign-Out", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.share) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch (Exception e) {
                //e.toString();
            }
        }
        if (item.getItemId() == R.id.contactUs) {

            Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
            callIntent.setData(Uri.parse("tel:0800000000"));    //this is the phone number calling
            //check permission
            //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
            //the system asks the user to grant approval.
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //request permission from user if the app hasn't got the required permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                        10);

            } else {     //have got permission
                try {
                    startActivity(callIntent);  //call activity and make phone call
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void flipperiew(int image) {

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this
        ).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                }).setNegativeButton("No", null).show();
    }

}