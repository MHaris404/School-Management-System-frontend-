package com.example.assetssm;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Fragment_mainProfileParentChilds extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter_Common viewPagerAdapter_common;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofileparentchilds, container, false);

        viewPager = view.findViewById(R.id.Fragment_mainProfileChild_pager);
        tabLayout = view.findViewById(R.id.Fragment_mainProfileChild_tab);
        viewPagerAdapter_common = new ViewPagerAdapter_Common(getChildFragmentManager());
        viewPagerAdapter_common.AddFragment(new Fragment_mainProfile2(), "P");
        viewPagerAdapter_common.AddFragment(new Fragment_mainTimetable(), "T");
        viewPagerAdapter_common.AddFragment(new Fragment_mainAttendance(), "A");
        viewPagerAdapter_common.AddFragment(new Fragment_mainFees(), "F");
        viewPagerAdapter_common.AddFragment(new Fragment_mainLibrary(), "L");
        viewPagerAdapter_common.AddFragment(new Fragment_mainExam(), "E");
        viewPagerAdapter_common.AddFragment(new Fragment_mainClass(), "C");
        viewPagerAdapter_common.AddFragment(new Fragment_mainHostel(), "H");
        viewPagerAdapter_common.AddFragment(new Fragment_mainTransport(), "T");
        viewPagerAdapter_common.AddFragment(new Fragment_mainGrade(), "G");

        viewPager.setAdapter(viewPagerAdapter_common);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
                Toast.makeText(getActivity(), "" + tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        return view;
    }
}

