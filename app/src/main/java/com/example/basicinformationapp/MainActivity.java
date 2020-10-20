package com.example.basicinformationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public Button setbutton;
    public Button selectbutton;
    public static final int RQ_CODE = 2;
    public static final String DATE_TAG = "dateTag";
    public static final String F_NAME = "firstname";
    public static final String L_NAME = "lastname";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String PHONE = "phoneNo";
    public static final String DATE = "birthdate";
    public static final String COUNTRY = "country";
    public static final String STATE = "state";
    public static final String COUNTRY_TAG = "countryTag";
    public static final String STATE_TAG = "stateTag";
    public static final String USERS="Users";
    public static final int COUNTRY_RQ_CODE = 4;
    private String country;
    private String state;
    public static final String LOG_TAG = "logtag";
    public EditText firstname;
    public EditText lastname;
    public EditText age;
    public EditText email;
    public EditText phoneNo;
    public TextView birthdateValue;
    public TextView countryState;
    public Button submit;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setbutton = (Button) findViewById(R.id.setbutton1);
        birthdateValue = (TextView) findViewById(R.id.birthdatevalue);
        selectbutton = (Button) findViewById(R.id.setbutton2);
        countryState = (TextView) findViewById(R.id.countryValue);
        firstname = (EditText) findViewById(R.id.FirstNameValue);
        lastname = (EditText) findViewById(R.id.FamilyLastNameValue);
        email = (EditText) findViewById(R.id.EmailValue);
        phoneNo = (EditText) findViewById(R.id.editTextPhone);
        submit = (Button) findViewById(R.id.submitbutton);
        db= FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();


            }
        });


        setbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DateActivity.class);
                startActivityForResult(intent, RQ_CODE);
            }
        });
        selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, CountryActivity.class);
                startActivityForResult(intent1, COUNTRY_RQ_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RQ_CODE) {
            String msg = data.getStringExtra(DATE_TAG);
            if (msg != null) {
                birthdateValue.setText(msg);
            }
        } else if (requestCode == COUNTRY_RQ_CODE) {
            Log.i(LOG_TAG, "onActivityResult:in country if ");
            if (data != null) {
                country = data.getStringExtra(COUNTRY_TAG);
                state = data.getStringExtra(STATE_TAG);
                countryState.setText(country + " , " + state);
            }

        }
    }

    public void saveData() {
        boolean validData = true;
        String fname = firstname.getText().toString();
        validData = fname.equals("") ? false : validData;
        String lname = lastname.getText().toString();
        validData = lname.equals("") ? false : validData;
        String agevalue = age.getText().toString();
        validData = Integer.parseInt(agevalue) < 0 ? false : validData;
        String emailId = email.getText().toString();
        validData = email.equals("") ? false : validData;
        String phone = phoneNo.getText().toString();
        validData = phoneNo.equals("") ? false : validData;
        String birthdate = birthdateValue.getText().toString();
        validData = birthdate.equals("") ? false : validData;

        if(validData==true){
            Map<String, String> data = new HashMap<>();
            data.put(F_NAME, fname);
            data.put(L_NAME, lname);
            data.put(AGE, agevalue);
            data.put(EMAIL, emailId);
            data.put(PHONE, phone);
            data.put(DATE, birthdate);
            data.put(COUNTRY, country);
            data.put(STATE,state);

            Log.i(LOG_TAG,data.toString());

            db.collection(USERS).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                   Log.i(LOG_TAG,"Success");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i(LOG_TAG,"Failure");

                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Data",Toast.LENGTH_SHORT).show();

        }

    }
}