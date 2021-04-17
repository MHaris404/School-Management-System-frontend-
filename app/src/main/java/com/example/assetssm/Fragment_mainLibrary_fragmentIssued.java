package com.example.assetssm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class Fragment_mainLibrary_fragmentIssued extends Fragment {

    View view;
    LinearLayout Library_bookIssue_expendedView;
    Button Library_bookIssue_btnMore;
    CardView Library_bookIssue_cardView;

    public Fragment_mainLibrary_fragmentIssued() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainlibrary_fragmentissued, container, false);

        Library_bookIssue_expendedView = view.findViewById(R.id.Library_bookIssue_expendedView);
        Library_bookIssue_cardView = view.findViewById(R.id.Library_bookIssue_cardView);
        Library_bookIssue_btnMore = view.findViewById(R.id.Library_bookIssue_btnMore);
        bookIssueMore(Library_bookIssue_expendedView, Library_bookIssue_cardView, Library_bookIssue_btnMore);

        return view;
    }

    private void bookIssueMore(LinearLayout library_bookIssue_expendedView, CardView library_bookIssue_cardView, Button library_bookIssue_btnMore) {
        library_bookIssue_btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainProfile2 instBookIssueMore = new Fragment_mainProfile2();
                instBookIssueMore.ProfileDetailsExpandable(Library_bookIssue_expendedView, Library_bookIssue_btnMore, Library_bookIssue_cardView);
            }
        });
    }
}
