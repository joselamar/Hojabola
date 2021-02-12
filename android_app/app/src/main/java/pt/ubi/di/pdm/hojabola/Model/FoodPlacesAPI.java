package pt.ubi.di.pdm.hojabola.Model;

import java.util.ArrayList;

public class FoodPlacesAPI {
    private ArrayList<FoodPlaces> FoodPlaces;

    public ArrayList<FoodPlaces> getFoodPlaces() {
        return FoodPlaces;
    }

    public void setFoodPlaces(ArrayList<FoodPlaces> foodPlaces) {
        FoodPlaces = foodPlaces;
    }
}
