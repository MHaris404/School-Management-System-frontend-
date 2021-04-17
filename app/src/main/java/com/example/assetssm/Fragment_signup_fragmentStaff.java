package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class Fragment_signup_fragmentStaff extends Fragment {
    private static TextView textToolHeader;
    View view;
    Toolbar toolbar;

    public Fragment_signup_fragmentStaff() {
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item=menu.findItem(R.id.item1);
//        if(item!=null)
//            item.setVisible(false);
        menu.clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarLogin);
        textToolHeader = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textToolHeader.setText("Apply for Appointment");
        setHasOptionsMenu(true);

        view = inflater.inflate(R.layout.fragment_signup_fragmentstaff, container, false);
        return view;
    }
}
