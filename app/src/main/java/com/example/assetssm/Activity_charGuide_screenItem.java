package com.example.assetssm;

public class Activity_charGuide_screenItem {
    String Title, Desciption;
    int ScreenImg;

    public Activity_charGuide_screenItem(String title, String desciption, int screenImg) {
        Title = title;
        Desciption = desciption;
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesciption() {
        return Desciption;
    }

    public void setDesciption(String desciption) {
        Desciption = desciption;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}
