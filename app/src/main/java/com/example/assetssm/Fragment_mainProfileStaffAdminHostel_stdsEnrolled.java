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
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Fragment_mainProfileStaffAdminHostel_stdsEnrolled extends Fragment {

    Toolbar toolbar;
    TextView txt_toolbarTitle;

    LinearLayout expandableView;
    Button arrowBtn;
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadminhostel_stdsenrolled, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);

        //campus Title
        //xt_toolbarTitle.setText("Hostel");

        expandableView = view.findViewById(R.id.staff_admin_hostel_stdsEnrolled_expendedView);
        cardView = view.findViewById(R.id.staff_admin_hostel_stdsEnrolled_cardView);
        arrowBtn = view.findViewById(R.id.staff_admin_hostel_stdsEnrolled_btnMore);
        stdEnrolledMore(expandableView, arrowBtn, cardView);

        return view;
    }

    private void stdEnrolledMore(LinearLayout a, Button b, CardView c) {
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
