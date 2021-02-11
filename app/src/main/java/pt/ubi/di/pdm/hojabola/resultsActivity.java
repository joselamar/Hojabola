package pt.ubi.di.pdm.hojabola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import pt.ubi.di.pdm.hojabola.Adapter.ResultAdapter;
import pt.ubi.di.pdm.hojabola.Adapter.resultsSliderAdapter;
import pt.ubi.di.pdm.hojabola.Model.Game;

public class resultsActivity extends AppCompatActivity {
    SharedPreferences sp;
    private ResultAdapter mAdapter;
    private resultsSliderAdapter resultsSliderAdapter;
    ViewPager viewPagerS;
    ArrayList<Game> games = new ArrayList<>();
    private database db;
    private Toolbar toolbar;
    private SQLiteDatabase oSQLiteDB;
    private int maxFixture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("login", MODE_PRIVATE);
        String team = getIntent().getStringExtra("club"); //from facebook login we get the club name from intent, so it sets the theme
        if (team==null) {
            team = sp.getString("club", "LoginTheme");
        }
        handleTheme(team); //to set the theme
        final String finalTeam = team;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        db = new database(this);
        oSQLiteDB = db.getWritableDatabase();

        viewPagerS= findViewById(R.id.viewPagerR);


        toolbar = findViewById(R.id.my_toolbarR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resultados");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Cursor oCursor = oSQLiteDB.rawQuery("SELECT MAX(gRound) FROM Game",new String[]{} );
        if(oCursor.moveToFirst()){
            maxFixture=oCursor.getInt(0);
        }

        resultsSliderAdapter = new resultsSliderAdapter(resultsActivity.this,maxFixture);
        viewPagerS.setAdapter(resultsSliderAdapter);
        viewPagerS.setCurrentItem(maxFixture-1);

    }

    //to return to main activity on back button pressed
    @Override
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
        }  else if (team.equals("Ferreira")){
            setTheme(R.style.ferreira);
        } else if (team.equals("Gil Vicente")){
            setTheme(R.style.gilvicente);
        } else if (team.equals("Maritimo")){
            setTheme(R.style.maritimo);
        } else if (team.equals("Moreirense")){
            setTheme(R.style.moreirense);
        }  else if (team.equals("Portimonense")){
            setTheme(R.style.portimonense);
        } else if (team.equals("Porto")){
            setTheme(R.style.porto);
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

}
