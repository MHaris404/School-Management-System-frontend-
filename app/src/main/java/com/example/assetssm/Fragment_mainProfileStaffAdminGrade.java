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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment_mainProfileStaffAdminGrade extends Fragment {
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    SearchView sView_book;

    LinearLayout expandableView;
    Button arrowBtn;
    CardView cardView;

    FloatingActionButton FAB_admin_gradeAdder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadmingrade, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Grade");

        sView_book = view.findViewById(R.id.searchview_admin_bookGrade);
        //search(searchView);

        expandableView = view.findViewById(R.id.staff_admin_grade_expendedView);
        cardView = view.findViewById(R.id.staff_admin_grade_cardView);
        arrowBtn = view.findViewById(R.id.staff_admin_grade_btnMore);
        bookMore(expandableView, arrowBtn, cardView);

        FAB_admin_gradeAdder = view.findViewById(R.id.FAB_admin_gradeSectionAdder);
        roomAdder();

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

    private void roomAdder() {
        FAB_admin_gradeAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainProfileStaffAdminGrade_gradeAdder instAdder = new Fragment_mainProfileStaffAdminGrade_gradeAdder();
                instAdder.setCancelable(false);
                instAdder.show(getChildFragmentManager(), "Adder");
            }
        });
    }

}

