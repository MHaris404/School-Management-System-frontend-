package com.example.assetssm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class Activity_mainOfficerList_cardProfile extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    public CustomDrawerLayoutMatrix drawerLayout_Std;
    Toolbar toolbar_Std;
    NavigationView navigationView_Std;
    ActionBarDrawerToggle actionBarDrawerToggle_Std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainofficerlist_cardprofile);

        toolbar_Std = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar_Std);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragmentManager = getSupportFragmentManager();

        //default frag
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int allvalue = preferences.getInt("itemClickType", -1);
        switch (allvalue) {
            case 1:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile,
                                    new Fragment_mainOfficerList_cardProfile_std(),
                                    Utils_loginned.Fragment_mainOfficerList_cardProfile_std)
                            .commit();
                    break;
                }
            case 2:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile,
                                    new Fragment_mainOfficerList_cardProfile_prt(),
                                    Utils_loginned.Fragment_mainOfficerList_cardProfile_prt)
                            .commit();
                    break;

                }
            case 3:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile,
                                    new Fragment_mainOfficerList_cardProfile_emp(),
                                    Utils_loginned.Fragment_mainOfficerList_cardProfile_emp)
                            .commit();
                    break;
                }

        }
    }
}
