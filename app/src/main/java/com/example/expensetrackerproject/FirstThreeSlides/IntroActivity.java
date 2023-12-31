package com.example.expensetrackerproject.FirstThreeSlides;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.expensetrackerproject.Authentication.SignInCreateAccountActivity;
import com.example.expensetrackerproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager2 screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button nextBttn;
    private Button getStarted;
    private int position = 0;
    private Animation bttnAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // when activity is about to be launched, restorePrefData() checks if activity has been opened before
        if(restorePrefData()){
            Intent nextActivity = new Intent(getApplicationContext(), SignInCreateAccountActivity.class);
            startActivity(nextActivity);
            finish();
        }

        // initialize
        tabIndicator = findViewById(R.id.tabLayout);
        nextBttn = findViewById(R.id.nextButton);
        getStarted = findViewById(R.id.getStarted);
        bttnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

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
                screenPager.setCurrentItem(position, true); // move to the selected screen
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

        // next button click listener
        nextBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if(position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if(position == mList.size() - 1){ // when user reaches the last screen, it will hide the indicator & "Next" button and show the "Get Started" button
                    showButton();
                }
            }
        });
        // if user chooses to scroll instead of pressing the "Next" button, "Get Started" will still appear
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mList.size() - 1){
                    showButton();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to SignInCreateAccountActivity
                Intent nextActivity = new Intent(getApplicationContext(), SignInCreateAccountActivity.class);
                startActivity(nextActivity);
                // save boolean value to storage so next time the user runs the app
                // the app can understand the user has already viewed the IntroActivity
                savePrefsData();
                finish();
            }
        });
    }
    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroOpened = pref.getBoolean("isIntroOpened", false);
        return isIntroOpened;
    }

    // code to check if user viewed the IntroActivity
    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    // shows "Get Started" and hides indicator & "Next"
    private void showButton() {
        nextBttn.setVisibility(View.INVISIBLE);
        getStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // animation for "Get Started"
        getStarted.setAnimation(bttnAnimation);
    }
}