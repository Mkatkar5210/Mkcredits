package com.example.mkcredits;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransitionImpl;
import java.util.ArrayList;
import java.util.List;
import java.lang.String.*;

import static android.text.TextUtils.isEmpty;

//to make transactions
public class usertransfer extends AppCompatActivity {
    ListView data;
    sqlhelper mj;
    TextView user;
    EditText amount;
    double transferamount=0;
    sqlhelper db;

    public
   static String id;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.usertotransfer);

        super.onCreate(savedInstanceState);
        data=(ListView)findViewById(R.id.selectuser);
        db=new sqlhelper(this);
        amount=(EditText)findViewById(R.id.amount);
        user=(TextView) findViewById(R.id.selecteduser);
        String name=db.selectuser(id);
        user.setText(name);
        List<CustomerModel> everyone= new ArrayList<>();   //to take input from database
        everyone=db.getothers(id);
        ArrayAdapter custemerarray=new ArrayAdapter<CustomerModel>(usertransfer.this,android.R.layout.simple_list_item_1,everyone);//to set the listview
        data.setAdapter(custemerarray);
            data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    transferamount =0;
                    transferamount=Double.parseDouble(amount.getText().toString());
                    CustomerModel clickedcustomer = (CustomerModel) parent.getItemAtPosition(position);
                    int id2 = clickedcustomer.getId();
                    double finalamt;
                    Exception exception=new Exception();
                    try {
                        if (transferamount > 0 && db.getTOTALCREDITS(usertransfer.id) >= transferamount) {
                            finalamt = db.getTOTALCREDITS(usertransfer.id) - transferamount;
                            db.finalaccount(usertransfer.id, finalamt);

                            finalamt = db.getTOTALCREDITS(Integer.toString(id2)) + transferamount;
                            db.finalaccount(Integer.toString(id2), finalamt);
                            Intent intent = new Intent(usertransfer.this, list.class);
                            startActivity(intent);
                            Toast.makeText(usertransfer.this, "transaction succesfull", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(usertransfer.this, "insufficient funds or invalid amount", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e1)
                    {
                        Toast.makeText(usertransfer.this, "insufficient funds or invalid amount", Toast.LENGTH_LONG).show();

                    }


                }
            });


        }

    }


