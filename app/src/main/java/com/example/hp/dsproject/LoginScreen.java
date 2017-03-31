package com.example.hp.dsproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private Button b1;
    private EditText et1 , et2;
    private boolean flag;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        b1 = (Button)findViewById(R.id.ls_b1);
        et1 = (EditText)findViewById(R.id.ls_et1);
        et2 = (EditText)findViewById(R.id.ls_et2);
        SharedPreferences sharedPreferences = getSharedPreferences("Enable" , MODE_PRIVATE);
        flag = sharedPreferences.getBoolean("k0" , false);
        if(!flag) {
            b1.setText(R.string.sign_up);
        }
        else {
            b1.setText(R.string.login);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag) {
                    startActivity(new Intent(LoginScreen.this,SignUpScreen.class));
                    finish();
                }
                else {
                    String name = et1.getText().toString();
                    String pass = et2.getText().toString();
                    if(validate(name , pass)) {
                        startActivity(new Intent(LoginScreen.this , ReservationActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(LoginScreen.this , "Incorrect Username or Password" , Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    private boolean validate(String name , String pass) {
        db = openOrCreateDatabase("userdb" , MODE_PRIVATE , null);
        Cursor ob = db.rawQuery("select * from user;",null);
        ob.moveToFirst();
        String na = ob.getString(1);
        String pa = ob.getString(2);
        ob.close();
        return (na.equals(name))&&(pa.equals(pass));

    }
}
