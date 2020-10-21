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

public class StateFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private ArrayAdapter adapter;
    public static int selection_status;
    private CountryActivity root_activity;
    public String countryCode;
    private ArrayList<String> statesList;

    public StateFragment(ArrayList<String> states){

        this.countryCode=countryCode;
        this.statesList=states;
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
        String state=adapter.getItem(position).toString();
        String country=root_activity.countrySelected;
        Toast.makeText(getActivity(),"Item: "+state,Toast.LENGTH_SHORT).show();
        root_activity.stateSelected=state;
        root_activity.selectionShow.setText(country+", "+state);
        root_activity.selection_status=CountryActivity.DONE_SELECTION;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter= adapter= new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, statesList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

}
