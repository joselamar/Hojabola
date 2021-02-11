package pt.ubi.di.pdm.hojabola.Model;

public class Commentary {
    private String date;
    private String comment;
    private int aSpots;

    public Commentary(String date, String comment, int aSpots) {
        this.date = date;
        this.comment = comment;
        this.aSpots = aSpots;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getaSpots() {
        return aSpots;
    }

    public void setaSpots(int aSpots) {
        this.aSpots = aSpots;
    }
}
