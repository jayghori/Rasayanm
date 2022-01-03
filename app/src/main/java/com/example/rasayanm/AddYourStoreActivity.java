package com.example.rasayanm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class AddYourStoreActivity extends AppCompatActivity {

    RadioButton rbManufacture,rbTrader,rbReseller,rbDealer,rbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_store);

        rbManufacture=findViewById(R.id.rbManufacture);
        rbTrader=findViewById(R.id.rbTrader);
        rbReseller=findViewById(R.id.rbReseller);
        rbDealer=findViewById(R.id.rbDealer);
        rbService=findViewById(R.id.rbService);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        rbManufacture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    rbTrader.setChecked(false);
//                    rbReseller.setChecked(false);
//                    rbDealer.setChecked(false);
//                    rbService.setChecked(false);
//                }
//            }
//        });
//
//        rbTrader.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    rbManufacture.setChecked(false);
//                    rbReseller.setChecked(false);
//                    rbDealer.setChecked(false);
//                    rbService.setChecked(false);
//                }
//            }
//        });
//
//        rbReseller.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    rbTrader.setChecked(false);
//                    rbManufacture.setChecked(false);
//                    rbDealer.setChecked(false);
//                    rbService.setChecked(false);
//                }
//            }
//        });
//
//        rbDealer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    rbTrader.setChecked(false);
//                    rbReseller.setChecked(false);
//                    rbManufacture.setChecked(false);
//                    rbService.setChecked(false);
//                }
//            }
//        });
//
//        rbService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    rbTrader.setChecked(false);
//                    rbReseller.setChecked(false);
//                    rbDealer.setChecked(false);
//                    rbManufacture.setChecked(false);
//                }
//            }
//        });
    }
}