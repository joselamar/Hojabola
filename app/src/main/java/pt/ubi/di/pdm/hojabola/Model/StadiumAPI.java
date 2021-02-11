package pt.ubi.di.pdm.hojabola.Model;

import java.util.ArrayList;

public class StadiumAPI {
    private ArrayList<Stadium> Stadium;

    StadiumAPI(){}

    public ArrayList<Stadium> getStadium() {
        return Stadium;
    }

    public void setStadium(ArrayList<Stadium> stadium) {
        Stadium = stadium;
    }
}
