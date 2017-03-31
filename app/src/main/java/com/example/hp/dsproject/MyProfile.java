package com.example.hp.dsproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {

    TextView name , uname , dob , contact , address , amount;
    SQLiteDatabase db;
    Button b1 , b2;
    Cursor ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);
        name = (TextView)findViewById(R.id.mp_name);
        uname = (TextView)findViewById(R.id.mp_uname);
        dob = (TextView)findViewById(R.id.mp_dob);
        contact = (TextView)findViewById(R.id.mp_contact);
        address = (TextView)findViewById(R.id.mp_addr);
        amount = (TextView)findViewById(R.id.mp_amt);
        b1 = (Button)findViewById(R.id.mp_b1);
        b2 = (Button)findViewById(R.id.mp_b2);

        ob = db.rawQuery("select * from user;" , null);
        ob.moveToFirst();
        //Name varchar ,  Uname varchar , Pass varchar , DOB varchar , Contact varchar , Address varchar , Amount int

        String adde = ob.getString(5);
        String[] ode = adde.split("[|]" , 0);

        name.setText(ob.getString(0));
        dob.setText(ob.getString(3));
        uname.setText(ob.getString(1));
        contact.setText(ob.getString(4));
        address.setText(ode[0]+" "+ode[1]+" "+ode[2]);
        amount.setText(""+ob.getInt(6));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , EditProfile.class);
                startActivity(i);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , AddBalance.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        amount.setText(""+ob.getInt(6));
    }

    @Override
    protected void onResume() {
        super.onResume();
        amount.setText(""+ob.getInt(6));
    }
}
