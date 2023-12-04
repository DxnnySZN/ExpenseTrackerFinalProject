package com.example.expensetrackerproject.FirstThreeSlides;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expensetrackerproject.R;
import java.util.List;

public class IntroViewPagerAdapter extends RecyclerView.Adapter<IntroViewPagerAdapter.IntroViewHolder> {
    private Context mContext;
    private List<ScreenActivity> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenActivity> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_screen, parent, false);
        return new IntroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroViewHolder holder, int position) {
        ScreenActivity screen = mListScreen.get(position);
        holder.title.setText(screen.getTitle());
        holder.image.setImageResource(screen.getScreenImg());
    }

    @Override
    public int getItemCount() {
        return mListScreen != null ? mListScreen.size() : 0;
    }

    public static class IntroViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        public IntroViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.introTitle);
            image = itemView.findViewById(R.id.info1pic1);
        }
    }
}