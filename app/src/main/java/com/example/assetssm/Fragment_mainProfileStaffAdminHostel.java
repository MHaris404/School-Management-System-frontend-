package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment_mainProfileStaffAdminHostel extends Fragment {

    Toolbar toolbar;
    TextView txt_toolbarTitle;

    LinearLayout expandableView;
    Button arrowBtn, staff_admin_hostel_btnStdEnrolled;
    CardView cardView;

    FloatingActionButton FAB_admin_hostelCampusAdder;

    Fragment_mainProfileStaffAdminLibrary adminLibrary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadminhostel, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Hostel");

        expandableView = view.findViewById(R.id.staff_admin_hostel_expendedView);
        cardView = view.findViewById(R.id.staff_admin_hostel_cardView);
        arrowBtn = view.findViewById(R.id.staff_admin_hostel_btnMore);
        hostelMore(expandableView, arrowBtn, cardView);
        staff_admin_hostel_btnStdEnrolled = view.findViewById(R.id.staff_admin_hostel_btnStdEnrolled);
        StdsEnrolled();

        FAB_admin_hostelCampusAdder = view.findViewById(R.id.FAB_admin_hostelCampusAdder);
        roomAdder();

        return view;
    }

    private void StdsEnrolled() {
        staff_admin_hostel_btnStdEnrolled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                adminLibrary= new Fragment_mainProfileStaffAdminLibrary();
//                adminLibrary.preference_general(2);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("general", 2);
                editor.commit();

                Intent intent = new Intent(getActivity(), Activity_mainProfileStaffAdmin_general.class);
                startActivity(intent);
            }
        });
    }

    private void hostelMore(LinearLayout a, Button b, CardView c) {
        //addedView.setVisibility(View.GONE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainProfile2 fragment_mainProfile2 = new Fragment_mainProfile2();
                fragment_mainProfile2.ProfileDetailsExpandable(a, b, c);
            }
        });
    }

    private void roomAdder() {
        FAB_admin_hostelCampusAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainProfileStaffAdminHostel_hostelCampusAdder instAdder = new Fragment_mainProfileStaffAdminHostel_hostelCampusAdder();
                instAdder.setCancelable(false);
                instAdder.show(getChildFragmentManager(), "Adder");
            }
        });
    }
}
