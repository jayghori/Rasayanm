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
import com.example.rasayanm.Model.QuickOffer;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityOfferDetail extends AppCompatActivity {

    CircleImageView imgView;
    TextView tvTitile, tvStock, tvDiscription, tvPrice, tvLocality, tvCountryName, tvId, tvGradeUnit, tvPriceUnit, tvStockUnit;
    Button btnContactUs, btnIntrest;
    FirebaseFirestore firebaseFirestore;
    List<QuickOffer> quickOfferList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvTitile = findViewById(R.id.tvTitle);
        tvStock = findViewById(R.id.tvStock);
        tvDiscription = findViewById(R.id.tvDiscription);
        tvPrice = findViewById(R.id.tvPrice);
        tvLocality = findViewById(R.id.tvLocality);
        tvCountryName = findViewById(R.id.tvCountryName);
        tvId = findViewById(R.id.tvId);
        tvGradeUnit = findViewById(R.id.tvGradeUnit);
        tvStockUnit = findViewById(R.id.tvStockUnit);
        tvPriceUnit = findViewById(R.id.tvPriceUnit);
        btnContactUs = findViewById(R.id.btnContactUs);
        imgView = findViewById(R.id.imgView);
        btnIntrest = findViewById(R.id.btnIntrested);
        firebaseFirestore = FirebaseFirestore.getInstance();
        quickOfferList = new ArrayList<>();

        btnIntrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                String myString = "I Am Interested (Quick offer)" + "\n" + "\n" +
                        "Tittle :-  " + titile + " " + "\n"
                        + "Stock :- " + stock + " " +
                        "Gradeunit :- " + gradeunit + " " + "\n"
                        + "Priceunit :- " + priceunit + " " + "\n" +
                        "Stockunit :- " + stockunit + "," + "\n" +
                        "Discription :- " + discription + "," +
                        "Price :- " + price + " " + "\n" +
                        "Locality :- " + locality + " " + "\n"
                        + "Countryname :- " + countryname + " ";


                Intent intent = new Intent(ActivityOfferDetail.this, ChatActivity.class);
                intent.putExtra("string", myString);
                startActivity(intent);
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
                if (ActivityCompat.checkSelfPermission(ActivityOfferDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(ActivityOfferDetail.this,
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

//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("message/rfc822");
//                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
//                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
//                try {
//                    startActivity(Intent.createChooser(i, "Send mail..."));
//                } catch (android.content.ActivityNotFoundException ex) {
//
//                    Toast.makeText(ActivityOfferDetail.this, "There are no email clients installed", Toast.LENGTH_SHORT).show();                }
//
//            }


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
        tvStockUnit.setText(stockunit);
        tvPriceUnit.setText(priceunit);


    }

//    private void Call(View view) {
//
//
//            Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
//            callIntent.setData(Uri.parse("tel:0800000000"));    //this is the phone number calling
//            //check permission
//            //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
//            //the system asks the user to grant approval.
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                //request permission from user if the app hasn't got the required permission
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
//                        10);
//                return;
//            }else {     //have got permission
//                try{
//                    startActivity(callIntent);  //call activity and make phone call
//                }
//                catch (android.content.ActivityNotFoundException ex){
//                    Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//    }
}