package pt.ubi.di.pdm.hojabola.Remote;

public class Common {

    public static final String BASE_URL="https://35.181.153.96/projectapi/";

    public static api4app getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(api4app.class);
    }
}
