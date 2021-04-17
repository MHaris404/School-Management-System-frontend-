package com.example.assetssm.TimeTableSteps;

import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.assetssm.CustomToast;
import com.example.assetssm.R;

import java.util.Calendar;

import ernestoyaquello.com.verticalstepperform.Step;

public class TimeStep1 extends Step<String> {

    private static Animation shakeAnimation;
    private TextView time;

    public TimeStep1(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.

        time = new TextView(getContext());
        time.setSingleLine(true);
        time.setHint("Hint: 8:30 am");

        shakeAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHourPicker();
            }
        });

        return time;
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
        return time != null ? time.getText().toString() : "Configure Starting Time";
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
//        time.setText("2");
    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as completed.
//        time.setText(hour + ":" + minute);
    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as uncompleted.
        time.setText("Didn't Configure time");
    }

    @Override
    public void restoreStepData(String stepData) {
        // To restore the step after a configuration change, we restore the text of its EditText view.
        time.setText(stepData);
    }

    public void showHourPicker() {
        final Calendar myCalender = Calendar.getInstance();
        final int[] startingTime = {8, 30};

        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    if (minute % 5 == 0) {

                        myCalender.set(0, 0, 0, hourOfDay, minute);
                        time.setText(DateFormat.format("hh:mm aa", myCalender));

                        startingTime[0] = hourOfDay;
                        startingTime[1] = minute;

                    } else {
                        new CustomToast().Show_Toast(getContext(), view,
                                "Required: Minutes in 5");

                        time.startAnimation(shakeAnimation);
                    }
                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, startingTime[0], startingTime[0], true);
        timePickerDialog.setTitle("Configure Time");
        timePickerDialog.updateTime(startingTime[0], startingTime[1]);
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }


}