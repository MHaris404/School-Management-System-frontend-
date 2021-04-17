package com.example.assetssm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class Activity_mainProfileStaffAdmin_general extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainprofilestaffadmin_general);

        toolbar = findViewById(R.id.toolbarMain);
        txt_toolbarTitle = toolbar.findViewById(R.id.toolbarMain_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragmentManager = getSupportFragmentManager();

        //default frag
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int allvalue = preferences.getInt("general", -1);
        switch (allvalue) {
            case 1:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.activity_mainProfileStaffAdmin_general_container,

                                    new Fragment_mainProfileStaffAdminLibrary_bookIssuedTo(),
                                    Utils_loginned.fragment_mainProfileStaffAdminLibrary_bookIssuedTo)
                            .commit();
                    break;
                }
            case 2:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.activity_mainProfileStaffAdmin_general_container,

                                    new Fragment_mainProfileStaffAdminHostel_stdsEnrolled(),
                                    Utils_loginned.fragment_mainProfileStaffAdminHostel_stdsEnrolled)
                            .commit();
                    break;
                }
            case 3:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.activity_mainProfileStaffAdmin_general_container,

                                    new Fragment_mainProfileStaffAdminTimetable_tTableGenerator(),
                                    Utils_loginned.fragment_mainProfileStaffAdminTimetable_tTableGenerator)
                            .commit();
                    break;
                }
            case 4:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.activity_mainProfileStaffAdmin_general_container,

                                    new Fragment_mainProfileStaffAdminTimetable_tTableGenerator(),
                                    Utils_loginned.fragment_mainProfileStaffAdminTimetable_tTableGenerator_2AfterDataGet)
                            .commit();
                    break;
                }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
