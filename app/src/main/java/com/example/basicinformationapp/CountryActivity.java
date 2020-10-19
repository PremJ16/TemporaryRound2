package com.example.basicinformationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country2);
        donebutton = (Button) findViewById(R.id.c_donebutton);
        cancelbutton = (Button) findViewById(R.id.c_cancelbutton);
        fragmentFrame = (FrameLayout) findViewById(R.id.fragmentFrame);
        countryFragment = new CountryFragment();

        selection_status=COUNTRY_SELECTION;


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentFrame, countryFragment).commit();


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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (country.equals(CHN_CODE)) {
            Log.i(MainActivity.LOG_TAG, "In china");
            stateFragment = new StateFragment(CHN_CODE);
        } else if (country.equals(IND_CODE)) {
            stateFragment = new StateFragment(IND_CODE);
        } else if (country.equals(MEX_CODE)) {
            stateFragment = new StateFragment(MEX_CODE);
        } else if (country.equals(USA_CODE)) {
            stateFragment = new StateFragment(USA_CODE);
        }
        //stateFragment=new StateFragment(IND_CODE);
        if (stateFragment != null) {
            fragmentTransaction.replace(R.id.fragmentFrame, stateFragment).commit();
        } else {
            Log.i(MainActivity.LOG_TAG, "Statefragment is null");
        }

    }

}
