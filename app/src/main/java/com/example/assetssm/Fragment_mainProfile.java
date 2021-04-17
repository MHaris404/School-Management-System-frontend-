package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_mainProfile extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationViewStd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofile, container, false);

        //bottom nav
        bottomNavigationViewStd = view.findViewById(R.id.main_bottomnavigation_view);
        bottomNavigationViewStd.setOnNavigationItemSelectedListener(this);
        bottomNavigationViewStd.setSelectedItemId(R.id.menu_profile);

        //default frag
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                    .replace(R.id.fragment_container2,
                            new Fragment_mainProfile2(),
                            Utils_loginned.fragment_mainProfile2).commit();
            bottomNavigationViewStd.setSelectedItemId(R.id.menu_profile);
        }

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_profile:
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_container2, new Fragment_mainProfile2())
                        .commit();
                break;
            case R.id.menu_timetable:
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_container2, new Fragment_mainTimetable())
                        .commit();
                break;
            case R.id.menu_attendance:
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_container2, new Fragment_mainAttendance())
                        .commit();
                break;
        }
        return true;
    }

}
