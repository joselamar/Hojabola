package pt.ubi.di.pdm.hojabola.Model;

public class User {
    public String uName;
    public String uEmail;
    public int has_idHas;

    public User(){
    }

    public User(String uName, String uEmail, int has_idHas) {
        this.uName = uName;
        this.uEmail = uEmail;
        this.has_idHas = has_idHas;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public int getHas_idHas() {
        return has_idHas;
    }

    public void setHas_idHas(int has_idHas) {
        this.has_idHas = has_idHas;
    }
}
