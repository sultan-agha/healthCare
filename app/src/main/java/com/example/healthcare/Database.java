package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
//othertype is either lab or medicine
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);
        // for cart table
        String qry2="create table cart(username text,product text,price float,othertype text)";
        sqLiteDatabase.execSQL(qry2);
        //for book table
        String qry3="create table orderplace(username text,fullname text,address text,contact text,pincode int ,date text,time text, price float, othertype text)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    public void register(String username,String email,String password){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String password){
        int res=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and password=?",str);
        //if user exist
        if(c.moveToFirst()){
            res=1;
        }
        return res;
    }
    // for cart
    public void addCart(String username,String product,float price ,String othertype){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("othertype",othertype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    //check if is in the cart
    public int checkCart(String username,String product){
        int res=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from cart where username=? and product=?",str);
        //if user exist
        if(c.moveToFirst()){
            res=1;
        }
        db.close();
        return res;
    }
    //remove from cart
    public void removeCart(String username,String othertype){
        String str[]=new String[2];
        str[0]=username;
        str[1]=othertype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","username=? and othertype=?",str);
        db.close();
    }
    // to get the cart data
    public ArrayList getCartData(String username, String othertype){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[2];
        str[0]=username;
        str[1]=othertype;
        Cursor c=db.rawQuery("select * from cart where username=? and othertype=?",str);
        //if user exist
        if(c.moveToFirst()){
           do{
               String product=c.getString(1);
               String price=c.getString(2);
               arr.add(product+"$"+price);
           }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    //for place order
    public void addOrder(String username,String fullname,String address ,String contact,int pincode,String date,String time,float price, String othertype){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("price",price);
        cv.put("othertype",othertype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }
    public ArrayList getOrderData(String username){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[1];
        str[0]=username;
        Cursor c=db.rawQuery("select * from orderplace where username=?",str);
        //if user exist
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8)+"$");
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    //check if appointment exist
    public int getAppointmentExist(String username,String fullname,String address ,String contact,String date,String time){
        int res=0;
        String str[]=new String[6];
        str[0]=username;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from orderplace where username=? and fullname=? and address=? and contact=? and date=? and time=?",str);
        //if user exist
        if(c.moveToFirst()){
            res=1;
        }
        db.close();
        return res;
    }
}
