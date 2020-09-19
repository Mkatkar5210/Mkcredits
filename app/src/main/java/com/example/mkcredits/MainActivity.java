package com.example.mkcredits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Button  showacc;
Button adduser;
EditText name;
EditText email;
EditText currentcredits;
    sqlhelper     mj = new sqlhelper(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
showacc= (Button) findViewById(R.id.showacc);
adduser=(Button)findViewById(R.id.addu);
name=(EditText) findViewById(R.id.name);
email=(EditText) findViewById(R.id.email);
currentcredits=(EditText) findViewById(R.id.credits);


showacc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

Intent intent=new Intent(MainActivity.this,list.class);
startActivity(intent);




    }
});

adduser.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Exception exception=new Exception();
try {
    String username = name.getText().toString();
    String useremail = email.getText().toString();
    double credit = Double.parseDouble(currentcredits.getText().toString());
    boolean j = mj.Adduser(username, useremail, credit);
    if (j)
        Toast.makeText(MainActivity.this, "user added", Toast.LENGTH_SHORT).show();
    else
        Toast.makeText(MainActivity.this, "error while adding dat", Toast.LENGTH_SHORT).show();
}
catch (Exception e1){
    Toast.makeText(MainActivity.this, "error while adding data", Toast.LENGTH_SHORT).show(); }
                               }
                           }
);

    }

}