package com.example.basicinformationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    public static final String USERS = "Users";
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
        age = (EditText) findViewById(R.id.AgeValue);
        email = (EditText) findViewById(R.id.EmailValue);
        phoneNo = (EditText) findViewById(R.id.editTextPhone);
        submit = (Button) findViewById(R.id.submitbutton);
        db = FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> data = wrapData();

                if (data != null) {
                    db.collection(USERS).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failure in saving data", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
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
                countryState.setText(country + ", " + state);
            }

        }
    }

    public Map<String, String> wrapData() {
        Log.i(LOG_TAG, "Inside wrapData()");
        Map<String, String> data = new HashMap<>();
        if (firstname.getText() == null || firstname.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error with First Name", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            data.put(F_NAME, firstname.getText().toString());
        }
        if (lastname.getText() == null || lastname.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error with Last Name", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            data.put(L_NAME, lastname.getText().toString());
        }
        if (age.getText() == null || age.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error with Age", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            data.put(AGE, age.getText().toString());
        }
        if (email.getText() == null || email.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error with Email", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            data.put(EMAIL, email.getText().toString());
        }
        if (phoneNo.getText() == null || phoneNo.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error with Phone", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            data.put(PHONE, phoneNo.getText().toString());
        }
        if (birthdateValue.getText() == null || birthdateValue.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error with Birth Date", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            data.put(DATE, birthdateValue.getText().toString());
        }
        if (countryState.getText() == null || countryState.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error with Country and State", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (country == null || country.equals("")) {
            return null;
        } else {
            data.put(COUNTRY, country);
        }
        if (state == null || state.equals("")) {
            return null;
        } else {
            data.put(STATE, state);
        }
        Log.i(LOG_TAG, data.toString());
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuitem_mainactivity_userslist:
                intent = new Intent(MainActivity.this, UserListAcitivity.class);
                startActivity(intent);
                break;
            case R.id.menuitem_mainactivity_dobactivity:
                intent = new Intent(MainActivity.this, DateActivity.class);
                startActivityForResult(intent, RQ_CODE);
                break;
            case R.id.menuitem_mainactivity_countryactivity:
                intent = new Intent(MainActivity.this, CountryActivity.class);
                startActivityForResult(intent, COUNTRY_RQ_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}