package com.example.assetssm.TimeTableSteps;

import android.app.DatePickerDialog;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import ernestoyaquello.com.verticalstepperform.Step;

public class YearStep2 extends Step<String> {

    private TextView year1;

    public YearStep2(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.

        year1 = new TextView(getContext());
        year1.setSingleLine(true);
        year1.setHint("Hint: 2021");

        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearPicker();
            }
        });

        return year1;
    }

    @Override
    protected IsDataValid isStepDataValid(String stepData) {
        // The step's data (i.e., the user name) will be considered valid only if it is longer than
        // three characters. In case it is not, we will display an error message for feedback.
        // In an optional step, you should implement this method to always return a valid value.
        boolean isNameValid = stepData.length() >= 0;
        String errorMessage = !isNameValid ? "3 characters minimum" : "";

        return new IsDataValid(isNameValid, errorMessage);
    }

    @Override
    public String getStepData() {
        // We get the step's data from the value that the user has typed in the EditText view.
//        Editable userName = (Editable) time.getText();
        return year1 != null ? year1.getText().toString() : "Configure Year";
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        // Because the step's data is already a human-readable string, we don't need to convert it.
        // However, we return "(Empty)" if the text is empty to avoid not having any text to display.
        // This string will be displayed in the subtitle of the step whenever the step gets closed.
        String userName = getStepData();
        return !userName.isEmpty() ? userName : "(Empty)";
    }

    @Override
    protected void onStepOpened(boolean animated) {
        // This will be called automatically whenever the step gets opened.
//        time.setText("1");
    }

    @Override
    protected void onStepClosed(boolean animated) {
        // This will be called automatically whenever the step gets closed.
//        year1.setText("2");
    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as completed.
//        time.setText(hour + ":" + minute);
    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as uncompleted.
        year1.setText("Didn't Configure Year");
    }

    @Override
    public void restoreStepData(String stepData) {
        // To restore the step after a configuration change, we restore the text of its EditText view.
        year1.setText(stepData);
    }

    public void showYearPicker() {
        final Calendar myCalender = Calendar.getInstance();
        int yearS = myCalender.get(Calendar.YEAR);

        DatePickerDialog.OnDateSetListener yearPickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (view.isShown()) {
                    myCalender.set(Calendar.YEAR, yearS);
                    myCalender.set(year + 1, 0, 0, 0, 0);
                    year1.setText(DateFormat.format("yyyy", myCalender));
                }
            }
        };

        DatePickerDialog DatePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, yearPickerListener, yearS, 0, 0);
        DatePickerDialog.getDatePicker().findViewById(getContext().getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        DatePickerDialog.getDatePicker().findViewById(getContext().getResources().getIdentifier("month", "id", "android")).setVisibility(View.GONE);
        DatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        DatePickerDialog.getDatePicker().setMaxDate((long) (System.currentTimeMillis() + 6.307e+10));
        DatePickerDialog.setTitle("Configure Time");
        DatePickerDialog.updateDate(yearS, 0, 0);
        DatePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        DatePickerDialog.show();

    }

}