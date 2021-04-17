package com.example.assetssm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Activity_mainOfficerList_cardProfile_more extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    public CustomDrawerLayoutMatrix drawerLayout_Std;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter_Common viewPagerAdapter_common;
    Toolbar toolbar_Std;
    NavigationView navigationView_Std;
    ActionBarDrawerToggle actionBarDrawerToggle_Std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainofficerlist_cardprofile_more);

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
                            .replace(R.id.fragment_containerOfficer_cardprofile_more,

                                    new Fragment_mainProfileParentChilds(),
                                    Utils_loginned.fragment_mainProfileParentChilds)
                            .commit();
                    break;
                }
            case 2:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile_more,

                                    new Fragment_mainProfileParent(),
                                    Utils_loginned.fragment_mainProfileParent)
                            .commit();
                    break;

                }
            case 3:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile_more,

                                    /////////////////////////////////
                                    /////////////////////////////////
                                    /////////////////////////////////
                                    //chnage fragment
                                    /////////////////////////////////
                                    /////////////////////////////////

                                    new Fragment_mainProfileParent(),
                                    Utils_loginned.fragment_mainProfileParent)
                            .commit();
                    break;
                }
            case 4:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile_more,

                                    new Fragment_mainProfileStaffAdminApp_Dvr(),
                                    Utils_loginned.Fragment_mainProfileStaffAdminApp_Dvr)
                            .commit();
                    break;
                }
            case 5:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile_more,

                                    new Fragment_mainProfileStaffAdminApp_Std(),
                                    Utils_loginned.Fragment_mainProfileStaffAdminApp_Std)
                            .commit();
                    break;
                }
            case 6:
                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                            .replace(R.id.fragment_containerOfficer_cardprofile_more,

                                    new Fragment_mainProfileStaffAdminApp_Stf(),
                                    Utils_loginned.Fragment_mainProfileStaffAdminApp_Stf)
                            .commit();
                    break;
                }
        }
    }
}
