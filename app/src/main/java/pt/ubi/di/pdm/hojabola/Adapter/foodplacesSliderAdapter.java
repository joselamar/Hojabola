package pt.ubi.di.pdm.hojabola.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import pt.ubi.di.pdm.hojabola.Model.FoodPlaces;
import pt.ubi.di.pdm.hojabola.R;

public class foodplacesSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public ArrayList<FoodPlaces> foodPlaces;
    public int [] slideFoodPlaceimages;
    private fCommentaryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public foodplacesSliderAdapter(Context context, ArrayList<FoodPlaces> foodPlaces, int[] slideFoodPlaceimages) {
        this.context = context;
        this.foodPlaces = foodPlaces;
        this.slideFoodPlaceimages = slideFoodPlaceimages;
    }

    @Override
    public int getCount() {
        return foodPlaces.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_foodplace,container,false);

        ImageView foodplaceImage= view.findViewById(R.id.foodplaceImage);
        TextView foodplaceName=view.findViewById(R.id.foodNameTag);
        TextView foodplaceClassification= view.findViewById(R.id.foodClassificationTag);
        TextView foodplaceCoordinates = view.findViewById(R.id.foodCoordinatesTag);
        TextView foodplaceDistance = view.findViewById(R.id.foodDistanceTag);
        RecyclerView rvFoodplaces = view.findViewById(R.id.rvRestaurantComments);

        foodplaceImage.setBackgroundResource(slideFoodPlaceimages[position]);
        foodplaceName.setText(foodPlaces.get(position).getfName());
        foodplaceClassification.setText(foodPlaces.get(position).getfClassification());
        foodplaceCoordinates.setText(foodPlaces.get(position).getfCoordinates());
        foodplaceDistance.setText(foodPlaces.get(position).getfDistance());

        mLayoutManager = new LinearLayoutManager(context);
        mAdapter= new fCommentaryAdapter(foodPlaces.get(position).getfCommentary());
        rvFoodplaces.setLayoutManager(mLayoutManager);
        rvFoodplaces.setAdapter(mAdapter);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
