package com.example.basicinformationapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public Button setbutton;
public TextView birthdateValue;
public static final int RQ_CODE=2;
public static final String DATE_TAG="dateTag";
public Button selectbutton;
public static final String COUNTRY_TAG="countryTag";
public static final String STATE_TAG="stateTag";
public static final int COUNTRY_RQ_CODE=4;
private String country;
private String state;
public TextView countryState;
public static final String LOG_TAG="logtag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setbutton=(Button) findViewById(R.id.setbutton1);
        birthdateValue=(TextView) findViewById(R.id.birthdatevalue);
        selectbutton=(Button) findViewById(R.id.setbutton2);
        countryState=(TextView) findViewById(R.id.countryValue) ;

        setbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DateActivity.class);
                startActivityForResult(intent,RQ_CODE);
            }
        });
        selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,CountryActivity.class);
                startActivityForResult(intent1,COUNTRY_RQ_CODE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RQ_CODE){
            String msg=data.getStringExtra(DATE_TAG);
            if(msg!=null){
                birthdateValue.setText(msg);
            }
        }
        else if(requestCode==COUNTRY_RQ_CODE){
            Log.i(LOG_TAG, "onActivityResult:in country if ");
            country=data.getStringExtra(COUNTRY_TAG);
            state=data.getStringExtra(STATE_TAG);
            countryState.setText(country+" , "+state);

        }
    }
}