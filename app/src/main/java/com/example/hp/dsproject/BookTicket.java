package com.example.hp.dsproject;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

public class BookTicket extends AppCompatActivity {

    Spinner sources , dest ;
    TextView price , balance , PNR;
    ArrayAdapter arrayAdapter1 , arrayAdapter2;
    Button b1;

    static String[] sourcest = {"SILCHAR"};
    static String[] destinations = {"GUWAHATI"};
    static int[] prices ={85};
    String pnr;
    int count=0 , bal;

    SQLiteDatabase db;
    Cursor ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        sources = (Spinner)findViewById(R.id.bt_source_stations);
        dest = (Spinner)findViewById(R.id.bt_dest_stations);
        price = (TextView)findViewById(R.id.bt_price);
        balance = (TextView)findViewById(R.id.bt_balance);
        PNR = (TextView)findViewById(R.id.bt_tv2);
        b1 = (Button)findViewById(R.id.bt_b1);

        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);

        ob = db.rawQuery("select * from user;" , null);
        ob.moveToFirst();
        balance.setText(""+ob.getInt(6));
        bal = ob.getInt(6);

        arrayAdapter1 = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item ,sourcest);
        sources.setAdapter(arrayAdapter1);

        arrayAdapter2 = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , destinations);
        dest.setAdapter(arrayAdapter2);

        Random rand = new Random();

        int  n = rand.nextInt(10000) + 10000;

        pnr = Integer.toString(n);

        db.execSQL("insert into rail values('"+pnr+"','"+sourcest[0]+"','"+destinations[0]+"',"+prices[0]+")");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>0){
                    finish();
                }
                else {
                    if(bal>prices[0]) {
                        PNR.setText("PNR is :" + pnr +"\n Please note it down somewhere.");
                        int diff = bal-prices[0];
                        db.execSQL("update user set Amount="+diff+" where Uname like '"+ob.getString(1)+"';");
                        balance.setText(""+ob.getInt(6));
                        SharedPreferences sp = getSharedPreferences("listmode" , MODE_PRIVATE);
                        SharedPreferences.Editor e = sp.edit();

                        e.putBoolean("k1" , true);
                        e.apply();
                    }
                    else {
                        PNR.setText("Not enough balance!!");
                    }
                    count++;
                    b1.setText("Exit");
                }
            }
        });
    }
}



