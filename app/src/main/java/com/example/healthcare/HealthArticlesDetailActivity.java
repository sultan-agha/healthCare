package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDetailActivity extends AppCompatActivity {
TextView tv1;
ImageView img;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_detail);
        btn=findViewById(R.id.buttonHADBack);
        tv1=findViewById(R.id.textViewHADTitle);
        img=findViewById(R.id.imageViewHAD);
        //for back button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesDetailActivity.this,HealthArticlesActivity.class));
            }
        });
        Intent intent=getIntent();
        tv1.setText(intent.getStringExtra("text1"));
        //for image
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);
        }
    }
}