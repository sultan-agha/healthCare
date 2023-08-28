package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {
    EditText RedUsername, RedPassword, Redemail, Redconfirmpass;
    Button Rbtn;
    TextView Rtv;
    boolean passwordVisible;
    boolean ConfirmPasswordVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RedUsername = findViewById(R.id.editTextRegisterUsername4);
        Redemail = findViewById(R.id.editTextEmail);
        RedPassword = findViewById(R.id.editTextRegisterPassword);
        Redconfirmpass = findViewById(R.id.editTextRegestirConfirmPassword);
        Rbtn = findViewById(R.id.buttonRegister);
        Rtv = findViewById(R.id.textViewR);
        SpannableString spannableString = new SpannableString("Already have an account? Login");
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        Rtv.setText(spannableString);
// for password
        RedPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=RedPassword.getRight()-RedPassword.getCompoundDrawables()[right].getBounds().width()){
                        int selection=RedPassword.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            RedPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_security_24,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            RedPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            //set drawable image here
                            RedPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_security_24,0,R.drawable.baseline_visibility_24,0);
                            //for hide password
                            RedPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        RedPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        // for confirm password
        Redconfirmpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=Redconfirmpass.getRight()-Redconfirmpass.getCompoundDrawables()[right].getBounds().width()){
                        int selection=Redconfirmpass.getSelectionEnd();
                        if(ConfirmPasswordVisible){
                            //set drawable image here
                            Redconfirmpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_security_24,0,R.drawable.baseline_visibility_off_24,0);
                            //for hide password
                            Redconfirmpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            ConfirmPasswordVisible=false;
                        }else{
                            //set drawable image here
                            Redconfirmpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_security_24,0,R.drawable.baseline_visibility_24,0);
                            //for hide password
                            Redconfirmpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            ConfirmPasswordVisible=true;
                        }
                        Redconfirmpass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = RedUsername.getText().toString();
                String email = Redemail.getText().toString();
                String pass = RedPassword.getText().toString();
                String Confirmpass = Redconfirmpass.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if (username.length() == 0 || pass.length() == 0 || email.length() == 0 || Confirmpass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                } else if (pass.compareTo(Confirmpass) == 0) {
                          if(isValid(pass)){
                              db.register(username,email,pass);
                              Toast.makeText(getApplicationContext(), "Record inserted", Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                          }
                          else{
                              Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters and have capital letters,digits and special character", Toast.LENGTH_SHORT).show();
                          }
                } else {
                    Toast.makeText(getApplicationContext(), "Password and Confirm password didnt match ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Rtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            if (f1 == 1 && f2 == 1)
                return true;
            return false;
        }
    }
}