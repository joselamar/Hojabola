package pt.ubi.di.pdm.hojabola.Model;

public class Standings {
    private String position;
    private String clubName;
    private String gamesPlayed;
    private String goals;
    private String points;


    public Standings(String position, String clubName, String gamesPlayed, String goals, String points) {
        this.position = position;
        this.clubName = clubName;
        this.gamesPlayed = gamesPlayed;
        this.goals = goals;
        this.points = points;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(String gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
