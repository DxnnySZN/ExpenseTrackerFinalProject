package com.example.expensetrackerproject.FirstThreeSlides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.expensetrackerproject.R;
import com.google.android.material.tabs.TabLayout;

public class FirstTwoSlidesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_first_two_slides, container, false);
        FirstTwoSlides item = getArguments().getParcelable("item");

        // Initialize and bind UI elements using the item data
        TextView titleTV = view.findViewById(R.id.introTitle);
        ImageView info1pic1 = view.findViewById(R.id.info1pic1);


        titleTV.setText(item.getTitle());
        info1pic1.setImageResource(item.getImgId1());

        // For example, set item.getTitle(), item.getDescription(), and item.getImageResId() to TextViews and ImageView

        return view;
    }

    public static FirstTwoSlidesFragment newInstance(FirstTwoSlides item) {
        FirstTwoSlidesFragment fragment = new FirstTwoSlidesFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        fragment.setArguments(args);
        return fragment;
    }
}