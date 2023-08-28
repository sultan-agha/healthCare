package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
TextView tv;
Button btnD,btnT,btnB,btnBack;
private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv=findViewById(R.id.textViewApp);
        ed1=findViewById(R.id.editTextAppFullName);
        ed2=findViewById(R.id.editTextAppAddress);
        ed3=findViewById(R.id.editTextAppContact);
        ed4=findViewById(R.id.editTextAppFees);
        btnD=findViewById(R.id.buttonDate);
        btnT=findViewById(R.id.buttonTime);
        btnB=findViewById(R.id.buttonBookAppointment);
        btnBack=findViewById(R.id.BackApp);
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees: "+fees +"$");
        //date
        initDatePicker();
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        //time
        initTimePicker();
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        //Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });
        //Booking button
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //i need to send the email
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences=getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                if(db.getAppointmentExist(username,title+"=>"+fullname,address,contact,btnD.getText().toString(),btnT.getText().toString())==1){
                    Toast.makeText(BookAppointmentActivity.this, "Appointment alreadyn booked", Toast.LENGTH_SHORT).show();
                }else{
                    db.addOrder(username,title+"=>"+fullname,address,contact,0,btnD.getText().toString(),btnT.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(BookAppointmentActivity.this, "Your appointment is done successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity2.class));
                }
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                btnD.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int month =cal.get(Calendar.MONTH);
        int day =cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+864000000);
    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                btnT.setText(hourOfDay+"/"+minute);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs =cal.get(Calendar.HOUR);
        int mins =cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}