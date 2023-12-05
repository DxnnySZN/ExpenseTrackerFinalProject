package com.example.expensetrackerproject.FirstThreeSlides;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.expensetrackerproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager2 screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // initialize
        tabIndicator = findViewById(R.id.tabLayout);

        // allows info1pic1 ImageView to change with each slide
        int resourceID1 = getResources().getIdentifier("dudewithbread", "drawable", getPackageName());
        int resourceID2 = getResources().getIdentifier("testpic", "drawable", getPackageName());
        int resourceID3 = getResources().getIdentifier("aimoneylastslide", "drawable", getPackageName());

        // allows introTitle TextView to change with each slide
        List<ScreenActivity> mList = new ArrayList<>();
        mList.add(new ScreenActivity("Budget Like a Pro: Spend Smartly, Save Effortlessly", resourceID1));
        mList.add(new ScreenActivity("Track, Budget, Save – Your Financial Roadmap", resourceID2));
        mList.add(new ScreenActivity("Start Tracking Your Expenses Today!", resourceID3));

        // setup ViewPager2
        screenPager = findViewById(R.id.ViewPagerScreen);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // allows user to see which screen they're on due to the highlighting of the indicator
        screenPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabIndicator.selectTab(tabIndicator.getTabAt(position));
            }
        });

        // allows user to go to the following screens by clicking the physical indicator
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // handle tab selection
                int position = tab.getPosition();
                screenPager.setCurrentItem(position, true); // Move to the selected screen
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // handle tab deselection if needed
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // handle tab reselection if needed
            }
        });
    }
}