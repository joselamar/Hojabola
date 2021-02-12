package pt.ubi.di.pdm.hojabola.Model;

import java.util.ArrayList;

public class GameAPI {
    private ArrayList<Game> Game;

    GameAPI(){}

    public ArrayList<pt.ubi.di.pdm.hojabola.Model.Game> getGame() {
        return Game;
    }

    public void setGame(ArrayList<pt.ubi.di.pdm.hojabola.Model.Game> game) {
        Game = game;
    }
}
