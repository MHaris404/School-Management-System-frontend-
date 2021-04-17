package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_mainProfileStaffAdminAtt extends Fragment {

    private static View view;
    private static TextView login, textToolHeader;
    Toolbar toolbar;
    TextView txt_toolbarTitle;
    ViewPager viewPager;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;

    Spinner spn_fragStaffOfficer_attendanceYear;
    List<Integer> arr_attyear;
    ArrayAdapter<Integer> arr_adpyear;

    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofilestaffadminatt, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Attendance");

        searchView = view.findViewById(R.id.searchview_admin_att);
        //search(searchView);

        spn_fragStaffOfficer_attendanceYear = view.findViewById(R.id.spn_fragStaffOfficer_attendanceYear);
        attendYearSpn();

        viewPager = (ViewPager) view.findViewById(R.id.staffattendance_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.staffattendance_tab);

        ViewPagerAdapter_Common viewPagerAdapter_common = new ViewPagerAdapter_Common(getChildFragmentManager());
        viewPagerAdapter_common.AddFragment(new Fragment_mainProfileStaffAdminAtt_fragmentStaff(), "Staff");
        viewPagerAdapter_common.AddFragment(new Fragment_mainProfileStaffAdminAtt_fragmentStudent(), "Student");
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

    private void attendYearSpn() {
        arr_attyear = new ArrayList<Integer>();
        arr_attyear.add(2020);
        arr_attyear.add(2019);
        arr_attyear.add(2018);

        arr_adpyear = new ArrayAdapter<Integer>(getActivity(), R.layout.spn_itemcustom, arr_attyear);
        spn_fragStaffOfficer_attendanceYear.setAdapter(arr_adpyear);
        spn_fragStaffOfficer_attendanceYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
