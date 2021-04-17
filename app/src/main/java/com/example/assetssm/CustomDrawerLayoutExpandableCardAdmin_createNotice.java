package com.example.assetssm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomDrawerLayoutExpandableCardAdmin_createNotice extends AppCompatDialogFragment {

    Spinner spn_aud_cat, spn_aud_subcat;
    List<Integer> arr_aud_cat, arr_aud_subcat;
    ArrayAdapter<Integer> arr_adp_aud_cat, arr_adp_aud_subcat;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_notificationadmin_createnotice, null);

        spn_aud_cat = view.findViewById(R.id.spn_aud_cat);
        spn_aud_subcat = view.findViewById(R.id.spn_aud_subcat);
        aud_cat();
        aud_subcat();

        builder.setView(view)
                .setTitle("Broadcast Message")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    private void aud_cat() {
        arr_aud_cat = new ArrayList<Integer>();
        arr_aud_cat.add(10);
        arr_aud_cat.add(9);

        arr_adp_aud_cat = new ArrayAdapter<Integer>(getActivity(), R.layout.spn_itemcustom, arr_aud_cat);
        spn_aud_cat.setAdapter(arr_adp_aud_cat);
        spn_aud_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void aud_subcat() {
        arr_aud_subcat = new ArrayList<Integer>();
        arr_aud_subcat.add(10);
        arr_aud_subcat.add(9);

        arr_adp_aud_subcat = new ArrayAdapter<Integer>(getActivity(), R.layout.spn_itemcustom, arr_aud_subcat);
        spn_aud_subcat.setAdapter(arr_adp_aud_subcat);
        spn_aud_subcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


}