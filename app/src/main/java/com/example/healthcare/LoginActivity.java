package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
 EditText edUsername,edPassword;
 boolean passwordVisible;
 Button bt;
 TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        bt = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textView3);
        SpannableString spannableString = new SpannableString("Don't have an account? create one ");
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        tv.setText(spannableString);

        edPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=edPassword.getRight()-edPassword.getCompoundDrawables()[right].getBounds().width()){
                        int selection=edPassword.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            edPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_security_24,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            //set drawable image here
                            edPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_security_24,0,R.drawable.baseline_visibility_24,0);
                            //for hide password
                            edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        edPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String pass = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (username.length() == 0 || pass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(username, pass) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        // to save them in the local area to use them in home activity(we use share preferences)
                        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity2.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}