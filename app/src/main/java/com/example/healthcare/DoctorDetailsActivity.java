package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    // here is the list view and what i want to put in the discription
    //for family phyzician
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : 1xxx","Hospital Address : 1xxx","Exp : X yrs","Mobile No : 832636","fees 600"},
                    {"Doctor Name : 2xxx","Hospital Address : 2xxx","Exp : X yrs","Mobile No : 832636","900"},
                    {"Doctor Name : 3xxx","Hospital Address : 3xxx","Exp : X yrs","Mobile No : 832636","300"},
            };
    //for Dietician
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : 1yyy","Hospital Address : 1yyy","Exp : y yrs","Mobile No : 832636","fees 600"},
                    {"Doctor Name : 2yyy","Hospital Address : 2yyy","Exp : y yrs","Mobile No : 832636","900"},
                    {"Doctor Name : 3yyy","Hospital Address : 3yyy","Exp : y yrs","Mobile No : 832636","300"},
            };
    //for Dentist
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : 1zzz","Hospital Address : 1zzz","Exp : z yrs","Mobile No : 832636","fees 600"},
                    {"Doctor Name : 2zzz","Hospital Address : 2zzz","Exp : z yrs","Mobile No : 832636","900"},
                    {"Doctor Name : 3zzz","Hospital Address : 3zzz","Exp : z yrs","Mobile No : 832636","300"},
            };
    //for surgeon
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : 1ccc","Hospital Address : 1ccc","Exp : c yrs","Mobile No : 832636","fees 600"},
                    {"Doctor Name : 2ccc","Hospital Address : 2ccc","Exp : c yrs","Mobile No : 832636","900"},
                    {"Doctor Name : 3ccc","Hospital Address : 3ccc","Exp : c yrs","Mobile No : 832636","300"},
            };
    //for Cardiologist
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : 1mmm","Hospital Address : 1mmm","Exp : m yrs","Mobile No : 832636","fees 600"},
                    {"Doctor Name : 2mmm","Hospital Address : 2mmm","Exp : m yrs","Mobile No : 832636","900"},
                    {"Doctor Name : 3mmm","Hospital Address : 3mmm","Exp : m yrs","Mobile No : 832636","300"},
            };

TextView tv;
Button btn;
String [][] doctor_detail={};
ArrayList list;
SimpleAdapter sa;
HashMap<String,String>item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDBack);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        //for listview (we need to fetch data from database)
        if(title.compareTo("FamilyPhyzician")==0){
            doctor_detail=doctor_details1;
        }
        else
        if(title.compareTo("Dietician")==0){
            doctor_detail=doctor_details2;
        }
        else
        if(title.compareTo("Dentist")==0){
            doctor_detail=doctor_details3;
        }
        else
        if(title.compareTo("Surgeon")==0){
            doctor_detail=doctor_details4;
        }
        else {
            doctor_detail=doctor_details5;
        }
        //for back button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<doctor_detail.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_detail[i][0]);
            item.put("line2",doctor_detail[i][1]);
            item.put("line3",doctor_detail[i][2]);
            item.put("line4",doctor_detail[i][3]);
            item.put("line5","Fees: "+doctor_detail[i][4]+"$");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lv=findViewById(R.id.ListViewDD);
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_detail[i][0]);
                it.putExtra("text3",doctor_detail[i][1]);
                it.putExtra("text4",doctor_detail[i][3]);
                it.putExtra("text5",doctor_detail[i][4]);
                startActivity(it);
            }
        });
    }
}