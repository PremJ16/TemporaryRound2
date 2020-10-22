package com.example.basicinformationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountryActivity extends AppCompatActivity {
    public Button donebutton;
    public Button cancelbutton;


    public static int COUNTRY_SELECTION = 1;
    public static final int STATE_SELECTION = 2;
    public static final int DONE_SELECTION = 3;
    public static final String IND_CODE = "India";
    public static final String CHN_CODE = "China";
    public static final String USA_CODE = "USA";
    public static final String MEX_CODE = "Mexico";
    FrameLayout fragmentFrame;
    CountryFragment countryFragment;
    public int selection_status;
    StateFragment stateFragment;
    public String stateSelected;
    public String countrySelected;
    public TextView selectionShow;
    public ArrayList<Map<String, Object>> countries;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country2);
        donebutton = (Button) findViewById(R.id.c_donebutton);
        cancelbutton = (Button) findViewById(R.id.c_cancelbutton);
        fragmentFrame = (FrameLayout) findViewById(R.id.fragmentFrame);
        selectionShow=(TextView)findViewById(R.id.selection_show);
        countries= new ArrayList<>();
        db=FirebaseFirestore.getInstance();

        selection_status=COUNTRY_SELECTION;
        selectionShow.setText("");

        db.collection(MainActivity.COUNTRIES).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<String> countriesNames=new ArrayList<>();
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot country: task.getResult()) {
                                countries.add(country.getData());
                                countriesNames.add(country.getData().get(MainActivity.COUNTRY_NAME_TAG).toString());
                                //Log.i(MainActivity.LOG_TAG, country.getData().toString());

                            }
                            countryFragment=new CountryFragment(countriesNames);
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.add(R.id.fragmentFrame, countryFragment).commit();
                        } else {
                            Log.i(MainActivity.LOG_TAG, "Error in loading Users", task.getException());
                        }
                    }
                });


        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection_status == CountryActivity.DONE_SELECTION) {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.COUNTRY_TAG, countrySelected);
                    intent.putExtra(MainActivity.STATE_TAG, stateSelected);
                    setResult(MainActivity.COUNTRY_RQ_CODE, intent);
                    finish();

                }


            }
        });
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void showStatesFragment(String country) {
        Log.i(MainActivity.LOG_TAG, country);
        ArrayList<String> states=new ArrayList<>();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (country.equals(CHN_CODE)) {
            for(Object obj: (ArrayList<Object>)countries.get(0).get(MainActivity.COUNTRY_STATES_TAG)) {
                states.add(obj.toString());
            }

        } else if (country.equals(IND_CODE)) {
            for(Object obj: (ArrayList<Object>)countries.get(1).get(MainActivity.COUNTRY_STATES_TAG)) {
                states.add(obj.toString());
            }
        } else if (country.equals(MEX_CODE)) {
            for(Object obj: (ArrayList<Object>)countries.get(2).get(MainActivity.COUNTRY_STATES_TAG)) {
                states.add(obj.toString());
            }
        } else if (country.equals(USA_CODE)) {
            for(Object obj: (ArrayList<Object>)countries.get(3).get(MainActivity.COUNTRY_STATES_TAG)) {
                states.add(obj.toString());
            }
        }
        stateFragment=new StateFragment(states);
        if (stateFragment != null) {
            fragmentTransaction.replace(R.id.fragmentFrame, stateFragment).commit();
        } else {
            Log.i(MainActivity.LOG_TAG, "Statefragment is null");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(MainActivity.COUNTRY_RQ_CODE, null);
            finish();
        }
        return true;
    }
}
