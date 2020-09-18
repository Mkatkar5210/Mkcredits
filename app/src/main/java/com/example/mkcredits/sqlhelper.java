package com.example.mkcredits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static android.provider.Contacts.SettingsColumns.KEY;
import static java.sql.Types.REAL;
import static java.text.Collator.PRIMARY;
import static org.xmlpull.v1.XmlPullParser.TEXT;

public class sqlhelper extends SQLiteOpenHelper {


    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String TOTALCREDITS = "TOTALCREDITS";
    private static final String CREDITS_TABLE = "credits_table";

    public sqlhelper(@Nullable Context context) {
        super(context,"credits",null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + CREDITS_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + EMAIL + " TEXT," + TOTALCREDITS + " REAL);";

        db.execSQL(create);
    }

public boolean adddata()
{
boolean i;
i=insert( "bill gates", "billgates@gmail.com",11560);
 i=insert( "Warren Buffett", "warrent@gmail.com",8240);
 i=insert("Bernard Arnault", "bernard@gmail.com",11520);
 i=insert( "mukesh ambani", "mukesh@gmail.com",8110);
 i=insert( "mark zuckerberg","mark@gmail.com",7420);
 i=insert( "Larry Ellison", "larry@gmail.com",7420);
 i=insert( "Larry Page","llary@gmail.com",7360);
 i=insert( "Carlos Slim", "carlos@gmail.com",5090);
 i=insert( "MacKenzie Scott", "mac@gmail.com",6240);
 i=insert("jeff bezos","jeff@gmail.com",10840);

return i;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + CREDITS_TABLE);
onCreate(db);
    }



public boolean insert(String name,String email,double credit)
{
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv=new ContentValues();
    cv.put("NAME",name);
    cv.put("EMAIL",email);
    cv.put("TOTALCREDITS",credit);
    long result=db.insert("credits_table",null,cv);
    if(result==-1)
    {
        return false;
    }
    else
    {
        return true;
    }
}
public List<CustomerModel> getall()
{
 List<CustomerModel> returnl=new ArrayList<>();
 String query1="SELECT * FROM "+CREDITS_TABLE;
 SQLiteDatabase db=this.getReadableDatabase();
 Cursor cursor =db.rawQuery(query1,null);
if(cursor.moveToFirst())
{
    do{
        int id=cursor.getInt(1);
        String name=cursor.getString(2);
        String email=cursor.getString(3);
        double credits=cursor.getDouble(4);
CustomerModel cu=new CustomerModel(id,name,email,credits);
returnl.add(cu);

    }while(cursor.moveToNext());
}
cursor.close();
db.close();
 return returnl;
}
}
