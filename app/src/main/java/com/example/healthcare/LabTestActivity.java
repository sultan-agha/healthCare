package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
Button Bbtn,Gbtn;
// we r using the same multiline layout so we give it an empty string
private String[][] packages={
        {"Package 1 : Full Body Checkup","","","","999"},
        {"Package 2 : Blood Glucose Fasting","","","","299"},
        {"Package 3 : COVID-19 Antibody-IgG","","","","899"},
        {"Package 4 : Thyroid Check","","","","499"},
        {"Package 5 : Immunity Check","","","","699"},
        {"Package 6 : 6 check","","","","699"},

};
// for the informations of package
    private  String []packages_details =  {
        "Blood Glucose Fasting\n"+" Complete Hemogram\n"+"HBA1C\n"+
        "Kidney Function Test\n"+"LDH LAcate Dehydrogenase ,serum\n"+
        "Lipid Profile\n"+"Liver Function Test",

        "Blood Glucose Fasting",


        "COVID-19 Antibody-IgG",


       "Thyroid profile Total (T3,T4,TSH Ultra Sensitive)",


        "Complete Hemogram\n"+
        "CRP(C Reactive Protien)\n"+
        "Iron Studies\n"+
        "Kidney Function Test\n"+
        "Vitamin D Total-25 Hydroxy\n"+
        "Liver Function Test\n"+
        "Lipid Profile"
};
        HashMap<String,String> item;
        ArrayList list;
        SimpleAdapter sa;
        ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        Bbtn=findViewById(R.id.buttonLTBack);
        Gbtn=findViewById(R.id.buttonLTgotocart);
        lv=findViewById(R.id.ListViewLT);

        //for back button
        Bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity2.class));
            }
        });
        //for the list
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost: "+packages[i][4]+"$");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lv=findViewById(R.id.ListViewLT);
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(LabTestActivity.this,LabTestDetailActivity.class);
                //we have 5 strings details
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packages_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
        //for go to cart
        Gbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, LabCartActivity.class));
            }
        });
    }
}