package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class Fragment_mainProfileParent extends Fragment {
    View view;
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofileparent, container, false);

//        (( AppCompatActivity ) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar = getActivity().findViewById(R.id.toolbarMain);
//        txt_toolbarTitle = toolbar.findViewById(R.id.toolbarMain_title);
//        txt_toolbarTitle.setText("Parent Profile");

        return view;
    }
}
