package com.example.basicinformationapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CountryFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private ArrayAdapter adapter;
    private CountryActivity root_activity;
    private ArrayList<String> countriesList;

    public CountryFragment(ArrayList<String> countries) {
        countriesList=countries;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        root_activity=(CountryActivity) getActivity();
        View view=inflater.inflate(R.layout.listlayout,container,false);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String country=adapter.getItem(position).toString();
        Toast.makeText(getActivity(),"Item: "+country,Toast.LENGTH_SHORT).show();
        root_activity.countrySelected=country;
        root_activity.selection_status=CountryActivity.STATE_SELECTION;
        root_activity.selectionShow.setText(country);
        root_activity.showStatesFragment(country);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter= new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, countriesList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

}
