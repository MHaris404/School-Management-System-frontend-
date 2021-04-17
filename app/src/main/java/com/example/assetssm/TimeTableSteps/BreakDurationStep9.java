package com.example.assetssm.TimeTableSteps;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

public class BreakDurationStep9 extends Step<String> {

    private static Animation shakeAnimation;
    private TextView BreakDuration;

    public BreakDurationStep9(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.

        shakeAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        BreakDuration = new TextView(getContext());
        BreakDuration.setSingleLine(true);
        BreakDuration.setHint("Hint: 00:30");
        BreakDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBreakDurationPicker();
            }
        });

        return BreakDuration;
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
        return BreakDuration != null ? BreakDuration.getText().toString() : "Configure Duration";
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
    }

    @Override
    protected void onStepClosed(boolean animated) {
        // This will be called automatically whenever the step gets closed.

    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as completed.

    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as uncompleted.

    }

    @Override
    public void restoreStepData(String stepData) {
        // To restore the step after a configuration change, we restore the text of its EditText view.

    }

    public void showBreakDurationPicker() {
        final Calendar myCalender = Calendar.getInstance();
        final int[] time = {00, 30};

        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                if (view.isShown()) {
                    if (minute % 5 == 0) {
                        if (hour == 00) {
                            myCalender.set(0, 0, 0, hour, minute);
                            BreakDuration.setText(DateFormat.format("00:mm", myCalender));

                            time[0] = hour;
                            time[1] = minute;
                        } else {
                            myCalender.set(0, 0, 0, hour, minute);
                            BreakDuration.setText(DateFormat.format("hh:mm", myCalender));

                            time[0] = hour;
                            time[1] = minute;
                        }
                    } else {
                        new CustomToast().Show_Toast(getContext(), view,
                                "Required: Minutes in 5");

                        BreakDuration.startAnimation(shakeAnimation);
                    }

                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, time[0], time[1], true);
        timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        timePickerDialog.setTitle("Configure Duration");
        timePickerDialog.updateTime(time[0], time[1]);
        timePickerDialog.setCanceledOnTouchOutside(false);
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

}