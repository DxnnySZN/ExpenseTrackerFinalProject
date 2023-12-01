package com.example.expensetrackerproject.FirstThreeSlides;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.expensetrackerproject.R;

import java.util.List;


public class IntroViewPagerAdapter extends PagerAdapter {
    Context mContext;
    List<ScreenActivity> mListScreen;
    public IntroViewPagerAdapter(Context mContext, List<ScreenActivity> mListScreen){
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.activity_screen, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.info1pic1);
        TextView title = layoutScreen.findViewById(R.id.introTitle);

        title.setText(mListScreen.get(position).getTitle());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(layoutScreen);
        return layoutScreen;
    }
    @Override
    public int getCount(){
        return mListScreen.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o){
        return view == o;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((View) object);
    }
}