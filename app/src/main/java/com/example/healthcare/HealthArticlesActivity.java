package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {
ListView lv;
ArrayList list;
HashMap<String,String> item;
SimpleAdapter sa;
Button backbtn;
private String[][] health_details=
        {
                {"Walking daily","","","","Click for more details"},
                {"Home care for COVID_19","","","","Click for more details"},
                {"Stop smoking","","","","Click for more details"},
                {"Menstrual cramps","","","","Click for more details"},
                {"Healthy Gut","","","","Click for more details"},
        };
private int[]images={
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5,
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        lv=findViewById(R.id.ListViewHA);
        backbtn=findViewById(R.id.buttonHABack);

        //for back button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this,HomeActivity2.class));
            }
        });
        //for the list
        list=new ArrayList();
        for(int i=0;i<health_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lv=findViewById(R.id.ListViewHA);
        lv.setAdapter(sa);
        // for the list
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(HealthArticlesActivity.this,HealthArticlesDetailActivity.class);
                //we have 5 strings details
                it.putExtra("text1",health_details[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });
    }
}