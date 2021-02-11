package pt.ubi.di.pdm.hojabola;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chooseClub extends AppCompatActivity {


    private ImageView logo1,logo2,logo3,logo4,logo5,logo6,logo7,logo8,logo9,logo10,logo11,logo12,logo13,logo14,logo15,logo16,logo17,logo18;
    private TextView textlogo1,textlogo2,textlogo3,textlogo4,textlogo5,textlogo6,textlogo7,textlogo8,textlogo9,textlogo10,textlogo11,textlogo12,textlogo13,textlogo14,textlogo15,textlogo16,textlogo17,textlogo18;
    private String club = null;
    SharedPreferences sp;
    private EditText choosedClub;
    private Button registerBtn;
    api4app mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_club);

        mService = Common.getAPI();

        choosedClub= findViewById(R.id.choosedclub);
        registerBtn= findViewById(R.id.registerBtn);

        choosedClub.setClickable(false);
        choosedClub.setFocusable(false);
        choosedClub.setLongClickable(false);

        logo1= findViewById(R.id.logo1);
        logo1.setBackgroundResource(R.drawable.aveslogo);
        logo2= findViewById(R.id.logo2);
        logo2.setBackgroundResource(R.drawable.belenenseslogo);
        logo3= findViewById(R.id.logo3);
        logo3.setBackgroundResource(R.drawable.benficalogo);
        logo4= findViewById(R.id.logo4);
        logo4.setBackgroundResource(R.drawable.boavistalogo);
        logo5= findViewById(R.id.logo5);
        logo5.setBackgroundResource(R.drawable.bragalogo);
        logo6= findViewById(R.id.logo6);
        logo6.setBackgroundResource(R.drawable.famalicaologo);
        logo7= findViewById(R.id.logo7);
        logo7.setBackgroundResource(R.drawable.pacosferreiralogo);
        logo8= findViewById(R.id.logo8);
        logo8.setBackgroundResource(R.drawable.gilvicentelogo);
        logo9= findViewById(R.id.logo9);
        logo9.setBackgroundResource(R.drawable.maritimologo);
        logo10= findViewById(R.id.logo10);
        logo10.setBackgroundResource(R.drawable.moreirenselogo);
        logo11= findViewById(R.id.logo11);
        logo11.setBackgroundResource(R.drawable.portimonenselogo);
        logo12= findViewById(R.id.logo12);
        logo12.setBackgroundResource(R.drawable.portologo);
        logo13= findViewById(R.id.logo13);
        logo13.setBackgroundResource(R.drawable.rioavelogo);
        logo14= findViewById(R.id.logo14);
        logo14.setBackgroundResource(R.drawable.santaclaralogo);
        logo15= findViewById(R.id.logo15);
        logo15.setBackgroundResource(R.drawable.sportinglogo);
        logo16= findViewById(R.id.logo16);
        logo16.setBackgroundResource(R.drawable.tondelalogo);
        logo17= findViewById(R.id.logo17);
        logo17.setBackgroundResource(R.drawable.setuballogo);
        logo18= findViewById(R.id.logo18);
        logo18.setBackgroundResource(R.drawable.guimaraeslogo);

        logo1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo1 = findViewById(R.id.textlogo1);
                        club = textlogo1.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
        });
                logo2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo2= findViewById(R.id.textlogo2);
                        club=textlogo2.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo3= findViewById(R.id.textlogo3);
                        club=textlogo3.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo4= findViewById(R.id.textlogo4);
                        club=textlogo4.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo5= findViewById(R.id.textlogo5);
                        club=textlogo5.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo6= findViewById(R.id.textlogo6);
                        club=textlogo6.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo7= findViewById(R.id.textlogo7);
                        club=textlogo7.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo8= findViewById(R.id.textlogo8);
                        club=textlogo8.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo9= findViewById(R.id.textlogo9);
                        club=textlogo9.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo10= findViewById(R.id.textlogo10);
                        club=textlogo10.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo11= findViewById(R.id.textlogo11);
                        club=textlogo11.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo12= findViewById(R.id.textlogo12);
                        club=textlogo12.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo13= findViewById(R.id.textlogo13);
                        club=textlogo13.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo14= findViewById(R.id.textlogo14);
                        club=textlogo14.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo15= findViewById(R.id.textlogo15);
                        club=textlogo15.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo16= findViewById(R.id.textlogo16);
                        club=textlogo16.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo17= findViewById(R.id.textlogo17);
                        club=textlogo17.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });
                logo18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textlogo18= findViewById(R.id.textlogo18);
                        club=textlogo18.getText().toString();
                        choosedClub= findViewById(R.id.choosedclub);
                        choosedClub.setText(club);
                        choosedClub.setClickable(false);
                        choosedClub.setFocusable(false);
                        choosedClub.setLongClickable(false);
                    }
                });

                registerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String>aux=getIntent().getStringArrayListExtra("registerinfo");
                        createNewUser(aux.get(0),aux.get(1),aux.get(2),aux.get(3),choosedClub.getText().toString());
                    }
                });

    }

    private void createNewUser(String name, String email, String password,String confirmpassword,final String club) {
        sp=getSharedPreferences("login",MODE_PRIVATE);
        mService.registerUser(name,email,password,confirmpassword,club)
                .enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                        UserAPI result= response.body();
                        if(result.isError()){
                            Toast.makeText(chooseClub.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(chooseClub.this, "Registo completado com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();

                            if(getIntent().getStringExtra("fromFace")!=null){
                                Intent iActivity = new Intent(chooseClub.this, Main.class);
                                iActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                sp.edit().putString("club", club).apply();
                                startActivity(iActivity);
                            } else {
                                Intent iActivity = new Intent(chooseClub.this, Login.class);
                                iActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(iActivity);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<UserAPI> call, Throwable t) {
                        Toast.makeText(chooseClub.this, "Ocorreu um Erro\nVerifique a ligação à Internet", Toast.LENGTH_LONG).show();
                    }
                });
    }

}