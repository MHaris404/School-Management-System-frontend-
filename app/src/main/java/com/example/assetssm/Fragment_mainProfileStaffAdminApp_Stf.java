package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_mainProfileStaffAdminApp_Stf extends Fragment {
    private static View view;

    Button staffadminapp_staffAppoint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofilestaffadminapp_stf, container, false);

        staffadminapp_staffAppoint = view.findViewById(R.id.staffadminapp_staffAppoint);
        appointButton();

        return view;
    }

    private void appointButton() {
        staffadminapp_staffAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainProfileStaffAdminApp_Stf_appoint inst = new Fragment_mainProfileStaffAdminApp_Stf_appoint();
                inst.setCancelable(false);
                inst.show(getChildFragmentManager(), "Adder");
            }
        });
    }
}
