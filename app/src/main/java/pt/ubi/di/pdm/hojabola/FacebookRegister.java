package pt.ubi.di.pdm.hojabola;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacebookRegister extends AppCompatActivity {

    private ImageView icon;
    api4app mService;
    private Button facebookNextBtn;
    private EditText facebookregistername;
    private ArrayList<String>registerinfo=new ArrayList<>();
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_register);

        //to show logo
        icon = findViewById(R.id.icon);
        icon.setImageResource(R.drawable.football);

        mService = Common.getAPI();


        facebookNextBtn= findViewById(R.id.facebookNextBtn);
        facebookregistername= findViewById(R.id.facebookregisterusername);
        facebookNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookpreregister(facebookregistername.getText().toString());
            }
        });
    }

    private void facebookpreregister(String name) {
        mService.facebookPreRegisterUser(name)
                .enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                        UserAPI result= response.body();
                        if(result.isError()){
                            Toast.makeText(FacebookRegister.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            registerinfo.add(facebookregistername.getText().toString());
                            String email=getIntent().getStringExtra("email");
                            registerinfo.add(email);
                            //random password for facebook users
                            String everyone="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*\"-=+_()[{]}\\|;:\\'\\\",<.>/?";
                            Random random = new SecureRandom();
                            StringBuilder sb = new StringBuilder(25);
                            for (int i = 0; i < 20; i++) {
                                sb.append(everyone.charAt(random.nextInt(everyone.length())));
                            }
                            registerinfo.add(sb.toString());
                            registerinfo.add(sb.toString());

                            Intent iActivity = new Intent(FacebookRegister.this, chooseClub.class);
                            iActivity.putStringArrayListExtra("registerinfo",registerinfo);
                            iActivity.putExtra("fromFace","fromFace");
                            startActivity(iActivity);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAPI> call, Throwable t) {
                        Toast.makeText(FacebookRegister.this, "Ocorreu um Erro\nVerifique a ligação à Internet", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        LoginManager.getInstance().logOut();
        AccessToken.setCurrentAccessToken(null);
        finish();
        Intent iActivity = new Intent(FacebookRegister.this, Login.class);
        startActivity(iActivity);
    }
}
