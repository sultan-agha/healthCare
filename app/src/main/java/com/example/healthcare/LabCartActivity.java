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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class LabCartActivity extends AppCompatActivity {
Button Cdbtn,Ctbtn,Cbackbtn,Ccheckoutbtn;
TextView totalcost;
ListView lv;
SimpleAdapter sa;
HashMap<String,String> item;
ArrayList list;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private String[][] packages ={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labcart);
        Cdbtn=findViewById(R.id.buttonCartDate);
        Ctbtn=findViewById(R.id.buttonCartTime);
        Cbackbtn=findViewById(R.id.buttonBackCart);
        Ccheckoutbtn=findViewById(R.id.buttonCheckoutCart);
        totalcost=findViewById(R.id.textViewCartCost);
        lv=findViewById(R.id.ListViewCartPackage);


        //date
        initDatePicker();
        Cdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        //time
        initTimePicker();
        Ctbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        //Back button
        Cbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabCartActivity.this,HomeActivity2.class));
            }
        });
        //for checkout button(means u agree and u will pay)
        //send email or something to be done later
        Ccheckoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LabCartActivity.this,CheckoutLabActivity.class);
                it.putExtra("price",totalcost.getText());
                it.putExtra("date",Cdbtn.getText());
                it.putExtra("time",Ctbtn.getText());
                startActivity(it);
            }
        });
        //to get the data and put them in the list view
        SharedPreferences sharedPreferences=getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        float totalAmount=0;
        ArrayList dbData=db.getCartData(username,"lab");

        packages=new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"$";
            totalAmount+=Float.parseFloat(strData[1]);
        }
        totalcost.setText("Total Cost : "+totalAmount);
        // to put in the list view
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lv=findViewById(R.id.ListViewCartPackage);
        lv.setAdapter(sa);
    }




    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Cdbtn.setText(dayOfMonth+"/"+month+"/"+year);
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
                Ctbtn.setText(hourOfDay+"/"+minute);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs =cal.get(Calendar.HOUR);
        int mins =cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}