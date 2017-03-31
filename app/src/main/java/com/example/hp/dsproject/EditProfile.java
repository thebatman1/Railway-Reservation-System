package com.example.hp.dsproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    SQLiteDatabase db;
    EditText pass , cpass , contact , add1 , add2 , add3;
    Button b1;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        pass = (EditText)findViewById(R.id.ep_pass);
        cpass = (EditText)findViewById(R.id.ep_confpass);
        contact = (EditText)findViewById(R.id.ep_contact);
        add1 = (EditText)findViewById(R.id.ep_addr1);
        add2 = (EditText)findViewById(R.id.ep_addr2);
        add3 = (EditText)findViewById(R.id.ep_addr3);
        b1 = (Button)findViewById(R.id.ep_b1);
        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);
        Cursor ob = db.rawQuery("select * from user" , null);
        ob.moveToFirst();
        uname = ob.getString(1);
        contact.setText(ob.getString(4));
        String addr[] = ob.getString(5).split("[|]" , 0);
        add1.setText(addr[0]);
        add2.setText(addr[1]);
        add3.setText(addr[2]);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( contact.getText().toString().equals("") || add1.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext() , "Incomplete Information!!" , Toast.LENGTH_LONG).show();
                }
                else if(!cpass.getText().toString().equals(pass.getText().toString())) {
                    Toast.makeText(getApplicationContext() , "Passwords do not match!!" , Toast.LENGTH_LONG).show();
                }
                else if(add2.getText().toString().equals("") && !add3.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext() , "Fill Address Line 2 first!!" , Toast.LENGTH_LONG).show();
                }
                else {
                    String pas = pass.getText().toString();
                    db.execSQL("update user set Pass = '"+pas+"' where Uname LIKE '"+uname+"';");
                    String cont = contact.getText().toString();
                    db.execSQL("update user set Contact = '"+cont+"' where Uname LIKE '"+uname+"';");
                    String addr1 = add1.getText().toString();
                    String addr2 = add2.getText().toString();
                    String addr3 = add3.getText().toString();
                    db.execSQL("update user set Address = '"+addr1+" | "+addr2+" | "+addr3+"' where Uname LIKE '"+uname+"';");
                    Toast.makeText(getApplicationContext() , "Saved!!" , Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}
