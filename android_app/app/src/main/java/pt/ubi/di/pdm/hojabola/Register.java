package pt.ubi.di.pdm.hojabola;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private ImageView icon;
    private androidx.appcompat.widget.Toolbar mTopToolbar;
    private Button nextBtn;
    private api4app mService;
    private EditText registername,registeremail,registerpassword,registerconfirmpassword;
    ArrayList<String>registerInfo = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //to show logo
        icon = findViewById(R.id.icon);
        icon.setImageResource(R.drawable.football);

        mService = Common.getAPI();

        registername= findViewById(R.id.registerusername);
        registeremail= findViewById(R.id.registeremail);
        registerpassword= findViewById(R.id.registerpassword);
        registerconfirmpassword= findViewById(R.id.registerconfirmpassword);


        mTopToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setTitle("Registo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nextBtn= findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preRegisterUser(registername.getText().toString(),registeremail.getText().toString(),registerpassword.getText().toString(),registerconfirmpassword.getText().toString());
            }
        });



    }

    //to return to login activity on back button pressed
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        Intent iActivity = new Intent(this, Login.class);
        startActivity(iActivity);
        return true;
    }

    private void preRegisterUser(String name, String email, String password,String confirmpassword) {
        mService.preRegisterUser(name,email,password,confirmpassword)
                .enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                        UserAPI result= response.body();
                        if(result.isError()){
                            Toast.makeText(Register.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            registerInfo.add(registername.getText().toString());
                            registerInfo.add(registeremail.getText().toString());
                            registerInfo.add(registerpassword.getText().toString());
                            registerInfo.add(registerconfirmpassword.getText().toString());

                            Intent iActivity = new Intent(Register.this, chooseClub.class);
                            iActivity.putStringArrayListExtra("registerinfo",registerInfo);
                            startActivity(iActivity);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAPI> call, Throwable t) {
                        Toast.makeText(Register.this, "Ocorreu um Erro\nVerifique a ligação à Internet", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
