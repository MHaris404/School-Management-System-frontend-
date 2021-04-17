package com.example.assetssm;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment_mainAttendance extends Fragment {

    Toolbar toolbar;
    TextView txt_toolbarTitle;
    CalendarView calendarView;
    TextView attendance_monthStatsPresentPercentage, attendance_monthStatsAbsentPercentage, attendance_monthStatsLatePercentage, attendance_monthStatsLeavePercentage;
    String[] status = {"Present", "Absent", "Late", "Leave"};
    double[] stats = {26.0, 11.0, 8.0, 4.0};
    private Calendar lastSelectedCalendar = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainattendance, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Attendance");

        //calender
        calendarView = view.findViewById(R.id.attendance_calendarView);
        CalendarFuncAttendance(view);

        //month
        attendance_monthStatsPresentPercentage = view.findViewById(R.id.attendance_monthStatsPresentPercentage);
        attendance_monthStatsAbsentPercentage = view.findViewById(R.id.attendance_monthStatsAbsentPercentage);
        attendance_monthStatsLatePercentage = view.findViewById(R.id.attendance_monthStatsLatePercentage);
        attendance_monthStatsLeavePercentage = view.findViewById(R.id.attendance_monthStatsLeavePercentage);
        MonthChart(view);

        return view;
    }

    private void MonthChart(View view) {
        double total = 0.0;
        double[] percentage = new double[4];

        for (double a : stats) {
            total += a;
        }

        for (int a = 0; a < stats.length; a++) {
            percentage[a] = (stats[a] / total) * 100;
        }
        DecimalFormat f = new DecimalFormat("##.00");
        attendance_monthStatsPresentPercentage.setText(f.format(percentage[0]) + "%");
        attendance_monthStatsAbsentPercentage.setText(f.format(percentage[1]) + "%");
        attendance_monthStatsLatePercentage.setText(f.format(percentage[2]) + "%");
        attendance_monthStatsLeavePercentage.setText(f.format(percentage[3]) + "%");
    }

    private void CalendarFuncAttendance(View view) {

        lastSelectedCalendar = Calendar.getInstance();
        //calendarView.setMinDate(lastSelectedCalendar.getTimeInMillis() - 1000);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Calendar checkCalendar = Calendar.getInstance();
                checkCalendar.set(year, month, dayOfMonth);
                if (checkCalendar.equals(lastSelectedCalendar)) {
                    ///////////check if it current sunday/saturady//////////////
                    //yaha check kro
                    return;
                }
                //sunday disabled
                else if (checkCalendar.get(Calendar.DAY_OF_WEEK) == (Calendar.SUNDAY)) {
                    calendarView.setDate(lastSelectedCalendar.getTimeInMillis());

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                    String dateString = formatter.format(calendarView.getDate());
                    Integer Year = Integer.parseInt(dateString.substring(0, 4));
                    Integer Month = Integer.parseInt(dateString.substring(5, 7));
                    Integer Day = Integer.parseInt(dateString.substring(8, 10));

                    Toast.makeText(getActivity(), "y " + Year + "m " + Month + "d " + Day, Toast.LENGTH_SHORT).show();
                    ////////////////////check if it current sunday/saturady

                } else if (checkCalendar.get(Calendar.DAY_OF_WEEK) != (Calendar.SUNDAY)) {
                    Toast.makeText(getActivity(), "y " + year + "m " + month + "d " + dayOfMonth, Toast.LENGTH_SHORT).show();
                    ///////////////////check if it current sunday/saturady
                } else
                    lastSelectedCalendar = checkCalendar;
            }
        });
    }


}