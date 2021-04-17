package com.example.assetssm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_mainOfficerList_cardProfile_std extends Fragment {
    private static View view;
    Button btn_officerlist_cardprofile_std;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainofficerlist_cardprofile_std, container, false);

        btn_officerlist_cardprofile_std = view.findViewById(R.id.btn_officerlist_cardprofile_std);
        btn_officerlist_cardprofile_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_mainOfficerList_cardProfile_more.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
