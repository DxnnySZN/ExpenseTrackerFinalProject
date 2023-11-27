package com.example.expensetrackerproject.FirstThreeSlides;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.expensetrackerproject.Authentication.ViewPagerAdapter;
import com.example.expensetrackerproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Onboarding_activity extends AppCompatActivity {
    private ViewPager screenPager;
    private OnboardingAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ArrayList<Object> onboardingItems = new ArrayList<>();
        // Here is to edit the contents of the xml widgets
        int resourceId1 = getResources().getIdentifier("dudewithbread", "drawable", getPackageName());
        int resourceId2 = getResources().getIdentifier("testpic", "drawable", getPackageName());
        int resourceId3 = getResources().getIdentifier("aimoneylastslide", "drawable", getPackageName());
        FirstTwoSlides a = new FirstTwoSlides(resourceId1, "Budget Like a Pro: Spend Smartly, Save Effortlessly");
        FirstTwoSlides b = new FirstTwoSlides(resourceId2, "Track, Budget, Save – Your Financial Roadmap");
        LastOnboardingItem c = new LastOnboardingItem(resourceId3, "Start Tracking Your Expenses Today!");
        onboardingItems.add(a);
        onboardingItems.add(b);
        onboardingItems.add(c);


        ViewPager2 viewPager = findViewById(R.id.viewPager);
        OnboardingAdapter adapter = new OnboardingAdapter(this, onboardingItems);
        viewPager.setAdapter(adapter);

        // initialize tabIndicator
       // tabIndicator = findViewById(R.id.tabIndicator);

        // setup TabLayout with ViewPager
        //tabIndicator.setupWithViewPager(R.id.screenPager);
    }
}