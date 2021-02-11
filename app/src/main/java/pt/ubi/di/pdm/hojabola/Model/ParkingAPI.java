package pt.ubi.di.pdm.hojabola.Model;

import java.util.ArrayList;

public class ParkingAPI {
    private ArrayList<Parking> Parking;

    public ParkingAPI() {
    }

    public ArrayList<pt.ubi.di.pdm.hojabola.Model.Parking> getParking() {
        return Parking;
    }

    public void setParking(ArrayList<pt.ubi.di.pdm.hojabola.Model.Parking> parking) {
        Parking = parking;
    }
}
