package com.example.hp.dsproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBalance extends AppCompatActivity {

    SQLiteDatabase db;
    EditText cardno , pin , amt;
    Button b1;
    String uname;
    int amount;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);

        count = 0;
        cardno = (EditText)findViewById(R.id.ab_cardno);
        pin = (EditText)findViewById(R.id.ab_pin);
        amt = (EditText)findViewById(R.id.ab_amt);
        b1 = (Button)findViewById(R.id.ab_b1);
        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);
        Cursor ob = db.rawQuery("select * from user;" , null);
        ob.moveToFirst();
        uname = ob.getString(1);
        amount = ob.getInt(6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cardno.getText().toString().equals("") || pin.getText().toString().equals("") || amt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext() , "Please Enter Correct Details" , Toast.LENGTH_LONG).show();
                }
                else if(cardno.getText().toString().length()<16) {
                    Toast.makeText(getApplicationContext() , "Enter Valid Card Number" , Toast.LENGTH_LONG).show();
                    count++;
                    if(count>3) {
                        Toast.makeText(getApplicationContext() , "Failed!! Try Again Later!!" , Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else if(pin.getText().toString().length()!=4) {
                    Toast.makeText(getApplicationContext() , "Enter Valid PIN" , Toast.LENGTH_LONG).show();
                    count++;
                    if(count>3) {
                        Toast.makeText(getApplicationContext() , "Failed!! Try Again Later!!" , Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else {
                    String k = amt.getText().toString();
                    amount = amount + Integer.parseInt(k);
                    db.execSQL("update user set Amount=" + amount + " where Uname LIKE '" + uname + "';");
                    Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
