package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import java.util.ArrayList;
import java.util.List;

public class Fragment_mainGrade extends Fragment {

    View view;
    Toolbar toolbar;
    TextView txt_toolbarTitle;
    Spinner spn_fragGrade_gradeSelect;
    List<Integer> gradesClass;
    ArrayAdapter<Integer> arrayAdapterGrade;
    ArrayAdapter<String> arrayAdapterSubject;

    LinearLayout grades_record_quiz_expendedView, grades_record_assign_expendedView, grades_record_presentation_expendedView, grades_record_performance_expendedView, grades_record_exam1_expendedView, grades_record_exam2_expendedView;
    Button grades_record_quiz_recordBtnMore, grades_record_assign_recordBtnMore, grades_record_presentation_recordBtnMore, grades_record_performance_recordBtnMore, grades_record_exam1_recordBtnMore, grades_record_exam2_recordBtnMore;
    CardView grades_record_quiz_cardView, grades_record_assign_cardView, grades_record_presentation_cardView, grades_record_performance_cardView, grades_record_exam1_cardView, grades_record_exam2_cardView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maingrade, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Academic Record");

        spn_fragGrade_gradeSelect = view.findViewById(R.id.spn_fragGrade_gradeSelect);
        gradesClassSpn();

        grades_record_quiz_expendedView = view.findViewById(R.id.grades_record_quiz_expendedView);
        grades_record_quiz_recordBtnMore = view.findViewById(R.id.grades_record_quiz_recordBtnMore);
        grades_record_quiz_cardView = view.findViewById(R.id.grades_record_quiz_cardView);
        moreQuizRecord(grades_record_quiz_expendedView, grades_record_quiz_recordBtnMore, grades_record_quiz_cardView);

        grades_record_assign_expendedView = view.findViewById(R.id.grades_record_assignment_expendedView);
        grades_record_assign_recordBtnMore = view.findViewById(R.id.grades_record_asign_recordBtnMore);
        grades_record_assign_cardView = view.findViewById(R.id.grades_record_assign_cardView);
        moreAssignRecord(grades_record_assign_expendedView, grades_record_assign_recordBtnMore, grades_record_assign_cardView);

        grades_record_presentation_expendedView = view.findViewById(R.id.grades_record_presentation_expendedView);
        grades_record_presentation_recordBtnMore = view.findViewById(R.id.grades_record_presentation_recordBtnMore);
        grades_record_presentation_cardView = view.findViewById(R.id.grades_record_presentation_cardView);
        morePresentationRecord(grades_record_presentation_expendedView, grades_record_presentation_recordBtnMore, grades_record_presentation_cardView);

        grades_record_performance_expendedView = view.findViewById(R.id.grades_record_performance_expendedView);
        grades_record_performance_recordBtnMore = view.findViewById(R.id.grades_record_performance_recordBtnMore);
        grades_record_performance_cardView = view.findViewById(R.id.grades_record_performance_cardView);
        morePerformanceRecord(grades_record_performance_expendedView, grades_record_performance_recordBtnMore, grades_record_performance_cardView);

        grades_record_exam1_expendedView = view.findViewById(R.id.grades_record_exam1_expendedView);
        grades_record_exam1_recordBtnMore = view.findViewById(R.id.grades_record_exam1_recordBtnMore);
        grades_record_exam1_cardView = view.findViewById(R.id.grades_record_exam1_cardView);
        moreExam1Record(grades_record_exam1_expendedView, grades_record_exam1_recordBtnMore, grades_record_exam1_cardView);

        grades_record_exam2_expendedView = view.findViewById(R.id.grades_record_exam2_expendedView);
        grades_record_exam2_recordBtnMore = view.findViewById(R.id.grades_record_exam2_recordBtnMore);
        grades_record_exam2_cardView = view.findViewById(R.id.grades_record_exam2_cardView);
        moreExam2Record(grades_record_exam2_expendedView, grades_record_exam2_recordBtnMore, grades_record_exam2_cardView);

        return view;
    }

    private void moreExam2Record(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void moreExam1Record(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void morePerformanceRecord(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void morePresentationRecord(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void moreAssignRecord(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void moreQuizRecord(LinearLayout a, Button b, CardView c) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordsDetailsExpandable(a, b, c);
            }
        });
    }

    private void gradesClassSpn() {
        gradesClass = new ArrayList<Integer>();
        gradesClass.add(1);
        gradesClass.add(2);
        gradesClass.add(3);
        gradesClass.add(4);
        gradesClass.add(5);
        gradesClass.add(6);

        arrayAdapterGrade = new ArrayAdapter<Integer>(getActivity(), R.layout.spn_itemcustom, gradesClass);
        spn_fragGrade_gradeSelect.setAdapter(arrayAdapterGrade);
        spn_fragGrade_gradeSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void RecordsDetailsExpandable(final LinearLayout addedView, final Button btn, final CardView cardView) {
        //addedView.setVisibility(View.GONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addedView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    addedView.setVisibility(View.VISIBLE);
                    btn.setBackgroundResource(R.drawable.iconminus);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    addedView.setVisibility(View.GONE);
                    btn.setBackgroundResource(R.drawable.iconplus);
                }
            }
        });
    }
}