package pt.ubi.di.pdm.hojabola.Model;

public class Parking {
    private int idParking;
    private String pName;
    private String pAdress;
    private int pSpots;
    private int pAvailable;
    private String pCoordinates;
    private int isPayed;
    private String image;

    public Parking(int idParking, String pName, String pAdress, int pSpots, int pAvailable, String pCoordinates, int isPayed, String image) {
        this.idParking = idParking;
        this.pName = pName;
        this.pAdress = pAdress;
        this.pSpots = pSpots;
        this.pAvailable = pAvailable;
        this.pCoordinates = pCoordinates;
        this.isPayed = isPayed;
        this.image = image;
    }

    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAdress() {
        return pAdress;
    }

    public void setpAdress(String pAdress) {
        this.pAdress = pAdress;
    }

    public int getpSpots() {
        return pSpots;
    }

    public void setpSpots(int pSpots) {
        this.pSpots = pSpots;
    }

    public int getpAvailable() {
        return pAvailable;
    }

    public void setpAvailable(int pAvailable) {
        this.pAvailable = pAvailable;
    }

    public String getpCoordinates() {
        return pCoordinates;
    }

    public void setpCoordinates(String pCoordinates) {
        this.pCoordinates = pCoordinates;
    }

    public int getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(int isPayed) {
        this.isPayed = isPayed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
