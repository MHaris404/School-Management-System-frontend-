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
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class Activity_Driver extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static FragmentManager fragmentManager;
    public CustomDrawerLayoutMatrix drawerLayout_Dvr;
    Toolbar toolbar_Dvr;
    NavigationView navigationView_Dvr;
    ActionBarDrawerToggle actionBarDrawerToggle_Dvr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        toolbar_Dvr = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar_Dvr);

        fragmentManager = getSupportFragmentManager();

        drawerLayout_Dvr = findViewById(R.id.drawer_layoutDvr);
        navigationView_Dvr = findViewById(R.id.nav_viewDvr);
        navigationView_Dvr.setNavigationItemSelectedListener(Activity_Driver.this);

        actionBarDrawerToggle_Dvr = new ActionBarDrawerToggle(Activity_Driver.this, drawerLayout_Dvr, toolbar_Dvr, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout_Dvr.addDrawerListener(actionBarDrawerToggle_Dvr);
        actionBarDrawerToggle_Dvr.syncState();

        //default frag
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                    .replace(R.id.fragment_containerDvr, new Fragment_mainTransport()
                            , Utils_loginned.Fragment_mainTransport)
                    .commit();

            navigationView_Dvr.setCheckedItem(R.id.menu_profileDrawer);
            Toast.makeText(this, "Instance", Toast.LENGTH_SHORT).show();
        }

    }

    public void closeDrawerEvent(View v) {
        drawerLayout_Dvr.closeDrawer(GravityCompat.START);
    }

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar_notifications, menu);

        Drawable drawable = menu.findItem(R.id.toolbar_iconNotif).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(Activity_Driver.this, R.color.colorWhite));
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
            case R.id.menu_MapDrawer:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerDvr, new Fragment_mainTransport()
                                , Utils_loginned.Fragment_mainTransport)
                        .commit();
                break;
            case R.id.menu_logoutDrawer:
                //code to logout
                Intent intent = new Intent(Activity_Driver.this, Activity_login.class);
                startActivity(intent);
                finish();
        }

        drawerLayout_Dvr.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout_Dvr.isDrawerOpen(GravityCompat.START)) {
            drawerLayout_Dvr.closeDrawer(GravityCompat.START);
        }
    }
}
