package com.example.assetssm;

import android.content.Context;
import android.util.AttributeSet;

import androidx.drawerlayout.widget.DrawerLayout;

public class CustomDrawerLayoutMatrix extends DrawerLayout {

    public CustomDrawerLayoutMatrix(Context context) {
        super(context);
    }

    public CustomDrawerLayoutMatrix(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayoutMatrix(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}