package com.example.basicinformationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OnclickUserActivity extends AppCompatActivity {
    public TextView fnamedisplayValue;
    public TextView lnamedisplayValue;
    public TextView agedisplayValue;
    public TextView emaildisplayValue;
    public TextView phonedisplayValue;
    public TextView birthdatedisplayValue;
    public TextView countrydisplayValue;
    public TextView statedisplayValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick_user);
        
        fnamedisplayValue=(TextView) findViewById(R.id.fnamedisplayValue);
        lnamedisplayValue=(TextView) findViewById(R.id.lnamedsiplayValue);
        agedisplayValue=(TextView) findViewById(R.id.agedisplayValue);
        emaildisplayValue=(TextView) findViewById(R.id.emaildisplayValue);
        phonedisplayValue=(TextView) findViewById(R.id.phonedisplayValue);
        birthdatedisplayValue=(TextView) findViewById(R.id.birthdatedisplayValue);
        countrydisplayValue=(TextView) findViewById(R.id.countrydisplayValue);
        statedisplayValue=(TextView) findViewById(R.id.statedisplayValue);

    }
}