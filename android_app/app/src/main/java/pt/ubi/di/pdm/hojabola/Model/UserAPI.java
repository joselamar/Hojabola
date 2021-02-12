package pt.ubi.di.pdm.hojabola.Model;

public class UserAPI {
    private boolean error;
    private int idUser;
    private User user;
    private String error_msg;
    private String club;

    public UserAPI(){
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }


    public String getClub() { return club; }


}

