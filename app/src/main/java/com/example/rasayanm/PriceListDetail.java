package com.example.rasayanm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class PriceListDetail extends AppCompatActivity {

    CircleImageView imgView;
    TextView tvTitile, tvStock, tvDiscription, tvPrice, tvLocality, tvCountryName, tvId;
    TextView tvGradeUnit, tvPriceUnit, tvStockUnit;
    Button btnContactUs, btnIntrest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list_detail);

        tvTitile = findViewById(R.id.tvTitle);
        tvStock = findViewById(R.id.tvStock);
        tvDiscription = findViewById(R.id.tvDiscription);
        tvPrice = findViewById(R.id.tvPrice);
        tvLocality = findViewById(R.id.tvLocality);
        tvCountryName = findViewById(R.id.tvCountryName);
        tvId = findViewById(R.id.tvId);
        tvPriceUnit = findViewById(R.id.tvPriceUnit);
        tvStockUnit = findViewById(R.id.tvStockUnit);
        tvGradeUnit = findViewById(R.id.tvGradeUnit);

        imgView = findViewById(R.id.imgView);
        btnContactUs = findViewById(R.id.btnContactUs);
        btnIntrest = findViewById(R.id.btnIntrested);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnIntrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
                callIntent.setData(Uri.parse("tel:9328784278"));    //this is the phone number calling
                //check permission
                //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
                //the system asks the user to grant approval.
                if (ActivityCompat.checkSelfPermission(PriceListDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(PriceListDetail.this,
                            new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                            10);
                    return;
                } else {     //have got permission
                    try {
                        startActivity(callIntent);  //call activity and make phone call
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Bundle bundle = getIntent().getExtras();

        String titile = bundle.getString("Title");
        String stock = bundle.getString("Stock");
        String gradeunit = bundle.getString("GradeUnit");
        String priceunit = bundle.getString("PriceUnit");
        String stockunit = bundle.getString("StockUnit");
        String discription = bundle.getString("Discription");
        String price = bundle.getString("Price");
        String locality = bundle.getString("Locality");
        String countryname = bundle.getString("CountryName");
        String id = bundle.getString("Id");
        String image = bundle.getString("Image");


        Glide.with(this).load(image).into(imgView);


        tvTitile.setText(titile);
        tvStock.setText(stock);
        tvDiscription.setText(discription);
        tvPrice.setText(price);
        tvLocality.setText(locality);
        tvCountryName.setText(countryname);
        tvId.setText(id);
        tvGradeUnit.setText(gradeunit);
        tvPriceUnit.setText(priceunit);
        tvStockUnit.setText(stockunit);


    }
}