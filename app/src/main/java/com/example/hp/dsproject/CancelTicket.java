package com.example.hp.dsproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CancelTicket extends AppCompatActivity {

    EditText et;
    Button b;
    SQLiteDatabase db;
    String pnr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_ticket);

        et = (EditText)findViewById(R.id.ct_et1);
        b = (Button)findViewById(R.id.ct_b1);

        pnr = et.getText().toString();
        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("delete from rail where PNR like '"+pnr+"';");
                Toast.makeText(getApplicationContext() , "Deleted!!" , Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
