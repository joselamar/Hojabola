package pt.ubi.di.pdm.hojabola;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences sp;
    private ImageView icon;
    private api4app mService;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //to show logo
        icon = findViewById(R.id.icon);
        icon.setImageResource(R.drawable.football);

        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadein);
        //Animation anim=AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        icon.startAnimation(animation);
        //icon.startAnimation(anim);


        final Runnable r = new Runnable() {
            @Override
            public void run() {
                    if(!isLogged()){
                        AccessToken accessToken = AccessToken.getCurrentAccessToken();
                        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

                        if(!isLoggedIn){
                            finish();
                            Intent iActivity = new Intent(SplashScreen.this, Login.class);
                            startActivity(iActivity);
                        } else { isRegistered(); }

                    } else {
                        finish();
                        Intent iActivity = new Intent(SplashScreen.this, Main.class);
                        startActivity(iActivity);
                    }
            }

        };

        new Handler().postDelayed(r, 1000);
    }

    private boolean isLogged() {
        sp=getSharedPreferences("login",MODE_PRIVATE);
        Boolean isLog=sp.getBoolean("logged",false);
        return isLog;
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    //to check if user has network if not app doesnt work adapted from user:Squonk
    //https://stackoverflow.com/questions/4238921/detect-whether-there-is-an-internet-connection-available-on-android
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if ("WIFI".equals(ni.getTypeName()))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if ("MOBILE".equals(ni.getTypeName()))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void isRegistered() {
            mService= Common.getAPI();
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object,GraphResponse response) {
                    JSONObject json = response.getJSONObject();
                    try {
                        if(json != null){
                            email = json.getString("email");

                            mService.isUserRegistered(email)
                                    .enqueue(new Callback<UserAPI>() {
                                        @Override
                                        public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                                            UserAPI result= response.body();
                                            if(result.isError()){
                                                finish();
                                                Intent iActivity = new Intent(SplashScreen.this, Main.class);
                                                iActivity.putExtra("email",email);
                                                startActivity(iActivity);
                                            } else {
                                                finish();
                                                Intent iActivity = new Intent(SplashScreen.this, FacebookRegister.class);
                                                iActivity.putExtra("email",email);
                                                startActivity(iActivity);
                                            }

                                        }
                                        @Override
                                        public void onFailure(Call<UserAPI> call, Throwable t) {
                                        }
                                    });
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,email,picture");
            request.setParameters(parameters);
            request.executeAsync();
    }
}




