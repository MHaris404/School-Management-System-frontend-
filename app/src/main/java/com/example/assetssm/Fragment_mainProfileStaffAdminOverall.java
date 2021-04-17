package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Cartesian3d;
import com.anychart.core.cartesian.series.Area3d;
import com.anychart.core.cartesian.series.Column;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Anchor;
import com.anychart.graphics.vector.hatchfill.HatchFillType;

import java.util.ArrayList;
import java.util.List;

public class Fragment_mainProfileStaffAdminOverall extends Fragment {

    Toolbar toolbar;
    TextView txt_toolbarTitle;
    ProgressBar progress_bar_earning, progress_bar_expense;
    AnyChartView anyChartView_earning, anyChartView_expense;
    int fiscalYear = 2019;

    CardView card_mainOfficerStd, card_mainOfficerPrt, card_mainOfficerEmp, card_mainOfficerEar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainstaffadminoverall, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Overall");

        earningChart(view);
        expenseChart(view);
        all(view);

        return view;
    }

    private void all(View view) {
        card_mainOfficerStd = view.findViewById(R.id.card_mainOfficerStd);
        card_mainOfficerStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAll = new Intent(getActivity(), Activity_mainOfficerList.class);
                preference(1);
                startActivity(intentAll);
            }
        });

        card_mainOfficerPrt = view.findViewById(R.id.card_mainOfficerPrt);
        card_mainOfficerPrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAll = new Intent(getActivity(), Activity_mainOfficerList.class);
                preference(2);
                startActivity(intentAll);
            }
        });

        card_mainOfficerEmp = view.findViewById(R.id.card_mainOfficerEmp);
        card_mainOfficerEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAll = new Intent(getActivity(), Activity_mainOfficerList.class);
                preference(3);
                startActivity(intentAll);
            }
        });

    }

    void preference(int val) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("itemClickType", val);
        editor.commit();
    }

    private void earningChart(View view) {

        anyChartView_earning = view.findViewById(R.id.any_chart_view_earning);
        APIlib.getInstance().setActiveAnyChartView(anyChartView_earning);

        progress_bar_earning = view.findViewById(R.id.progress_bar_earning);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawableProgress = DrawableCompat.wrap(progress_bar_earning.getIndeterminateDrawable());
            DrawableCompat.setTint(drawableProgress, ContextCompat.getColor(getContext(), R.color.colorPrimary));
            progress_bar_earning.setIndeterminateDrawable(DrawableCompat.unwrap(drawableProgress));
        } else {
            progress_bar_earning.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }

        anyChartView_earning.setProgressBar(progress_bar_earning);

        Cartesian3d area3dearning = AnyChart.area3d();

        area3dearning.xAxis(0).labels().format("${%Value}");

        area3dearning.animation(true);

        area3dearning.yAxis(0).title("Revenue");
        area3dearning.xAxis(0).title("Quarter");
        area3dearning.xAxis(0).labels().padding(5d, 5d, 0d, 5d);

        int fiscalYear = 2019;
        area3dearning.title("Quarterly Revenue for fiscal Year " + fiscalYear + "<br/>'+\n" +
                "    '<span style=\"color:#212121; font-size: 13px;\">compared to previous year</span>");

        area3dearning.title().useHtml(true);
        area3dearning.title().padding(0d, 0d, 20d, 0d);

        List<DataEntry> seriesDataearning = new ArrayList<>();
        seriesDataearning.add(new CustomDataEntry_earning("1", 162, 120));
        seriesDataearning.add(new CustomDataEntry_earning("2", 134, 120));
        seriesDataearning.add(new CustomDataEntry_earning("3", 116, 125));
        seriesDataearning.add(new CustomDataEntry_earning("4", 122, 120));


        Set setearning = Set.instantiate();
        setearning.data(seriesDataearning);
        Mapping series1Data = setearning.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = setearning.mapAs("{ x: 'x', value: 'value2' }");

        Area3d series1earning = area3dearning.area(series1Data);
        series1earning.name("" + fiscalYear).hovered().markers(false);
        series1earning.hatchFill("diagonal", "#000", 0.6d, 15d);

        Area3d series2earning = area3dearning.area(series2Data);
        int fiscalyearPrev = fiscalYear - 1;
        series2earning.name("" + fiscalyearPrev);
        series2earning.hovered().markers(false);
        series2earning.hatchFill(HatchFillType.DIAGONAL_BRICK, "#000", 0.6d, 15d);

        area3dearning.tooltip()
                .position(Position.CENTER_TOP)
                .positionMode(TooltipPositionMode.POINT)
                .anchor(String.valueOf(Anchor.LEFT_BOTTOM))
                .offsetX(5d)
                .offsetY(5d);

        area3dearning.interactivity().hoverMode(HoverMode.BY_X);
        area3dearning.zAspect("100%");

        area3dearning.legend().enabled(true);

        anyChartView_earning.setChart(area3dearning);
    }

    private void expenseChart(View view) {
        anyChartView_expense = view.findViewById(R.id.any_chart_view_expense);
        APIlib.getInstance().setActiveAnyChartView(anyChartView_expense);

        progress_bar_expense = view.findViewById(R.id.progress_bar_expense);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawableProgress = DrawableCompat.wrap(progress_bar_earning.getIndeterminateDrawable());
            DrawableCompat.setTint(drawableProgress, ContextCompat.getColor(getContext(), R.color.colorPrimary));
            progress_bar_expense.setIndeterminateDrawable(DrawableCompat.unwrap(drawableProgress));
        } else {
            progress_bar_expense.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }

        anyChartView_expense.setProgressBar(view.findViewById(R.id.progress_bar_expense));

        Cartesian cartesianexpense = AnyChart.column();

        List<DataEntry> dataexpense = new ArrayList<>();
        dataexpense.add(new ValueDataEntry("Jan", 80540));
        dataexpense.add(new ValueDataEntry("Feb", 94190));
        dataexpense.add(new ValueDataEntry("Mar", 102610));
        dataexpense.add(new ValueDataEntry("Apr", 110430));
        dataexpense.add(new ValueDataEntry("May", 128000));
        dataexpense.add(new ValueDataEntry("Jun", 143760));
        dataexpense.add(new ValueDataEntry("Jul", 170670));
        dataexpense.add(new ValueDataEntry("Aug", 213210));
        dataexpense.add(new ValueDataEntry("Sep", 249980));
        dataexpense.add(new ValueDataEntry("Oct", 249980));
        dataexpense.add(new ValueDataEntry("Nov", 249980));
        dataexpense.add(new ValueDataEntry("Dec", 249980));

        Column columnexpense = cartesianexpense.column(dataexpense);

        columnexpense.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(String.valueOf(Anchor.CENTER_BOTTOM))
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesianexpense.animation(true);
        cartesianexpense.title("Expenses for the Fiscal Year " + fiscalYear);

        cartesianexpense.yScale().minimum(0d);

        cartesianexpense.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesianexpense.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesianexpense.interactivity().hoverMode(HoverMode.BY_X);

        cartesianexpense.xAxis(0).title("Month");
        cartesianexpense.yAxis(0).title("Expense");

        anyChartView_expense.setChart(cartesianexpense);
    }

    private class CustomDataEntry_earning extends ValueDataEntry {
        CustomDataEntry_earning(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }

}
