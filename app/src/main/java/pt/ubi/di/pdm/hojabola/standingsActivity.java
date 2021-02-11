package pt.ubi.di.pdm.hojabola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.ubi.di.pdm.hojabola.Adapter.StandingsAdapter;
import pt.ubi.di.pdm.hojabola.Model.Standings;
import pt.ubi.di.pdm.hojabola.Model.StandingsAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class standingsActivity extends AppCompatActivity {
    SharedPreferences sp;
    private Toolbar toolbar;
    api4app mService;
    private RecyclerView rvStandings;
    private RecyclerView.LayoutManager mLayoutManager;
    private StandingsAdapter mAdapter;
    private ArrayList<Standings> standings=new ArrayList<>();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp=getSharedPreferences("login",MODE_PRIVATE);
        String team=sp.getString("club","LoginTheme");
        handleTheme(team);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);

        mService= Common.getAPI();

        toolbar =findViewById(R.id.my_toolbarS);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Classificação");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvStandings=(RecyclerView)findViewById(R.id.rvStanding);
        mLayoutManager= new LinearLayoutManager(this);
        mAdapter= new StandingsAdapter(standings);
        rvStandings.setLayoutManager(mLayoutManager);
        rvStandings.setAdapter(mAdapter);

        dialog = new ProgressDialog(standingsActivity.this);
        dialog.setMessage("A carregar as Classificações...");
        dialog.setCancelable(false);
        dialog.show();
        mService.getStandings(1).enqueue(new Callback<StandingsAPI>() {
            @Override
            public void onResponse(Call<StandingsAPI> call, Response<StandingsAPI> response) {
                StandingsAPI result=response.body();
                ArrayList<Standings> aux=result.getStanding();
                for(int i=0;i<aux.size();i++){
                    Standings s=new Standings(aux.get(i).getPosition(),aux.get(i).getClubName(),aux.get(i).getGamesPlayed(),aux.get(i).getGoals(),aux.get(i).getPoints());
                    standings.add(s);
                }

                mAdapter.updateStandings(standings);

                if(!(dialog==null))
                    if (dialog.isShowing())
                        dialog.dismiss();

            }

            @Override
            public void onFailure(Call<StandingsAPI> call, Throwable t) {
                if(!(dialog==null))
                    if (dialog.isShowing())
                        dialog.dismiss();
                Toast.makeText(standingsActivity.this, "Ocorreu um erro\nVerifique a sua coneção à Internet", Toast.LENGTH_LONG).show();

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
        Intent iActivity = new Intent(this,Main.class);
        startActivity(iActivity);
        return true;
    }

    public void onBackPressed() {
        finish();
        Intent iActivity = new Intent(this,Main.class);
        startActivity(iActivity);
    }
}
