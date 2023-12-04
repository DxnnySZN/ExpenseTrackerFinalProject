package com.example.expensetrackerproject.FirstThreeSlides;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.expensetrackerproject.R;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager2 screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        int resourceID1 = getResources().getIdentifier("dudewithbread", "drawable", getPackageName());
        int resourceID2 = getResources().getIdentifier("testpic", "drawable", getPackageName());
        int resourceID3 = getResources().getIdentifier("aimoneylastslide", "drawable", getPackageName());

        // fill list screen
        List<ScreenActivity> mList = new ArrayList<>();
        mList.add(new ScreenActivity("Budget Like a Pro: Spend Smartly, Save Effortlessly", resourceID1));
        mList.add(new ScreenActivity("Track, Budget, Save – Your Financial Roadmap", resourceID2));
        mList.add(new ScreenActivity("Start Tracking Your Expenses Today!", resourceID3));

        // setup ViewPager
        screenPager = findViewById(R.id.ViewPagerScreen);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);
    }
}