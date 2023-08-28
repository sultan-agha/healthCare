package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckoutLabActivity extends AppCompatActivity {
EditText edname,edaddress,edcontact,edpin;
Button BtnBooking,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_lab);
        edname=findViewById(R.id.editTextLBFullName);
        edaddress=findViewById(R.id.editTextLBAddress);
        edcontact=findViewById(R.id.editTextLBConatct);
        edpin=findViewById(R.id.editTextLBPincode);
        BtnBooking=findViewById(R.id.buttonLTBBookAppointment);
        btnBack=findViewById(R.id.BackLT);

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");
        //for back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(CheckoutLabActivity.this,HomeActivity2.class));
            }
        });

        BtnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edname.length() == 0 || edaddress.length() == 0 || edcontact.length() == 0 || edpin.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    Database db=new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences=getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
                String username =sharedPreferences.getString("username","").toString();
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpin.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"Lab");
                db.removeCart(username,"lab");
                Toast.makeText(CheckoutLabActivity.this, "Your Booking is done successfully and sent", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CheckoutLabActivity.this,HomeActivity2.class));
            }}
        });
    }
}