package pt.ubi.di.pdm.hojabola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import pt.ubi.di.pdm.hojabola.Model.User;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    api4app mService;
    private database db;
    private SQLiteDatabase oSQLiteDB;
    private ImageView icon;
    private Button btnlogin;
    private TextView fgtPass,register;
    private EditText username,password;
    private LoginButton loginbutton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp=getSharedPreferences("login",MODE_PRIVATE);

        mService = Common.getAPI();
        username= findViewById(R.id.loginusername);
        password= findViewById(R.id.loginpassword);
        loginbutton= findViewById(R.id.login_button);

        //to show logo
        icon = findViewById(R.id.icon);
        icon.setImageResource(R.drawable.football);

        //get phone local db
        db = new database(this);
        oSQLiteDB = db.getWritableDatabase();


        //login user
        btnlogin= findViewById(R.id.login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser(username.getText().toString(),password.getText().toString());

            }
        });

        // start forgot password activity
        fgtPass= findViewById(R.id.fgtpassword);
        fgtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // start register activity on click
        register= findViewById(R.id.toRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iActivity = new Intent(Login.this, Register.class);
                startActivity(iActivity);
            }
        });

        //facebook login
        callbackManager = CallbackManager.Factory.create();
        loginbutton.setReadPermissions(Arrays.asList(EMAIL));
        loginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object,GraphResponse response) {
                        JSONObject json = response.getJSONObject();
                        try {
                            if(json!= null){
                                final EditText invisible;
                                invisible= findViewById(R.id.invisible);
                                invisible.setText(json.getString("email"));
                                mService.isUserRegistered(json.getString("email"))
                                        .enqueue(new Callback<UserAPI>() {
                                            @Override
                                            public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                                                UserAPI result= response.body();
                                                if(result.isError()){
                                                    finish();
                                                    Toast.makeText(Login.this, "Login com Sucesso!", Toast.LENGTH_SHORT).show();
                                                    Intent iActivity = new Intent(Login.this,Main.class);
                                                    startActivity(iActivity);
                                                }
                                                else{
                                                    finish();
                                                    Intent iActivity = new Intent(Login.this,FacebookRegister.class);
                                                    iActivity.putExtra("email",invisible.getText().toString());
                                                    startActivity(iActivity);
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<UserAPI> call, Throwable t) {
                                                Toast.makeText(Login.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,location,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Login cancelado!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Login.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //function to connect to server and check for login info if true login
    private void authenticateUser(String name, String password) {
        mService.loginUser(name,password)
                .enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                        UserAPI result= response.body();
                        if(result.isError()){
                            Toast.makeText(Login.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            User user =result.getUser();
                            Toast.makeText(Login.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();
                            sp.edit().putBoolean("logged",true).apply();
                            goToMainWithTheme(user.getuEmail());
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserAPI> call, Throwable t) {
                        Toast.makeText(Login.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //for facebook login
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void goToMainWithTheme(String email) {
        mService=Common.getAPI();
        mService.getUserClub(email)
                .enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                        UserAPI result= response.body();
                        if(result.isError()){
                            Toast.makeText(Login.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            sp=getSharedPreferences("login",MODE_PRIVATE);
                            String team=result.getClub();
                            sp.edit().putString("club", team).apply();
                            Intent iActivity = new Intent(Login.this,Main.class);
                            iActivity.putExtra("club",team);
                            startActivity(iActivity);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAPI> call, Throwable t) {
                        Toast.makeText(Login.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
