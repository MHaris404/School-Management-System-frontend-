package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.assetssm.TimeTableSteps.BreakDurationStep9;
import com.example.assetssm.TimeTableSteps.ClassStep3;
import com.example.assetssm.TimeTableSteps.PeriodFridayDurationStep8;
import com.example.assetssm.TimeTableSteps.PeriodFridayQtyStep5;
import com.example.assetssm.TimeTableSteps.PeriodNonFridayDurationStep7;
import com.example.assetssm.TimeTableSteps.PeriodNonFridayQtyStep6;
import com.example.assetssm.TimeTableSteps.SectionStep4;
import com.example.assetssm.TimeTableSteps.TimeStep1;
import com.example.assetssm.TimeTableSteps.YearStep2;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class Fragment_mainProfileStaffAdminTimetable_tTableGenerator_2AfterDataGet extends Fragment implements StepperFormListener {

    Toolbar toolbar;
    TextView txt_toolbarTitle;

    LinearLayout expandableView;
    Button arrowBtn;
    CardView cardView;

    private TimeStep1 TimeStep;
    private YearStep2 YearStep;
    private ClassStep3 ClassStep;
    private SectionStep4 SectionStep;
    private PeriodFridayQtyStep5 PFQStep;
    private PeriodNonFridayQtyStep6 PNFQStep;
    private PeriodNonFridayDurationStep7 PNFDurationStep;
    private PeriodFridayDurationStep8 PFDurationStep;
    private BreakDurationStep9 BDurationStep;

    private VerticalStepperFormView verticalStepperForm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadmintimetable_ttablegenerator_2afterdataget, container, false);

        //stepper requires material theme
        getContext().getTheme().applyStyle(R.style.MaterialTheme, true);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);

        //campus Title
        //xt_toolbarTitle.setText("Hostel");

        // Create the steps.
        TimeStep = new TimeStep1("Assemble Time");
        YearStep = new YearStep2("Year");
        ClassStep = new ClassStep3("Class");
        SectionStep = new SectionStep4("Section");
        PFQStep = new PeriodFridayQtyStep5("Number of Friday Periods");
        PNFQStep = new PeriodNonFridayQtyStep6("Number of Non Friday Periods");
        PFDurationStep = new PeriodFridayDurationStep8("Duration of Friday Periods");
        PNFDurationStep = new PeriodNonFridayDurationStep7("Duration of Non Friday Periods");
        BDurationStep = new BreakDurationStep9("Duration of Break");

        // Find the form view, set it up and initialize it.
        verticalStepperForm = view.findViewById(R.id.stepper_form);
        verticalStepperForm
                .setup(this, YearStep, ClassStep, SectionStep, TimeStep,
                        PFQStep, PNFQStep, PNFDurationStep, PFDurationStep, BDurationStep)
                .allowNonLinearNavigation(false)
                .displayBottomNavigation(false)
                .lastStepNextButtonText("Confirmation")
                .init();
        return view;
    }

    @Override
    public void onCompletedForm() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("general", 4);
        editor.commit();

        Intent intent = new Intent(getActivity(), Activity_mainProfileStaffAdmin_general.class);
        startActivity(intent);
    }

    @Override
    public void onCancelledForm() {

    }

}
