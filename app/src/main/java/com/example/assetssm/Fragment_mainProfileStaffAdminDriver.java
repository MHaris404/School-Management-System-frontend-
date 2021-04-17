package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Fragment_mainProfileStaffAdminDriver extends Fragment {
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    SearchView sView_book;

    LinearLayout expandableView;
    Button arrowBtn, staff_admin_driver_btnGetLocation;
    CardView cardView;

    Fragment_mainProfileStaffAdminOverall fragment_mainProfileStaffAdminOverall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadmindriver, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Driver");

        sView_book = view.findViewById(R.id.searchview_admin_driver);
        //search(searchView);

        staff_admin_driver_btnGetLocation = view.findViewById(R.id.staff_admin_driver_btnGetLocation);
        staff_admin_driver_btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                fragment_mainProfileStaffAdminOverall = new Fragment_mainProfileStaffAdminOverall();
//                fragment_mainProfileStaffAdminOverall.preference(4);
//
//                Intent intentDriverLocation = new Intent(getActivity(),Activity_mainOfficerList_cardProfile.class);
//                startActivity(intentDriverLocation);

            }
        });

        expandableView = view.findViewById(R.id.staff_admin_driver_expendedView);
        cardView = view.findViewById(R.id.staff_admin_driver_cardView);
        arrowBtn = view.findViewById(R.id.staff_admin_driver_btnMore);
        bookMore(expandableView, arrowBtn, cardView);

        return view;
    }

    private void bookMore(LinearLayout a, Button b, CardView c) {
        //addedView.setVisibility(View.GONE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainProfile2 fragment_mainProfile2 = new Fragment_mainProfile2();
                fragment_mainProfile2.ProfileDetailsExpandable(a, b, c);
            }
        });
    }
}

