package com.example.basicinformationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

    private String fname;
    private String lname;
    private String age;
    private String email;
    private String phone;
    private String dob;
    private String country;
    private String state;

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


        fnamedisplayValue = (TextView) findViewById(R.id.fnamedisplayValue);
        lnamedisplayValue = (TextView) findViewById(R.id.lnamedsiplayValue);
        agedisplayValue = (TextView) findViewById(R.id.agedisplayValue);
        emaildisplayValue = (TextView) findViewById(R.id.emaildisplayValue);
        phonedisplayValue = (TextView) findViewById(R.id.phonedisplayValue);
        birthdatedisplayValue = (TextView) findViewById(R.id.birthdatedisplayValue);
        countrydisplayValue = (TextView) findViewById(R.id.countrydisplayValue);
        statedisplayValue = (TextView) findViewById(R.id.statedisplayValue);

        Intent callingIntent = getIntent();
        fname = callingIntent.getStringExtra(MainActivity.F_NAME);
        lname = callingIntent.getStringExtra(MainActivity.L_NAME);
        age = callingIntent.getStringExtra(MainActivity.AGE);
        email = callingIntent.getStringExtra(MainActivity.EMAIL);
        phone = callingIntent.getStringExtra(MainActivity.PHONE);
        dob = callingIntent.getStringExtra(MainActivity.DATE);
        country = callingIntent.getStringExtra(MainActivity.COUNTRY);
        state = callingIntent.getStringExtra(MainActivity.STATE);

        fnamedisplayValue.setText(fname);
        lnamedisplayValue.setText(lname);
        agedisplayValue.setText(age);
        emaildisplayValue.setText(email);
        phonedisplayValue.setText(phone);
        birthdatedisplayValue.setText(dob);
        countrydisplayValue.setText(country);
        statedisplayValue.setText(state);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            finish();
        }
        return true;
    }
}