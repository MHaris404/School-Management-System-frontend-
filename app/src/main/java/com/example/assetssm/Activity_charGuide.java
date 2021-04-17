package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class Activity_charGuide<Public> extends AppCompatActivity {

    ViewPagerAdapter_charGuide charGuideViewPagerAdapter;
    TabLayout tabIndicator;
    int position = 0;
    Button btnNext;
    Animation btnAnimation;
    private ViewPager screenPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make activity full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //check on launching this Activity
        int pagevalue = getPrefData();
        Log.d("tag", "" + pagevalue);

        if (pagevalue != -1) {
            Intent login = new Intent(getApplicationContext(), Activity_login.class);
            startActivity(login);
        } else {

            setContentView(R.layout.activity_charguide);

            //hide action bar
            //getSupportActionBar().hide();

            //init views
            btnNext = findViewById(R.id.btn_next);
            tabIndicator = findViewById(R.id.tab_indicator);
            btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

            //fill list screen
            final List<Activity_charGuide_screenItem> mlist = new ArrayList<>();
            mlist.add(new Activity_charGuide_screenItem("I'm a Parent", "Who are you?", R.drawable.img1));
            mlist.add(new Activity_charGuide_screenItem("I'm a Student", "Who are you?", R.drawable.img2));
            mlist.add(new Activity_charGuide_screenItem("I'm a Staff", "Who are you?", R.drawable.img4));
            mlist.add(new Activity_charGuide_screenItem("I'm School Bus Driver ", "Who are you?", R.drawable.img6));

            //setup  viewPager
            screenPager = findViewById(R.id.screen_viewpager);
            charGuideViewPagerAdapter = new ViewPagerAdapter_charGuide(this, mlist);
            screenPager.setAdapter(charGuideViewPagerAdapter);

            //setup tablayout with viewpager
            tabIndicator.setupWithViewPager(screenPager);

            //next button click listener
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = screenPager.getCurrentItem();
                    if (position < mlist.size()) {
                        position++;
                        screenPager.setCurrentItem(position);
                    }
                }
            });

            //tabLayout add chnage listener
            tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == mlist.size() - 1) {
                        loadLastScreen();
                    } else {
                        OtherScreen();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }

            });
        }
    }

    private void OtherScreen() {
        btnNext.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }

    public int getPrefData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Activity_charGuide.this);
        return preferences.getInt("page", -1);
    }

    public void savePrefsData(int i) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Activity_charGuide.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("page", i);
        editor.commit();
    }

    //show the GETSTARTED BUTTON and hide the indicator and nextBtn
    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }
}
