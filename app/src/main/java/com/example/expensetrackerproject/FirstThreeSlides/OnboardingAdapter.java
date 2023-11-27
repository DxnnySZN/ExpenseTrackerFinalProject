package com.example.expensetrackerproject.FirstThreeSlides;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class OnboardingAdapter extends FragmentStateAdapter {

    private ArrayList<Object> items;

    public OnboardingAdapter(FragmentActivity fragmentActivity, ArrayList<Object> items) {
        super(fragmentActivity);
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Object item = items.get(position);
        if (position == 2) {
            return LastOnboardingItemFragment.newInstance((LastOnboardingItem) item);
        }
        return FirstTwoSlidesFragment.newInstance((FirstTwoSlides) item);
    }
}
