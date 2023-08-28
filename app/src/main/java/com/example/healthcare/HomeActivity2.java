package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT).show();
        //for the logout
        CardView exit=findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity2.this,LoginActivity.class));
            }
        });
        // for the Find doctor
        CardView findDoctor=findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity2.this,FindDoctorActivity.class));
            }
        });
        // for the LabTest
        CardView LabTest=findViewById(R.id.cardLabTest);
        LabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity2.this,LabTestActivity.class));
            }
        });
        // for the Order Details(history)
        CardView OrderCart=findViewById(R.id.cardOrderDetails);
        OrderCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity2.this,OrderDetailsActivity.class));
            }
        });
        //for the medicine
        CardView MedicineCart=findViewById(R.id.cardBuyMedicine);
        MedicineCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity2.this,BuyMedicineActivity.class));
            }
        });
        //for the health articles
        CardView HealthArticlesCart=findViewById(R.id.cardHealthDoctor);
        HealthArticlesCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity2.this,HealthArticlesActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }


}