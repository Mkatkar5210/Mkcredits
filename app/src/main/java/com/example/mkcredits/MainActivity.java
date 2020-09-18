package com.example.mkcredits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
sqlhelper mj;

ListView finallist=(ListView)findViewById(R.id.finallist);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void gotolist(View view)
    { mj=new sqlhelper(this);
        boolean j=mj.adddata();
        if(j)
            Toast.makeText(this,"data added",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"error while adding data",Toast.LENGTH_LONG).show();

        List<CustomerModel> Custmers=mj.getall();
        ArrayAdapter custmerarray=new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1,Custmers);
        finallist.setAdapter(custmerarray);

    }
}