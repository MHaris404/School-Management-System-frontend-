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


public class Activity_Student extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static FragmentManager fragmentManager;
    public CustomDrawerLayoutMatrix drawerLayout_Std;
    Toolbar toolbar_Std;
    NavigationView navigationView_Std;
    ActionBarDrawerToggle actionBarDrawerToggle_Std;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar_Std = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar_Std);

        fragmentManager = getSupportFragmentManager();

        drawerLayout_Std = findViewById(R.id.drawer_layoutStd);
        navigationView_Std = findViewById(R.id.nav_viewStd);
        navigationView_Std.setNavigationItemSelectedListener(Activity_Student.this);

        actionBarDrawerToggle_Std = new ActionBarDrawerToggle(Activity_Student.this, drawerLayout_Std, toolbar_Std, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout_Std.addDrawerListener(actionBarDrawerToggle_Std);
        actionBarDrawerToggle_Std.syncState();

        //default frag
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                    .replace(R.id.fragment_containerStd,
                            new Fragment_mainProfile(),
                            Utils_loginned.fragment_mainProfile)
                    .commit();

            navigationView_Std.setCheckedItem(R.id.menu_profileDrawer);
            Toast.makeText(this, "Instance", Toast.LENGTH_SHORT).show();
        }
    }

    public void closeDrawerEvent(View v) {
        drawerLayout_Std.closeDrawer(GravityCompat.START);
    }

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar_notifications, menu);

        Drawable drawable = menu.findItem(R.id.toolbar_iconNotif).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(Activity_Student.this, R.color.colorWhite));
        menu.findItem(R.id.toolbar_iconNotif).setIcon(drawable);

        super.onCreateOptionsMenu(menu);
        return true;
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbar_iconNotif) {
            new CustomDrawerLayoutExpandableCard().show(getSupportFragmentManager(), "DialogNotifications");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profileDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainProfile()
                                , Utils_loginned.fragment_mainProfile)
                        .commit();
                break;
            case R.id.menu_feesDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainFees()
                                , Utils_loginned.Fragment_mainFees)
                        .commit();
                break;
            case R.id.menu_libraryDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainLibrary()
                                , Utils_loginned.Fragment_mainLibrary)
                        .commit();
                break;
            case R.id.menu_examDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainExam()
                                , Utils_loginned.Fragment_mainExam)
                        .commit();
                break;
            case R.id.menu_classDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainClass()
                                , Utils_loginned.Fragment_mainClass)
                        .commit();
                break;
            case R.id.menu_hostelDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainHostel()
                                , Utils_loginned.Fragment_mainHostel)
                        .commit();
                break;
            case R.id.menu_transportDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainTransport()
                                , Utils_loginned.Fragment_mainTransport)
                        .commit();
                break;
            case R.id.menu_gradeDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerStd, new Fragment_mainGrade()
                                , Utils_loginned.Fragment_mainGrade)
                        .commit();
                break;
            case R.id.menu_logoutDrawer:
                //code to logout
                Intent intent = new Intent(Activity_Student.this, Activity_login.class);
                startActivity(intent);
                finish();
        }

        drawerLayout_Std.closeDrawer(GravityCompat.START);
        return true;
    }

    // Replace profile Fragment with animation
    protected void replaceFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.fragment_containerStd, new Fragment_mainProfile(),
                        Utils_loginned.fragment_mainProfile)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment Fragment_mainProfile = fragmentManager.findFragmentByTag(Utils_loginned.fragment_mainProfile);

        if (drawerLayout_Std.isDrawerOpen(GravityCompat.START)) {
            drawerLayout_Std.closeDrawer(GravityCompat.START);
        } else if (Fragment_mainProfile != null && Fragment_mainProfile.isVisible()) {
            //do nothing
            return;
        } else {
            Fragment Fragment_mainFees = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_mainFees);
            Fragment Fragment_mainLibrary = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_mainLibrary);
            Fragment Fragment_mainExam = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_mainExam);
            Fragment Fragment_mainClass = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_mainClass);
            Fragment Fragment_mainHostel = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_mainHostel);
            Fragment Fragment_mainTransport = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_mainTransport);
            Fragment Fragment_mainGrade = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_mainGrade);

            if (Fragment_mainFees != null)
                replaceFragment();
            else if (Fragment_mainLibrary != null)
                replaceFragment();
            else if (Fragment_mainExam != null)
                replaceFragment();
            else if (Fragment_mainClass != null)
                replaceFragment();
            else if (Fragment_mainHostel != null)
                replaceFragment();
            else if (Fragment_mainTransport != null)
                replaceFragment();
            else if (Fragment_mainGrade != null)
                replaceFragment();
        }

    }
}
