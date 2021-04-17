package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CustomDrawerLayoutExpandableCardAdmin extends BottomSheetDialogFragment {

    LinearLayout expandableView;
    Button arrowBtn, btn_adminProfileStaff_createNotice;
    CardView cardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_notifcationadmin, container, false);

        btn_adminProfileStaff_createNotice = v.findViewById(R.id.btn_adminProfileStaff_createNotice);
        btn_adminProfileStaff_createNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDrawerLayoutExpandableCardAdmin_createNotice admin_createNotice = new CustomDrawerLayoutExpandableCardAdmin_createNotice();
                admin_createNotice.setCancelable(false);
                admin_createNotice.show(getChildFragmentManager(), "AdderAdminNotice");
            }
        });

        expandableView = v.findViewById(R.id.notif_message);
        arrowBtn = v.findViewById(R.id.btn_expandComplaint);
        cardView = v.findViewById(R.id.card_notif);

        expandableView.setVisibility(View.GONE);
        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.iconarrowup);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.iconarrowdown);
                }
            }
        });

        return v;
    }
}