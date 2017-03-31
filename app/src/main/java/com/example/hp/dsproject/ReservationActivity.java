package com.example.hp.dsproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReservationActivity extends AppCompatActivity {

    Button b1 , b2 , b3;
    SharedPreferences sp;
    SQLiteDatabase db;
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        b1 = (Button)findViewById(R.id.ra_b1);
        b2 = (Button)findViewById(R.id.ra_b2);
        b3 = (Button)findViewById(R.id.ra_b3);
        sp = getSharedPreferences("listmode" , MODE_PRIVATE);
        flag = sp.getBoolean("k1" , false);

        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag) {
                    db.execSQL("create table if not exists rail(PNR varchar , Source varchar , Destination varchar , Price int);");
                    startActivity(new Intent(getApplicationContext() , BookTicket.class));
                }
                else {
                    startActivity(new Intent(getApplicationContext() , BookTicket.class));
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Cursor c = db.rawQuery("select count(*) from rail" , null);
                    c.moveToFirst();
                    int count = c.getInt(0);
                    if(count==0) {
                        Toast.makeText(getApplicationContext() , "No Tickets to cancel!!" , Toast.LENGTH_LONG).show();
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(), CancelTicket.class));
                    }
                }


        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , BookedHistory.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menulist , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mi_mp:
                startActivity(new Intent(getApplicationContext() , MyProfile.class));
                break;
            case R.id.mi_abtw:
                startActivity(new Intent(getApplicationContext() , AddBalance.class));
                break;
            case R.id.mi_aa:
                Toast.makeText(this , "Developed By :\nMrinmay Mukherjee\nSaurabh Kumar\nRohan Khunteta" , Toast.LENGTH_LONG).show();
                break;
            case R.id.mi_lg:
                startActivity(new Intent(this , LoginScreen.class));
                finish();
                Toast.makeText(this , "You have been successfully logged out!!" , Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
