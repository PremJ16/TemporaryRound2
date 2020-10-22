package com.example.basicinformationapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

public class UsersListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    ArrayList<Map<String, Object>> usersList;
    SimpleAdapter adapter;

    public UsersListFragment(ArrayList<Map<String, Object>> list) {
        usersList = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] from={MainActivity.F_NAME, MainActivity.PHONE, MainActivity.COUNTRY};
        int[] to={R.id.user_name, R.id.user_phone, R.id.user_country};
        adapter=new SimpleAdapter(getActivity().getBaseContext(), usersList, R.layout.userslist_model, from, to);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String, Object> userData= usersList.get(i);
        //Log.i(MainActivity.LOG_TAG, userData.toString());
        Intent intent=new Intent(getActivity(), OnclickUserActivity.class);
        intent.putExtra(MainActivity.F_NAME, userData.get(MainActivity.F_NAME).toString());
        intent.putExtra(MainActivity.L_NAME, userData.get(MainActivity.L_NAME).toString());
        intent.putExtra(MainActivity.AGE, userData.get(MainActivity.AGE).toString());
        intent.putExtra(MainActivity.EMAIL, userData.get(MainActivity.EMAIL).toString());
        intent.putExtra(MainActivity.PHONE, userData.get(MainActivity.PHONE).toString());
        intent.putExtra(MainActivity.DATE, userData.get(MainActivity.DATE).toString());
        intent.putExtra(MainActivity.COUNTRY, userData.get(MainActivity.COUNTRY).toString());
        intent.putExtra(MainActivity.STATE, userData.get(MainActivity.STATE).toString());
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getListView().setOnItemClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

}