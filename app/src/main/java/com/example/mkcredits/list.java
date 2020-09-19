package com.example.mkcredits;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {

sqlhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView database=(ListView)findViewById(R.id.datab);

        db=new sqlhelper(list.this);
List<CustomerModel> everyone=new ArrayList<>();
everyone=db.getall();
        ArrayAdapter custemerarray=new ArrayAdapter<CustomerModel>(list.this,android.R.layout.simple_list_item_1,everyone);
    database.setAdapter(custemerarray);
    }


}
