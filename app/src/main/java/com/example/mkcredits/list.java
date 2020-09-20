package com.example.mkcredits;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
//to show the whole list
public class list extends AppCompatActivity {

sqlhelper db;//take the instance of database
    ListView database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        database=(ListView)findViewById(R.id.datab);

        db=new sqlhelper(list.this);
        List<CustomerModel> everyone=new ArrayList<>();   //to take input from database
        everyone=db.getall();
        ArrayAdapter custemerarray=new ArrayAdapter<CustomerModel>(list.this,android.R.layout.simple_list_item_1,everyone);//to set the listview
        database.setAdapter(custemerarray);
        database.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usertransfer ut=new usertransfer();
                Intent intent=new Intent(list.this,usertransfer.class);
                CustomerModel clickedcustomer= (CustomerModel) parent.getItemAtPosition(position);
                Toast.makeText(list.this,"ID="+Integer.toString(clickedcustomer.getId()),Toast.LENGTH_LONG).show();
                ut.id=Integer.toString(clickedcustomer.getId());
                startActivity(intent);



            }
        });
    }


}
