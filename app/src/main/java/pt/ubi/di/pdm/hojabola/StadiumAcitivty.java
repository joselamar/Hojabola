package pt.ubi.di.pdm.hojabola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import pt.ubi.di.pdm.hojabola.Model.Stadium;

public class StadiumAcitivty extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView stadiumImage;
    private database db;
    private SQLiteDatabase oSQLiteDB;
    private TextView stadiumNameTag,stadiumClubTag,stadiumYearTag,stadiumCapacityTag,stadiumHistoryTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String team = getIntent().getStringExtra("club");
        if (team==null) {
            team = sp.getString("club", "LoginTheme");
        }
        handleTheme(team); //to set the theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);

        //get db
        db = new database(this);
        oSQLiteDB = db.getWritableDatabase();

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Estádio do "+team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    handleStadiumImage(team);
    populateActivity(team);

    }

    private void populateActivity(String team) {
        team=getTeamName(team);
        Cursor oCursor = oSQLiteDB.rawQuery("SELECT * FROM Stadium WHERE sClub=?",new String[]{team} );
        oCursor.moveToFirst();
        Stadium s = new Stadium(oCursor.getString(1),oCursor.getString(2),oCursor.getString(3),oCursor.getString(4),oCursor.getString(5),oCursor.getString(6),oCursor.getString(7));
        stadiumNameTag= findViewById(R.id.stadiumNameTag);
        stadiumClubTag= findViewById(R.id.stadiumClubTag);
        stadiumCapacityTag= findViewById(R.id.stadiumCapacityTag);
        stadiumYearTag= findViewById(R.id.stadiumYearTag);
        stadiumHistoryTag= findViewById(R.id.stadiumHistoryTag);
        stadiumNameTag.setText(s.getsName());
        stadiumClubTag.setText(s.getsClub());
        stadiumCapacityTag.setText(s.getsCapacity());
        stadiumYearTag.setText(s.getsYear());
        stadiumHistoryTag.setText(s.getsHistory());
    }

    private String getTeamName(String team) {
        if (team.equals("Aves")){
            team="Clube Desportivo das Aves";
        } else if (team.equals("Belenenses")){
            team="Belenenses";
        } else if (team.equals("Benfica")){
            team="Sport Lisboa e Benfica";
        } else if (team.equals("Boavista")){
            team="Boavista Futebol Clube";
        } else if (team.equals("Braga")){
            team="Sporting Clube de Braga";
        } else if (team.equals("Famalicao")){
            team="Futebol Clube Famalicão";
        }  else if (team.equals("Ferreira")){
            team="Futebol Clube Paços Ferreira";
        } else if (team.equals("Gil Vicente")){
            team="Gil Vicente Futebol Clube";
        } else if (team.equals("Maritimo")){
            team="Club Sport Marítimo";
        } else if (team.equals("Moreirense")){
            team="Moreirense Futebol Clube";
        }  else if (team.equals("Portimonense")){
            team="Portimonense Sport Clube";
        } else if (team.equals("Porto")){
            team="Futebol Clube do Porto";
        } else if (team.equals("Rio Ave")){
            team="Rio Ave Futebol Clube";
        } else if (team.equals("Santa Clara")){
            team="Clube Desportivo Santa Clara";
        } else if (team.equals("Sporting")){
            team="Sporting Clube de Portugal";
        } else if (team.equals("Tondela")){
            team="Clube Desportivo Tondela";
        }  else if (team.equals("Setubal")){
            team="Vitória Futebol Clube";
        }  else if (team.equals("Guimaraes")){
            team="Vitória Sport Clube";
        }
        return team;
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

    public void handleStadiumImage(String team){
        stadiumImage= findViewById(R.id.stadiumImage);
        if (team.equals("Aves")){
            stadiumImage.setBackgroundResource(R.drawable.avesstadium);
        } else if (team.equals("Belenenses")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumbelenenses);
        } else if (team.equals("Benfica")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumbenfica);
        } else if (team.equals("Boavista")){
           stadiumImage.setBackgroundResource(R.drawable.stadiumboavista);
        } else if (team.equals("Braga")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumbraga);
        } else if (team.equals("Famalicao")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumfamalicao);
        }  else if (team.equals("Ferreira")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumferreira);
        } else if (team.equals("Gil Vicente")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumgilvicente);
        } else if (team.equals("Maritimo")){
            stadiumImage.setBackgroundResource(R.drawable.stadiummaritimo);
        } else if (team.equals("Moreirense")){
            stadiumImage.setBackgroundResource(R.drawable.stadiummoreirense);
        }  else if (team.equals("Portimonense")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumportimonense);
        } else if (team.equals("Porto")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumporto);
        } else if (team.equals("Rio Ave")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumrioave);
        } else if (team.equals("Santa Clara")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumsantaclara);
        } else if (team.equals("Sporting")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumsporting);
        } else if (team.equals("Tondela")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumtondela);
        }  else if (team.equals("Setubal")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumsetubal);
        }  else if (team.equals("Guimaraes")){
            stadiumImage.setBackgroundResource(R.drawable.stadiumguimaraes);
        }
    }

}
