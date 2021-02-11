package pt.ubi.di.pdm.hojabola.Model;

public class Game {
    private String score;
    private String competition;
    private String timeanddate;
    private String round;
    private String homeTeam;
    private String awayTeam;
    private String coordinates;
    private int stadium;

    public Game(String score, String competition, String timeanddate, String round, String homeTeam, String awayTeam, String coordinates, int stadium) {
        this.score = score;
        this.competition = competition;
        this.timeanddate = timeanddate;
        this.round = round;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.coordinates = coordinates;
        this.stadium = stadium;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getTimeanddate() {
        return timeanddate;
    }

    public void setTimeanddate(String timeanddate) {
        this.timeanddate = timeanddate;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public int getStadium() {
        return stadium;
    }

    public void setStadium(int stadium) {
        this.stadium = stadium;
    }
}
