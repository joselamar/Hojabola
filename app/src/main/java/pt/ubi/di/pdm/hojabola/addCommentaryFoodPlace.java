package pt.ubi.di.pdm.hojabola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addCommentaryFoodPlace extends AppCompatActivity {
    int stadium,idFoodPlace;
    String coordinates,title;
    api4app mService;
    private Toolbar toolbar;
    private EditText commentary,classification;
    private Button addCritic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String team = getIntent().getStringExtra("club");
        if (team==null) {
            team = sp.getString("club", "LoginTheme");
        }
        handleTheme(team); //to set the theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commentary_food_place);

        //intens for not losing the connection between gameOptios Parking and comments
        idFoodPlace=getIntent().getIntExtra("idFoodPlace",0);
        stadium=getIntent().getIntExtra("stadium",0);
        coordinates=getIntent().getStringExtra("coordinates");
        title=getIntent().getStringExtra("title");

        toolbar=(Toolbar)findViewById(R.id.my_toolbarACFP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Adicionar Crítica");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        commentary=(EditText)findViewById(R.id.commentaryFP);
        classification=(EditText)findViewById(R.id.classification);
        addCritic=(Button)findViewById(R.id.addCritic);

        mService= Common.getAPI();

        addCritic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.addFoodPlaceCommentary(idFoodPlace,classification.getText().toString(),commentary.getText().toString()).enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                        UserAPI result=response.body();
                        if(result.isError()){
                            Toast.makeText(addCommentaryFoodPlace.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(addCommentaryFoodPlace.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                            Intent iActivity = new Intent(addCommentaryFoodPlace.this,foodplacesActivity.class);
                            iActivity.putExtra("stadium",stadium);
                            iActivity.putExtra("coordinates",coordinates);
                            iActivity.putExtra("title", title);
                            startActivity(iActivity);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAPI> call, Throwable t) {
                        Toast.makeText(addCommentaryFoodPlace.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    public void handleTheme(String team){
        if (team.equals("Aves")){
            setTheme(R.style.aves);
        } else if (team.equals("Belenenses")){
            setTheme(R.style.belenenses);
        } else if (team.equals("Benfica")){
            setTheme(R.style.benfica);
        } else if (team.equals("Boavista")){
            setTheme(R.style.boavista);
        } else if (team.equals("Braga")){
            setTheme(R.style.braga);
        } else if (team.equals("Famalicao")){
            setTheme(R.style.famalicao);
        } else if (team.equals("Porto")){
            setTheme(R.style.porto);
        } else if (team.equals("Ferreira")){
            setTheme(R.style.ferreira);
        } else if (team.equals("Maritimo")){
            setTheme(R.style.maritimo);
        } else if (team.equals("Moreirense")){
            setTheme(R.style.moreirense);
        } else if (team.equals("Gil Vicente")){
            setTheme(R.style.gilvicente);
        } else if (team.equals("Portimonense")){
            setTheme(R.style.portimonense);
        } else if (team.equals("Rio Ave")){
            setTheme(R.style.rioave);
        } else if (team.equals("Santa Clara")){
            setTheme(R.style.santaclara);
        } else if (team.equals("Sporting")){
            setTheme(R.style.sporting);
        } else if (team.equals("Tondela")){
            setTheme(R.style.tondela);
        }  else if (team.equals("Setubal")){
            setTheme(R.style.setubal);
        }  else if (team.equals("Guimaraes")){
            setTheme(R.style.guimaraes);
        }
    }

    public boolean onSupportNavigateUp(){
        finish();
        Intent iActivity = new Intent(this,foodplacesActivity.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
        return true;
    }

    public void onBackPressed() {
        finish();
        Intent iActivity = new Intent(this,foodplacesActivity.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
    }

}
