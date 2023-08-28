package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailActivity extends AppCompatActivity {
    TextView tvPack,tvTC;
    EditText edDetails;
    Button Bbtn,Cbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);
        tvPack=findViewById(R.id.textViewBMDpackage);
        tvTC=findViewById(R.id.textViewBMTotalCost);
        edDetails=findViewById(R.id.editTextTextBMD);
        Bbtn=findViewById(R.id.buttonBMDBack);
        Cbtn=findViewById(R.id.buttonBMDgotocart);

        edDetails.setKeyListener(null);
        Intent intent=getIntent();
        tvPack.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTC.setText("Total Cost : "+intent.getStringExtra("text3")+"$");

        //for back button
        Bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailActivity.this,LabTestActivity.class));
            }
        });
        //for Add to cart
        Cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPack.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(BuyMedicineDetailActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetailActivity.this, "Product inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));

                }
            }
        });
    }
}