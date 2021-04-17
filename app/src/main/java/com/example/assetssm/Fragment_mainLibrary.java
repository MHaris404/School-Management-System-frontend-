package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Fragment_mainLibrary extends Fragment {

    private static View view;
    private static TextView login, textToolHeader;
    Toolbar toolbar;
    TextView txt_toolbarTitle;
    ViewPager viewPager;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainlibrary, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Library");

        viewPager = (ViewPager) view.findViewById(R.id.library_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.library_tab);

        ViewPagerAdapter_Common viewPagerAdapter_common = new ViewPagerAdapter_Common(getChildFragmentManager());
        viewPagerAdapter_common.AddFragment(new Fragment_mainLibrary_fragmentBooks(), "Books");
        viewPagerAdapter_common.AddFragment(new Fragment_mainLibrary_fragmentIssued(), "Issued Books");
        viewPager.setAdapter(viewPagerAdapter_common);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

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
