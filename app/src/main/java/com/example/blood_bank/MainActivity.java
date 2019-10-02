package com.example.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorTreeAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button ad,r,al;
    ImageButton sh,vd;
    dbhelper db;
    String bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new dbhelper(this);

        ad = (Button) findViewById(R.id.btnAddDon);
        r = (Button) findViewById(R.id.btnRegister);
        al = (Button) findViewById(R.id.btnAdmin);
        sh = (ImageButton) findViewById(R.id.ibtnShare);
        vd = (ImageButton) findViewById(R.id.ibtnVideo);

    }
    public void adDonor(View v)
    {
        Intent i = new Intent(getApplicationContext(),Registration.class);
        startActivity(i);
    }

    public void request(View v)
    {
        Intent i = new Intent(getApplicationContext(),RequestForm.class);
        startActivity(i);
    }

    public void aLogin(View v)
    {
        Intent i = new Intent(getApplicationContext(),AdminLogin.class);
        startActivity(i);
    }

    public void search(View v)
    {
            Intent i = new Intent(getApplicationContext(),Donors_List.class);
            startActivity(i);
    }

    public void share(View v)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,"Blood Bank - A chance to donate blood. http://www.play.google.com");
        startActivity(i);
    }

    public void bvideo(View v)
    {
        Intent i =new Intent(getApplicationContext(),video.class);
        startActivity(i);
    }
}
