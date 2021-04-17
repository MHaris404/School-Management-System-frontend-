package com.example.assetssm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class Activity_Staff extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static FragmentManager fragmentManager;
    public CustomDrawerLayoutMatrix drawerLayout_SOff;
    Toolbar toolbar_SOff;
    NavigationView navigationView_SOff;
    ActionBarDrawerToggle actionBarDrawerToggle_SOff;

    Fragment_mainProfileStaffAdminOverall fmPSAo;
    Intent intentAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        toolbar_SOff = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar_SOff);

        fragmentManager = getSupportFragmentManager();

        drawerLayout_SOff = findViewById(R.id.drawer_layoutSOff);
        navigationView_SOff = findViewById(R.id.nav_viewSOff);
        navigationView_SOff.setNavigationItemSelectedListener(Activity_Staff.this);

        actionBarDrawerToggle_SOff = new ActionBarDrawerToggle(Activity_Staff.this, drawerLayout_SOff, toolbar_SOff, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout_SOff.addDrawerListener(actionBarDrawerToggle_SOff);
        actionBarDrawerToggle_SOff.syncState();

        //default frag
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                    .replace(R.id.fragment_containerSOff,
                            new Fragment_mainProfileStaffAdminOverall(),
                            Utils_loginned.fragment_mainProfileStaffAdminOverall)
                    .commit();

            navigationView_SOff.setCheckedItem(R.id.menu_mainStaffOverall_Drawer);
            Toast.makeText(this, "Instance", Toast.LENGTH_SHORT).show();
        }
    }


    public void closeDrawerEvent(View v) {
        drawerLayout_SOff.closeDrawer(GravityCompat.START);
    }

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar_notifications, menu);

        Drawable drawable = menu.findItem(R.id.toolbar_iconNotif).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(Activity_Staff.this, R.color.colorWhite));
        menu.findItem(R.id.toolbar_iconNotif).setIcon(drawable);

        super.onCreateOptionsMenu(menu);
        return true;
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbar_iconNotif) {
            new CustomDrawerLayoutExpandableCardAdmin().show(getSupportFragmentManager(), "DialogNotifications");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mainStaffProfile_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffOfficer()
                                , Utils_loginned.fragment_mainProfileStaffOfficer)
                        .commit();
                break;
            case R.id.menu_mainStaffOverall_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminOverall(),
                                Utils_loginned.fragment_mainProfileStaffAdminOverall)
                        .commit();
                break;
            case R.id.menu_mainStaffApp_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminApp(),
                                Utils_loginned.fragment_mainProfileStaffAdminApp)
                        .commit();
                break;
            case R.id.menu_mainStaffAtt_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminAtt(),
                                Utils_loginned.fragment_mainProfileStaffAdminAtt)
                        .commit();
                break;
            case R.id.menu_mainStaffSubj_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminSubj(),
                                Utils_loginned.fragment_mainProfileStaffAdminSubj)
                        .commit();
                break;
            case R.id.menu_mainStaffBook_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminBook(),
                                Utils_loginned.fragment_mainProfileStaffAdminBook)
                        .commit();
                break;
            case R.id.menu_mainStaffLibrary_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminLibrary(),
                                Utils_loginned.fragment_mainProfileStaffAdminLibrary)
                        .commit();
                break;
            case R.id.menu_mainStaffRoom_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminRoom(),
                                Utils_loginned.fragment_mainProfileStaffAdminRoom)
                        .commit();
                break;
            case R.id.menu_mainStaffGrade_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminGrade(),
                                Utils_loginned.fragment_mainProfileStaffAdminGrade)
                        .commit();
                break;
            case R.id.menu_mainStaffDriver_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminDriver(),
                                Utils_loginned.fragment_mainProfileStaffAdminDriver)
                        .commit();
                break;
            case R.id.menu_mainStaffHostel_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminHostel(),
                                Utils_loginned.fragment_mainProfileStaffAdminHostel)
                        .commit();
                break;
            case R.id.menu_mainStaffEnrollment_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminEnrollment(),
                                Utils_loginned.fragment_mainProfileStaffAdminEnrollment)
                        .commit();
                break;
            case R.id.menu_mainStaffTimeTable_Drawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerSOff,
                                new Fragment_mainProfileStaffAdminTimetable(),
                                Utils_loginned.fragment_mainProfileStaffAdminTimetable)
                        .commit();
                break;

            case R.id.menu_mainStafflogout_Drawer:
                //code to logout
                Intent intent = new Intent(Activity_Staff.this, Activity_login.class);
                startActivity(intent);
        }

        drawerLayout_SOff.closeDrawer(GravityCompat.START);
        return true;
    }

    // Replace profile Fragment with animation
    protected void replaceFragment() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                .replace(R.id.fragment_containerSOff, new Fragment_mainProfileStaffOfficer()
                        , Utils_loginned.fragment_mainProfileStaffOfficer)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment Fragment_mainProfileStaffOfficer = fragmentManager.findFragmentByTag(Utils_loginned.fragment_mainProfileStaffOfficer);

        if (drawerLayout_SOff.isDrawerOpen(GravityCompat.START)) {
            drawerLayout_SOff.closeDrawer(GravityCompat.START);
        } else if (Fragment_mainProfileStaffOfficer != null && Fragment_mainProfileStaffOfficer.isVisible()) {
            //do nothing
            return;
        } else {

        }
    }
}

