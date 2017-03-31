package com.example.hp.dsproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpScreen extends AppCompatActivity {

    Button b1;
    EditText name , dob , contact , addr1 , addr2 , addr3 , uname , pass , cpass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        b1 = (Button)findViewById(R.id.su_b1);
        name = (EditText)findViewById(R.id.su_name);
        uname = (EditText)findViewById(R.id.su_uname);
        pass = (EditText)findViewById(R.id.su_pass);
        cpass = (EditText)findViewById(R.id.su_confpass);
        dob = (EditText)findViewById(R.id.su_dob);
        contact = (EditText)findViewById(R.id.su_contact);
        addr1 = (EditText)findViewById(R.id.su_addr1);
        addr2 = (EditText)findViewById(R.id.su_addr2);
        addr3 = (EditText)findViewById(R.id.su_addr3);
        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na = name.getText().toString();
                String dobi = dob.getText().toString();
                String con = contact.getText().toString();
                String add1 = addr1.getText().toString();
                String add2 = addr2.getText().toString();
                String add3 = addr3.getText().toString();
                String una = uname.getText().toString();
                String pas = pass.getText().toString();
                String cpas = cpass.getText().toString();
                if( na.equals("") || dobi.equals("") || con.equals("") || add1.equals("") || una.equals("") || pas.equals("") || cpas.equals(""))
                    Toast.makeText(getApplicationContext() , "Pleae fill all the fields" , Toast.LENGTH_LONG).show();
                else if(!pas.equals(cpas))
                    Toast.makeText(getApplicationContext() , "Passwords donot match!!" , Toast.LENGTH_SHORT).show();
                else if(con.length()!=10) {
                    Toast.makeText(getApplicationContext() , "Enter a valid phone number!!" , Toast.LENGTH_SHORT).show();
                }
                else {
                    int amnt = 0;
                    db.execSQL("create table if not exists user(Name varchar ,  Uname varchar , Pass varchar , DOB varchar , Contact varchar , Address varchar , Amount int);");
                    db.execSQL("insert into user values('"+na+"','"+una+"','"+pas+"','"+dobi+"','"+con+"','"+add1+" | "+add2+" | "+add3+"',"+amnt+");");
                    Toast.makeText(getApplicationContext() , "Details Saved!!" , Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("Enable" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("k0" , true);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext() , LoginScreen.class));
                    finish();
                }
            }
        });

    }
}
