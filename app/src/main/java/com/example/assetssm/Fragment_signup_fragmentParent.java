package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Fragment_signup_fragmentParent extends Fragment implements OnClickListener {
    private static View view;
    private static TextView login, textToolHeader;

    ViewPager viewPager;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarLogin);
        textToolHeader = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textToolHeader.setText("Apply for Admission");
        setHasOptionsMenu(true);

        view = inflater.inflate(R.layout.fragment_signup_fragmentparent, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.signup_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.signup_tab);
        //appBarLayout = (AppBarLayout) view.findViewById(R.id.signup_std_appbar);

        ViewPagerAdapter_Common viewPagerAdapter_common = new ViewPagerAdapter_Common(getChildFragmentManager());
        viewPagerAdapter_common.AddFragment(new Fragment_signup_fragmentParent_parent(), "Parent Registration");
        viewPagerAdapter_common.AddFragment(new Fragment_signup_fragmentParent_student(), "Child Application");
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

        initViews();
        setListeners();
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item=menu.findItem(R.id.item1);
//        if(item!=null)
//            item.setVisible(false);
        menu.clear();
    }

    // Initialize all views
    private void initViews() {
        login = (TextView) view.findViewById(R.id.already_user);

    }

    // Set Listeners
    private void setListeners() {
        //signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.already_user:

                // Replace Activity_login fragment
                new Activity_login().replaceLoginFragment();
                break;
        }

    }

    // Check Validation Method
    private void checkValidation() {

    }
}
