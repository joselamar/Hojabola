package pt.ubi.di.pdm.hojabola.Model;

import java.util.ArrayList;

public class FoodPlaces {
    private int idFoodPlace;
    private String fName;
    private String fClassification;
    private String fCoordinates;
    private String fDistance;
    private ArrayList<fCommentary> fCommentary;

    public FoodPlaces(int idFoodPlace, String fName, String fClassification, String fCoordinates, String fDistance, ArrayList<pt.ubi.di.pdm.hojabola.Model.fCommentary> fCommentary) {
        this.idFoodPlace = idFoodPlace;
        this.fName = fName;
        this.fClassification = fClassification;
        this.fCoordinates = fCoordinates;
        this.fDistance = fDistance;
        this.fCommentary = fCommentary;
    }

    public int getIdFoodPlace() {
        return idFoodPlace;
    }

    public void setIdFoodPlace(int idFoodPlace) {
        this.idFoodPlace = idFoodPlace;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfClassification() {
        return fClassification;
    }

    public void setfClassification(String fClassification) {
        this.fClassification = fClassification;
    }

    public String getfCoordinates() {
        return fCoordinates;
    }

    public void setfCoordinates(String fCoordinates) {
        this.fCoordinates = fCoordinates;
    }

    public String getfDistance() {
        return fDistance;
    }

    public void setfDistance(String fDistance) {
        this.fDistance = fDistance;
    }

    public ArrayList<pt.ubi.di.pdm.hojabola.Model.fCommentary> getfCommentary() {
        return fCommentary;
    }

    public void setfCommentary(ArrayList<pt.ubi.di.pdm.hojabola.Model.fCommentary> fCommentary) {
        this.fCommentary = fCommentary;
    }
}
