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
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Fragment_mainProfileStaffAdminLibrary extends Fragment {
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    SearchView sView_book;

    LinearLayout expandableView;
    Button arrowBtn;
    CardView cardView;

    Button staff_admin_book_btnIssuedTo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadminlibrary, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Library");

        sView_book = view.findViewById(R.id.searchview_admin_bookGrade);
        //search(searchView);

        expandableView = view.findViewById(R.id.staff_admin_book_expendedView);
        cardView = view.findViewById(R.id.staff_admin_book_cardView);
        arrowBtn = view.findViewById(R.id.staff_admin_book_btnMore);
        bookMore(expandableView, arrowBtn, cardView);

        staff_admin_book_btnIssuedTo = view.findViewById(R.id.staff_admin_book_btnIssuedTo);
        staff_admin_book_btnIssuedTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference_general(1);
                Intent intent = new Intent(getActivity(), Activity_mainProfileStaffAdmin_general.class);
                startActivity(intent);
            }
        });

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

    public void preference_general(int val) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("general", val);
        editor.commit();
    }
}

