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

public class BuyMedicineActivity extends AppCompatActivity {

    // we r using the same multiline layout so we give it an empty string
    private String[][] Medicine={
            {"medicine 1 : med1","","","","999"},
            {"medicine 2 : med2","","","","299"},
            {"medicine 3 : med3","","","","899"},
            {"medicine 4 : med4","","","","499"},
            {"medicine 5 : med5","","","","699"},
            {"medicine 6 : med6","","","","699"},
            {"medicine 7 : med7","","","","699"},
            {"medicine 8 : med8","","","","699"},
            {"medicine 9 : med9","","","","699"},
    };
    // for the informations of package
    private  String []Medicine_details =  {
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
                    "Lipid Profile",
            "666666",
            "7777777",
            "8888888",
            "9999999",
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lv;
    Button Bbtn,Gbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        Bbtn=findViewById(R.id.buttonBMBack);
        Gbtn=findViewById(R.id.buttonBMgotocart);
        lv=findViewById(R.id.ListViewBM);

        //for back button
        Bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity2.class));
            }
        });
        //for the list
        list=new ArrayList();
        for(int i=0;i<Medicine.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",Medicine[i][0]);
            item.put("line2",Medicine[i][1]);
            item.put("line3",Medicine[i][2]);
            item.put("line4",Medicine[i][3]);
            item.put("line5","Total Cost: "+Medicine[i][4]+"$");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lv=findViewById(R.id.ListViewBM);
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailActivity.class);
                //we have 5 strings details
                it.putExtra("text1",Medicine[i][0]);
                it.putExtra("text2",Medicine_details[i]);
                it.putExtra("text3",Medicine[i][4]);
                startActivity(it);
            }
        });
        //for go to cart
        Gbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,MedicineCartActivity.class));
            }
        });
    }
}