package pt.ubi.di.pdm.hojabola.Model;

import java.util.ArrayList;

public class StandingsAPI {
    private ArrayList<Standings> Standings;

    public StandingsAPI() {
    }

    public ArrayList<Standings> getStanding() {
        return Standings;
    }

    public void setStanding(ArrayList<Standings> standing) {
        Standings = standing;
    }
}
