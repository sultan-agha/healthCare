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

public class CheckoutMedicineActivity extends AppCompatActivity {
    EditText edname,edaddress,edcontact,edpin;
    Button BtnBooking,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_medicine);
        edname=findViewById(R.id.editTextMBFullName);
        edaddress=findViewById(R.id.editTextMBAddress);
        edcontact=findViewById(R.id.editTextMBConatct);
        edpin=findViewById(R.id.editTextMBPincode);
        BtnBooking=findViewById(R.id.buttonMBBookAppointment);
        btnBack=findViewById(R.id.BackBM);

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        //for back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CheckoutMedicineActivity.this,HomeActivity2.class));
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
                    db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpin.getText().toString()),date.toString()," ",Float.parseFloat(price[1].toString()),"medicine");
                    db.removeCart(username,"medicine");
                    Toast.makeText(CheckoutMedicineActivity.this, "Your Booking is done successfully and sent", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CheckoutMedicineActivity.this,HomeActivity2.class));
                }}
        });
    }
}