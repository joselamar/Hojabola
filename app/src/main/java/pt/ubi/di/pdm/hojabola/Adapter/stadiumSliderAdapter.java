package pt.ubi.di.pdm.hojabola.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ubi.di.pdm.hojabola.R;

public class stadiumSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public ArrayList<String> slideStadiumName;
    public ArrayList<String> slideStadiumClub;
    public ArrayList<String> slideStadiumCapacity;
    public ArrayList<String> slideStadiumYear;
    public ArrayList<String> slideStadiumHistory;
    public int [] slideStadiumsImages;

    public stadiumSliderAdapter(Context context, ArrayList<String> slideStadiumName, ArrayList<String> slideStadiumClub, ArrayList<String> slideStadiumCapacity, ArrayList<String> slideStadiumYear, ArrayList<String> slideStadiumHistory, int[] slideStadiumsImages) {
        this.context = context;
        this.slideStadiumName = slideStadiumName;
        this.slideStadiumClub = slideStadiumClub;
        this.slideStadiumCapacity = slideStadiumCapacity;
        this.slideStadiumYear = slideStadiumYear;
        this.slideStadiumHistory = slideStadiumHistory;
        this.slideStadiumsImages = slideStadiumsImages;
    }


    @Override
    public int getCount() {
        return slideStadiumsImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_stadium, container,false);


        ImageView stadiumImage= view.findViewById(R.id.stadiumImageR);
        TextView stadiumNameTag= view.findViewById(R.id.stadiumNameTagR);
        TextView stadiumClubTag= view.findViewById(R.id.stadiumClubTagR);
        TextView stadiumCapacityTag= view.findViewById(R.id.stadiumCapacityTagR);
        TextView stadiumYearTag= view.findViewById(R.id.stadiumYearTagR);
        TextView stadiumHistoryTag= view.findViewById(R.id.stadiumHistoryTagR);

        stadiumImage.setBackgroundResource(slideStadiumsImages[position]);
        stadiumNameTag.setText(slideStadiumName.get(position));
        stadiumClubTag.setText(slideStadiumClub.get(position));
        stadiumCapacityTag.setText(slideStadiumCapacity.get(position));
        stadiumYearTag.setText(slideStadiumYear.get(position));
        stadiumHistoryTag.setText(slideStadiumHistory.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
