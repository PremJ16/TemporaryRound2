package com.example.basicinformationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                    intent.putExtra(MainActivity.DATE_TAG,date);
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
                Intent intent=new Intent();
                //intent.putExtra(MainActivity.DATE_TAG,date);
                setResult(MainActivity.RQ_CODE,intent);
                finish();
            }
        });
    }
}