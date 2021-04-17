package com.example.assetssm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapter_charGuide extends PagerAdapter {

    Context mContext;
    List<Activity_charGuide_screenItem> mLisstScreen;

    public ViewPagerAdapter_charGuide(Context context, List<Activity_charGuide_screenItem> mLisstScreen) {
        this.mContext = context;
        this.mLisstScreen = mLisstScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layoutScreen = inflater.inflate(R.layout.activity_charguide_screenitem, null);

        Button title = layoutScreen.findViewById(R.id.intro_title);
        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        title.setText(mLisstScreen.get(position).getTitle());
        description.setText(mLisstScreen.get(position).getDesciption());
        imgSlide.setImageResource(mLisstScreen.get(position).getScreenImg());

        final int page = position;
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(container.getContext(), Activity_login.class);
                if (mContext instanceof Activity_charGuide) {
                    ((Activity_charGuide) mContext).savePrefsData(page);
                }
                mContext.startActivity(login);
            }
        });

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mLisstScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
