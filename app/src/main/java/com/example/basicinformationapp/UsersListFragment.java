package com.example.basicinformationapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
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
        Intent intent=new Intent(getActivity(), OnclickUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getListView().setOnItemClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }
}