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

public class MedicineCartActivity extends AppCompatActivity {
    Button Cdbtn,Cbackbtn,Ccheckoutbtn;
    TextView totalcost;
    ListView lv;
    SimpleAdapter sa;
    HashMap<String,String> item;
    ArrayList list;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private String[][] medicines ={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_cart);
        Cdbtn=findViewById(R.id.buttonMCartDate);
        Cbackbtn=findViewById(R.id.buttonMBackCart);
        Ccheckoutbtn=findViewById(R.id.buttonMCheckoutCart);
        totalcost=findViewById(R.id.textViewMCartCost);
        lv=findViewById(R.id.ListViewCartMedicine);


        //date
        initDatePicker();
        Cdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        //Back button
        Cbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicineCartActivity.this,HomeActivity2.class));
            }
        });
        //for checkout button(means u agree and u will pay)
        //send email or something to be done later
        Ccheckoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MedicineCartActivity.this,CheckoutMedicineActivity.class);
                it.putExtra("price",totalcost.getText());
                it.putExtra("date",Cdbtn.getText());
                startActivity(it);
            }
        });
        //to get the data and put them in the list view
        SharedPreferences sharedPreferences=getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        float totalAmount=0;
        ArrayList dbData=db.getCartData(username,"medicine");

        medicines=new String[dbData.size()][];
        for(int i=0;i<medicines.length;i++){
            medicines[i]=new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            medicines[i][0]=strData[0];
            medicines[i][4]="Cost : "+strData[1]+"$";
            totalAmount+=Float.parseFloat(strData[1]);
        }
        totalcost.setText("Total Cost : "+totalAmount);
        // to put in the list view
        list=new ArrayList();
        for(int i=0;i<medicines.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",medicines[i][0]);
            item.put("line2",medicines[i][1]);
            item.put("line3",medicines[i][2]);
            item.put("line4",medicines[i][3]);
            item.put("line5",medicines[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lv=findViewById(R.id.ListViewCartMedicine);
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
}