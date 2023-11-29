package com.example.expensetrackerproject.FirstThreeSlides;

public class ScreenActivity {
    private String title;
    private int ScreenImg;

    public ScreenActivity(String title, int screenImg) {
        this.title = title;
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getScreenImg() {
        return ScreenImg;
    }
    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}