package com.example.expensetrackerproject.FirstThreeSlides;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.expensetrackerproject.R;
import com.example.expensetrackerproject.Authentication.SignInCreateAccountActivity;

public class LastOnboardingItemFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_last_onboarding_item, container, false);
        LastOnboardingItem item = getArguments().getParcelable("item");

        // Initialize and bind UI elements using the item data
        TextView exampleTextView = view.findViewById(R.id.introTitle);
        ImageView exampleImageView = view.findViewById(R.id.moneyspread);
        exampleTextView.setText(item.getTextOnTop());
        exampleImageView.setImageResource(item.getImageResourceId());
        Button button = view.findViewById(R.id.getStarted);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be executed when the button is clicked
               Intent myIntent = new Intent(v.getContext(), SignInCreateAccountActivity.class);
               v.getContext().startActivity(myIntent);
            }
        });
        return view;
    }
    public static LastOnboardingItemFragment newInstance(LastOnboardingItem item) {
        LastOnboardingItemFragment fragment = new LastOnboardingItemFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        fragment.setArguments(args);
        return fragment;
    }
}
