package com.example.assetssm;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_mainProfile2 extends Fragment {

    View view;
    Toolbar toolbar;
    TextView txt_toolbarTitle;
    AnyChartView pieChart;
    LinearLayout expandableView;
    Button arrowBtn;
    CardView cardView;

    String[] status = {"Present", "Absent", "Late", "Leave"};
    int[] percentage = {50, 25, 15, 10};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofile2, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Profile");

        //details
        expandableView = view.findViewById(R.id.profileDetails_open);
        arrowBtn = view.findViewById(R.id.btn_expandDetails);
        cardView = view.findViewById(R.id.profiledetails);
        ProfileDetailsExpandable(expandableView, arrowBtn, cardView);

        //chart
        pieChart = view.findViewById(R.id.profile_pieChartAttendance);
        pieChart.setProgressBar(view.findViewById(R.id.progress_bar));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                setupChart();
            }
        }, 10000);

        return view;
    }

    public void ProfileDetailsExpandable(final LinearLayout addedView, final Button btn, final CardView cardView) {
        //addedView.setVisibility(View.GONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addedView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    addedView.setVisibility(View.VISIBLE);
                    btn.setBackgroundResource(R.drawable.iconarrowup);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    addedView.setVisibility(View.GONE);
                    btn.setBackgroundResource(R.drawable.iconarrowdown);
                }
            }
        });
    }

    private void setupChart() {

        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();

        for (int i = 0; i < status.length; i++) {
            data.add(new ValueDataEntry(status[i], percentage[i]));
        }

        pie.data(data);
        pie.title("Attendance");
        pie.labels().position("outside");
        pie.legend().title().enabled(true);
        pie.legend().title().text("Status Guide").padding(0d, 0d, 10d, 0d);


        pie.legend()
                .position("LEFT")
                .itemsLayout(LegendLayout.VERTICAL)
                .align(Align.LEFT);

        pieChart.setChart(pie);
        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(getActivity(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
