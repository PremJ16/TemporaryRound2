package com.example.basicinformationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class UserListAcitivity extends AppCompatActivity {

    private FrameLayout userslist_frame;
    private ArrayList<Map<String, Object>> users_list;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userslist);

        userslist_frame = (FrameLayout) findViewById(R.id.userslist_frame);

        db = FirebaseFirestore.getInstance();
        users_list=new ArrayList<Map<String, Object>>();

        db.collection(MainActivity.USERS).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot doc: task.getResult()) {
                                users_list.add(doc.getData());
                                Log.i(MainActivity.LOG_TAG, doc.getData().toString());
                            }
                            UsersListFragment usersListFragment = new UsersListFragment(users_list);
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.add(R.id.userslist_frame, usersListFragment).commit();
                        } else {
                            Log.i(MainActivity.LOG_TAG, "Error in loading Users", task.getException());
                        }
                    }
                });




    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {


            finish();
        }
        return true;
    }

}