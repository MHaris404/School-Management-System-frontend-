package com.example.assetssm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_Parent extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static FragmentManager fragmentManager;
    public CustomDrawerLayoutMatrix drawerLayout_Prt;
    Toolbar toolbar_Prt;
    TextView toolbarMain_title;
    NavigationView navigationView_Prt;
    ActionBarDrawerToggle actionBarDrawerToggle_Prt;
    CircleImageView circleImageViewParent, circleImageViewChidl1, circleImageViewAddChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        toolbar_Prt = findViewById(R.id.toolbarMain);
        toolbarMain_title = toolbar_Prt.findViewById(R.id.toolbarMain_title);
        toolbarMain_title.setText("Profile");
        setSupportActionBar(toolbar_Prt);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        fragmentManager = getSupportFragmentManager();

        drawerLayout_Prt = findViewById(R.id.drawer_layoutPrt);
        navigationView_Prt = findViewById(R.id.nav_viewPrt);
        navigationView_Prt.setNavigationItemSelectedListener(Activity_Parent.this);

        actionBarDrawerToggle_Prt = new ActionBarDrawerToggle(Activity_Parent.this, drawerLayout_Prt, toolbar_Prt, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout_Prt.addDrawerListener(actionBarDrawerToggle_Prt);
        actionBarDrawerToggle_Prt.syncState();

        //default frag
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                    .replace(R.id.fragment_containerPrt,
                            new Fragment_mainProfileParent(),
                            Utils_loginned.fragment_mainProfileParent)
                    .commit();

//            navigationView_Prt.setCheckedItem(R.id.menu_profileDrawer);
            Toast.makeText(this, "Instance", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, getIntent().getExtras().getString("cardId"), Toast.LENGTH_SHORT).show();;
        }

        circleImageViewParent = findViewById(R.id.profile_imageParent);
        circleImageViewAddChild = findViewById(R.id.profile_imageChildAddChild);
        circleImageViewChidl1 = findViewById(R.id.profile_imageChild1);

//        ColorFilter cf = new PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
//        circleImageViewAddChild.setColorFilter(cf);

        circleImageViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toolbarMain_title.setText("Parent Profile");
                circleImageViewParent.setBorderColor(getResources().getColor(R.color.colorGreen));
                circleImageViewParent.setBorderWidth(4);
                circleImageViewAddChild.setBorderWidth(0);
                circleImageViewChidl1.setBorderWidth(0);

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerPrt,
                                new Fragment_mainProfileParent(),
                                Utils_loginned.fragment_mainProfileParent)
                        .commit();
            }
        });

        circleImageViewAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toolbarMain_title.setText("Apply for Admission");

                circleImageViewAddChild.setBorderColor(getResources().getColor(R.color.colorGreen));

                circleImageViewParent.setBorderWidth(0);
                circleImageViewAddChild.setBorderWidth(4);
                circleImageViewChidl1.setBorderWidth(0);

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerPrt,
                                new Fragment_signup_fragmentParent_student(),
                                Utils_loginned.Fragment_signup_fragmentParent_studentAddChild)
                        .commit();
            }
        });

        circleImageViewChidl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // toolbarMain_title.setText("Student Profile");

                circleImageViewChidl1.setBorderColor(getResources().getColor(R.color.colorGreen));

                circleImageViewParent.setBorderWidth(0);
                circleImageViewAddChild.setBorderWidth(0);
                circleImageViewChidl1.setBorderWidth(4);

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.left_out, R.anim.right_enter)
                        .replace(R.id.fragment_containerPrt,
                                new Fragment_mainProfileParentChilds(),
                                Utils_loginned.fragment_mainProfileChild)
                        .commit();
            }
        });

    }


    public void closeDrawerEvent(View v) {
        drawerLayout_Prt.closeDrawer(GravityCompat.START);
    }

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar_notifications, menu);

        Drawable drawable = menu.findItem(R.id.toolbar_iconNotif).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(Activity_Parent.this, R.color.colorWhite));
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
            case R.id.menu_logoutDrawer:
                //code to logout
                Intent intent = new Intent(Activity_Parent.this, Activity_login.class);
                startActivity(intent);
                finish();

        }

        drawerLayout_Prt.closeDrawer(GravityCompat.START);
        return true;
    }

    // Replace profile Fragment with animation
    protected void replaceFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.fragment_containerPrt, new Fragment_mainProfileParent(),
                        Utils_loginned.fragment_mainProfileParent)
                .addToBackStack(null)
                .commit();


        toolbarMain_title.setText("Parent Profile");
        circleImageViewParent.setBorderColor(getResources().getColor(R.color.colorGreen));
        circleImageViewParent.setBorderWidth(4);
        circleImageViewAddChild.setBorderWidth(0);
        circleImageViewChidl1.setBorderWidth(0);

    }

    @Override
    public void onBackPressed() {
        Fragment Fragment_mainProfileParent = fragmentManager.findFragmentByTag(Utils_loginned.fragment_mainProfileParent);

        if (drawerLayout_Prt.isDrawerOpen(GravityCompat.START)) {
            drawerLayout_Prt.closeDrawer(GravityCompat.START);
        } else if (Fragment_mainProfileParent != null && Fragment_mainProfileParent.isVisible()) {
            //do nothing
            return;
        } else {
            Fragment fragment_mainProfile2 = fragmentManager.findFragmentByTag(Utils_loginned.fragment_mainProfile2);
            Fragment Fragment_signup_fragmentParent_studentAddChild = fragmentManager.findFragmentByTag(Utils_loginned.Fragment_signup_fragmentParent_studentAddChild);
            Fragment fragment_mainProfileChild = fragmentManager.findFragmentByTag(Utils_loginned.fragment_mainProfileChild);


            if (Fragment_signup_fragmentParent_studentAddChild != null && Fragment_signup_fragmentParent_studentAddChild.isVisible())
                replaceFragment();
            else if (fragment_mainProfile2 != null && fragment_mainProfile2.isVisible())
                replaceFragment();
            else if (fragment_mainProfileChild != null && fragment_mainProfileChild.isVisible())
                replaceFragment();

        }
    }

}
