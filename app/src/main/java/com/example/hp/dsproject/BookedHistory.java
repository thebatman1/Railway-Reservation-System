package com.example.hp.dsproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookedHistory extends AppCompatActivity {

    ListView lv;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_history);

        lv = (ListView)findViewById(R.id.bh_list);
        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);
        ArrayList<String> al = new ArrayList<>();

        Cursor ob = db.rawQuery("select * from rail" , null);
        while(ob.moveToNext()) {
            al.add(ob.getString(0));
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , al);
        aa.notifyDataSetChanged();
        lv.setAdapter(aa);
    }
}
