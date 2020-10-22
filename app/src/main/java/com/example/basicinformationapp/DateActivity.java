package com.example.basicinformationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class DateActivity extends AppCompatActivity {
public Button donebutton;
public Button cancelbutton;
private int currentday;
private int currentmonth;
private int currentyear;

DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        donebutton=(Button) findViewById(R.id.c_donebutton);
        cancelbutton=(Button) findViewById(R.id.cancelbutton);
        datePicker=(DatePicker) findViewById(R.id.datepicker);
        currentday=datePicker.getDayOfMonth();
        currentmonth=datePicker.getMonth()+1;
        currentyear=datePicker.getYear();

        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day=datePicker.getDayOfMonth();
                int month=datePicker.getMonth()+1;
                int year=datePicker.getYear();
                boolean validDate=true;
                if(year<=currentyear){
                    if(month<=currentmonth){
                        if(day<=currentday){
                           validDate=true;
                        }
                        else {
                            validDate=false;
                        }
                    }
                    else {
                        validDate=false;
                    }
                }
                else {
                    validDate=false;
                }
                if(validDate==true){
                    String date=Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
                    Intent intent=new Intent();
                    intent.putExtra(MainActivity.DAY_TAG, day);
                    intent.putExtra(MainActivity.MONTH_TAG, month);
                    intent.putExtra(MainActivity.YEAR_TAG, year);
                    setResult(MainActivity.RQ_CODE,intent);
                    finish();
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Invaid Date",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(MainActivity.RQ_CODE,null);
                finish();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MainActivity.LOG_TAG, "DateActivity : onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MainActivity.LOG_TAG, "DateActivity : onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MainActivity.LOG_TAG, "DateActivity : onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MainActivity.LOG_TAG, "DateActivity : onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MainActivity.LOG_TAG, "DateActivity : onDestroy");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            setResult(MainActivity.RQ_CODE,null);
            finish();
        }
        return true;
    }
}