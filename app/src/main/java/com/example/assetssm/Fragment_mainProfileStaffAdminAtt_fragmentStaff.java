package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Fragment_mainProfileStaffAdminAtt_fragmentStaff extends Fragment {

    View view;
    LinearLayout mainProfileAdmin_staff_levelLow_expendedView, mainProfileAdmin_staff_levelMedium_expendedView, mainProfileAdmin_staff_levelHigh_expendedView;
    Button mainProfileAdmin_staff_levelLow_btnMore, mainProfileAdmin_staff_levelMedium_btnMore, mainProfileAdmin_staff_levelHigh_btnMore;
    CardView mainProfileAdmin_staff_levelLow_cardView, mainProfileAdmin_staff_levelMedium_cardView, mainProfileAdmin_staff_levelHigh_cardView;

    LinearLayout mainProfileAdmin_staff_levelLow_month1_expendedView;
    CardView mainProfileAdmin_staff_levelLow_month1_cardView;
    Button mainProfileAdmin_staff_levelLow_month1_btnMore;

    public Fragment_mainProfileStaffAdminAtt_fragmentStaff() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofilestaffadminatt_fragmentstaff, container, false);

        mainProfileAdmin_staff_levelLow_cardView = view.findViewById(R.id.mainProfileAdmin_staff_levelLow_cardView);
        mainProfileAdmin_staff_levelLow_expendedView = view.findViewById(R.id.mainProfileAdmin_staff_levelLow_expendedView);
        mainProfileAdmin_staff_levelLow_btnMore = view.findViewById(R.id.mainProfileAdmin_staff_levelLow_btnMore);
        More_levelLow(mainProfileAdmin_staff_levelLow_expendedView, mainProfileAdmin_staff_levelLow_btnMore, mainProfileAdmin_staff_levelLow_cardView);

        mainProfileAdmin_staff_levelMedium_cardView = view.findViewById(R.id.mainProfileAdmin_staff_levelMedium_cardView);
        mainProfileAdmin_staff_levelMedium_expendedView = view.findViewById(R.id.mainProfileAdmin_staff_levelMedium_expendedView);
        mainProfileAdmin_staff_levelMedium_btnMore = view.findViewById(R.id.mainProfileAdmin_staff_levelMedium_btnMore);
        More_levelMedium(mainProfileAdmin_staff_levelMedium_expendedView, mainProfileAdmin_staff_levelMedium_btnMore, mainProfileAdmin_staff_levelMedium_cardView);

        mainProfileAdmin_staff_levelHigh_cardView = view.findViewById(R.id.mainProfileAdmin_staff_levelHigh_cardView);
        mainProfileAdmin_staff_levelHigh_expendedView = view.findViewById(R.id.mainProfileAdmin_staff_levelHigh_expendedView);
        mainProfileAdmin_staff_levelHigh_btnMore = view.findViewById(R.id.mainProfileAdmin_staff_levelHigh_btnMore);
        More_levelHigh(mainProfileAdmin_staff_levelHigh_expendedView, mainProfileAdmin_staff_levelHigh_btnMore, mainProfileAdmin_staff_levelHigh_cardView);

        mainProfileAdmin_staff_levelLow_month1_cardView = view.findViewById(R.id.mainProfileAdmin_staff_levelLow_month1_cardView);
        mainProfileAdmin_staff_levelLow_month1_expendedView = view.findViewById(R.id.mainProfileAdmin_staff_levelLow_month1_expendedView);
        mainProfileAdmin_staff_levelLow_month1_btnMore = view.findViewById(R.id.mainProfileAdmin_staff_levelLow_month1_btnMore);
        More_levelLow_month1(mainProfileAdmin_staff_levelLow_month1_expendedView, mainProfileAdmin_staff_levelLow_month1_btnMore, mainProfileAdmin_staff_levelLow_month1_cardView);

        return view;
    }

    private void More_levelLow(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainGrade instBookMore = new Fragment_mainGrade();
                instBookMore.RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void More_levelMedium(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainGrade instBookMore = new Fragment_mainGrade();
                instBookMore.RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void More_levelHigh(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainGrade instBookMore = new Fragment_mainGrade();
                instBookMore.RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void More_levelLow_month1(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainGrade instBookMore = new Fragment_mainGrade();
                instBookMore.RecordsDetailsExpandable(a, b, c);
            }
        });
    }

}

