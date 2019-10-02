package com.example.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_details extends AppCompatActivity {

    EditText ut,ur,un,ub,uc,ucon;
    Button bck2;
    dbhelper db;
    String id;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        db = new dbhelper(this);

        ut = (EditText) findViewById(R.id.etUType);
        ur = (EditText) findViewById(R.id.etURelation);
        un = (EditText) findViewById(R.id.etUName);
        ub = (EditText) findViewById(R.id.etUBld_grp);
        uc = (EditText) findViewById(R.id.etUContact);
        ucon = (EditText) findViewById(R.id.etUCond);
        bck2 = (Button) findViewById(R.id.btnUBack);

        try
        {
            Intent i = getIntent();
            id = i.getStringExtra("nm");
            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
            c = db.getUser_details(id);
            c.moveToFirst();

            if (!c.isAfterLast())
            {
                ut.setText(c.getString(0));
                ur.setText(c.getString(1));
                un.setText(c.getString(2));
                ub.setText(c.getString(3));
                uc.setText(c.getString(4));
                ucon.setText(c.getString(5));
            }
            c.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }
    }

    public void cback(View v)
    {
        Intent i = new Intent(getApplicationContext(),AUser_List.class);
        startActivity(i);
    }
}
