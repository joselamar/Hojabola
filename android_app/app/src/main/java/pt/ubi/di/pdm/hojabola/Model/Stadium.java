package pt.ubi.di.pdm.hojabola.Model;

public class Stadium {
    private String sName;
    private String sClub;
    private String sAdress;
    private String sYear;
    private String sCapacity;
    private String sCoordinates;
    private String sHistory;

    public Stadium(String sName, String sClub, String sAdress, String sYear, String sCapacity, String sCoordinates, String sHistory) {
        this.sName = sName;
        this.sClub = sClub;
        this.sAdress = sAdress;
        this.sYear = sYear;
        this.sCapacity = sCapacity;
        this.sCoordinates = sCoordinates;
        this.sHistory = sHistory;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsClub() {
        return sClub;
    }

    public void setsClub(String sClub) {
        this.sClub = sClub;
    }

    public String getsAdress() {
        return sAdress;
    }

    public void setsAdress(String sAdress) {
        this.sAdress = sAdress;
    }

    public String getsYear() {
        return sYear;
    }

    public void setsYear(String sYear) {
        this.sYear = sYear;
    }

    public String getsCapacity() {
        return sCapacity;
    }

    public void setsCapacity(String sCapacity) {
        this.sCapacity = sCapacity;
    }

    public String getsCoordinates() {
        return sCoordinates;
    }

    public void setsCoordinates(String sCoordinates) {
        this.sCoordinates = sCoordinates;
    }

    public String getsHistory() {
        return sHistory;
    }

    public void setsHistory(String sHistory) {
        this.sHistory = sHistory;
    }
}
