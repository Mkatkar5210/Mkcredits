package com.example.mkcredits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class sqlhelper extends SQLiteOpenHelper {


    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String TOTALCREDITS = "TOTALCREDITS";
    private static final String CREDITS_TABLE = "credits_table";

    public sqlhelper(@Nullable Context context) {
        //create database
        super(context,CREDITS_TABLE,null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
            //crete database table
        String create = "CREATE TABLE " + CREDITS_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + EMAIL + " TEXT," + TOTALCREDITS + " REAL);";

        db.execSQL(create);
    }
 public String selectuser(String id)
 {
     String query="SELECT * FROM "+CREDITS_TABLE+" WHERE "+ID+" = "+id;
     SQLiteDatabase db=this.getWritableDatabase();
     Cursor cursor =db.rawQuery(query,null,null);
     cursor.moveToFirst();
     String name;
     if(cursor.getCount()>0)
         name = "amount transfering from "+cursor.getString(cursor.getColumnIndex(NAME));
         else
             name=null;
     return name;

 }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + CREDITS_TABLE);
    onCreate(db);
    }

    public double getTOTALCREDITS(String id) {
        String query="SELECT * FROM "+CREDITS_TABLE+" WHERE "+ID+" = "+id;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null,null);
        cursor.moveToFirst();
        double totalcredits=cursor.getDouble(3);
        cursor.close();
        db.close();
        return totalcredits;
    }


    public void finalaccount(String id,double amount) {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+CREDITS_TABLE+" SET "+TOTALCREDITS+"="+amount+" WHERE "+ID+" = "+id+";";
        db.execSQL(query);

    }
    //add user in the database
    public boolean Adduser(String name,String email,double credit) {
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv=new ContentValues();
    cv.put(NAME,name);
    cv.put(EMAIL,email);
    cv.put(TOTALCREDITS,credit);
    long result=db.insert(CREDITS_TABLE,null,cv);
    if(result==-1)
    {
        return false;
    }
    else
    {
        return true;
    }
    }




    //to select the usert to transfer the amount
    public List<CustomerModel> getothers(String id)
    {
        List<CustomerModel> returnl=new ArrayList<>();
        String query1=" SELECT * FROM "+CREDITS_TABLE+" WHERE "+ID+" != "+id;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =db.rawQuery(query1,null,null);
        if(cursor.moveToFirst())
        {
            do{
                int id1=cursor.getInt(0);
                String name=cursor.getString(1);
                String email=cursor.getString(2);
                double credits=cursor.getDouble(3);
                CustomerModel cu=new CustomerModel(id1,name,email,credits);
                returnl.add(cu);
            }while(cursor.moveToNext());
        }
        cursor.close();

        db.close();
        return returnl;
    }


    //show all the user entries
    public List<CustomerModel> getall()
    {
    List<CustomerModel> returnl=new ArrayList<>();
    String query1=" SELECT * FROM "+CREDITS_TABLE;
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor cursor =db.rawQuery(query1,null,null);
    if(cursor.moveToFirst())
    {
        do{
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String email=cursor.getString(2);
            double credits=cursor.getDouble(3);
            CustomerModel cu=new CustomerModel(id,name,email,credits);
            returnl.add(cu);
        }while(cursor.moveToNext());
    }
    cursor.close();

    db.close();
    return returnl;
    }




}
