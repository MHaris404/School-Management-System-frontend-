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

public class Fragment_mainLibrary_fragmentBooks extends Fragment {

    View view;
    LinearLayout Library_book_expendedView;
    Button Library_book_more;
    CardView Library_book_cardView;

    public Fragment_mainLibrary_fragmentBooks() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainlibrary_fragmentbooks, container, false);

        Library_book_expendedView = view.findViewById(R.id.Library_book_expendedView);
        Library_book_cardView = view.findViewById(R.id.Library_book_cardView);
        Library_book_more = view.findViewById(R.id.Library_book_btnMore);
        bookMore(Library_book_expendedView, Library_book_more, Library_book_cardView);

        return view;
    }

    private void bookMore(final LinearLayout library_book_expendedView, final Button library_book_more, final CardView library_book_cardView) {
        library_book_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_mainProfile2 instBookMore = new Fragment_mainProfile2();
                instBookMore.ProfileDetailsExpandable(library_book_expendedView, library_book_more, library_book_cardView);
            }
        });
    }


}
