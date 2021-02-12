package pt.ubi.di.pdm.hojabola;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pt.ubi.di.pdm.hojabola.Adapter.GameAdapter;
import pt.ubi.di.pdm.hojabola.Model.GameAPI;
import pt.ubi.di.pdm.hojabola.Model.Game;
import pt.ubi.di.pdm.hojabola.Model.Stadium;
import pt.ubi.di.pdm.hojabola.Model.StadiumAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView logo1,logo2;
    private TextView club1name,club2name,gametimeanddate;
    private RelativeLayout rl;
    private AdView mAdView;
    private RecyclerView mRecyclerView;
    SharedPreferences sp;
    FloatingActionButton fab;
    private GameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Game> games = new ArrayList<>();
    api4app mService;
    String coordinates;
    private database db;
    private SQLiteDatabase oSQLiteDB;
    private int flag=0,stadium;
    private ProgressDialog dialog;

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
        setContentView(R.layout.activity_main);
        //get db
        db = new database(this);
        oSQLiteDB = db.getWritableDatabase();

        //get Games from favorite team if never got them or if it hasn't done that in 5 days
        String date=sp.getString("date","");
        if(date.equals("")){
            handleGames(team);
            handleStadiums(team);
            flag=1;
        } else {
            long timeElapsed=Long.valueOf(date)-System.currentTimeMillis();
            if(timeElapsed>432000000){
                handleGames(team);
                flag=1;
            }
        }

        //connect elements to android
        logo1= findViewById(R.id.logo1);
        club1name= findViewById(R.id.club1name);
        logo2= findViewById(R.id.logo2);
        club2name= findViewById(R.id.club2name);
        gametimeanddate= findViewById(R.id.gametimeanddate);


        //button to show stadium info
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iActivity = new Intent(Main.this, StadiumAcitivty.class);
                iActivity.putExtra("team",finalTeam);
                startActivity(iActivity);
            }
        });

        //navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //load AD
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3741836068351818~6036997726");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //load RV
        mRecyclerView= findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GameAdapter(games);

        if(flag==0)
            populateRV(finalTeam);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //click listener to get selected game from rv
        mAdapter.setOnItemClickListener(new GameAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent iActivity = new Intent(getApplicationContext(),gameOptions.class);
                iActivity.putExtra("stadium",games.get(position).getStadium());
                iActivity.putExtra("coordinates",games.get(position).getCoordinates());
                iActivity.putExtra("title", games.get(position).getHomeTeam()+" vs "+games.get(position).getAwayTeam());
                startActivity(iActivity);
                finish();
            }
        });

        rl= findViewById(R.id.rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iActivity = new Intent(getApplicationContext(),gameOptions.class);
                iActivity.putExtra("stadium",stadium);
                iActivity.putExtra("coordinates",coordinates);
                iActivity.putExtra("title", club1name.getText()+" vs "+club2name.getText());
                startActivity(iActivity);
                finish();
            }
        });

    }


    // for when user is logged in he doesn't go back to start activity
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.moveTaskToBack(true);
        }
    }

    // Inflate the menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    // Handle action bar item clicks here.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Handle navigation view item clicks here.
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.results) {
            finish();
            Intent iActivity = new Intent(Main.this, resultsActivity.class);
            iActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(iActivity);
        } else if (id == R.id.standings) {
            finish();
            Intent iActivity = new Intent(Main.this, standingsActivity.class);
            iActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(iActivity);
        } else if (id == R.id.stadiums) {
            finish();
            Intent iActivity = new Intent(Main.this, StadiumsActivity.class);
            iActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(iActivity);
        } else if (id == R.id.reviews) {

        } else if (id == R.id.nav_logout){
            sp = getSharedPreferences("login",MODE_PRIVATE);
            sp.edit().putBoolean("logged",false).apply();
            sp.edit().putString("club","LoginTheme").apply();
            sp.edit().putString("date","").apply();
            LoginManager.getInstance().logOut();
            AccessToken.setCurrentAccessToken(null);
            finish();
            Intent iActivity = new Intent(Main.this, Login.class);
            iActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(iActivity);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //to show theme for every club
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

    //to set image for homeTeam
    public void handleHomeTeam(String team){
        if (team.equals("Aves")){
            logo1.setBackgroundResource(R.drawable.aveslogo);
        } else if (team.equals("Belenenses")){
            logo1.setBackgroundResource(R.drawable.belenenseslogo);
        } else if (team.equals("Benfica")){
            logo1.setBackgroundResource(R.drawable.benficalogo);
        } else if (team.equals("Boavista")){
            logo1.setBackgroundResource(R.drawable.boavistalogo);
        } else if (team.equals("Braga")){
            logo1.setBackgroundResource(R.drawable.bragalogo);
        } else if (team.equals("Famalicao")){
            logo1.setBackgroundResource(R.drawable.famalicaologo);
        } else if (team.equals("Ferreira")){
            logo1.setBackgroundResource(R.drawable.pacosferreiralogo);
        } else if (team.equals("Gil Vicente")){
            logo1.setBackgroundResource(R.drawable.gilvicentelogo);
        } else if (team.equals("Maritimo")){
            logo1.setBackgroundResource(R.drawable.maritimologo);
        } else if (team.equals("Moreirense")){
            logo1.setBackgroundResource(R.drawable.moreirenselogo);
        } else if (team.equals("Portimonense")){
            logo1.setBackgroundResource(R.drawable.portimonenselogo);
        } else if (team.equals("Porto")){
            logo1.setBackgroundResource(R.drawable.portologo);
        } else if (team.equals("Rio Ave")){
            logo1.setBackgroundResource(R.drawable.rioavelogo);
        } else if (team.equals("Santa Clara")){
            logo1.setBackgroundResource(R.drawable.santaclaralogo);
        } else if (team.equals("Sporting")){
            logo1.setBackgroundResource(R.drawable.sportinglogo);
        } else if (team.equals("Tondela")){
            logo1.setBackgroundResource(R.drawable.tondelalogo);
        }  else if (team.equals("Setubal")){
            logo1.setBackgroundResource(R.drawable.setuballogo);
        }  else if (team.equals("Guimaraes")){
            logo1.setBackgroundResource(R.drawable.guimaraeslogo);
        }
    }

    //to set image for awayTeam
    public void handleAwayTeam(String team){
        if (team.equals("Aves")){
            logo2.setBackgroundResource(R.drawable.aveslogo);
        } else if (team.equals("Belenenses")){
            logo2.setBackgroundResource(R.drawable.belenenseslogo);
        } else if (team.equals("Benfica")){
            logo2.setBackgroundResource(R.drawable.benficalogo);
        } else if (team.equals("Boavista")){
            logo2.setBackgroundResource(R.drawable.boavistalogo);
        } else if (team.equals("Braga")){
            logo2.setBackgroundResource(R.drawable.bragalogo);
        } else if (team.equals("Famalicao")){
            logo2.setBackgroundResource(R.drawable.famalicaologo);
        } else if (team.equals("Ferreira")){
            logo2.setBackgroundResource(R.drawable.pacosferreiralogo);
        } else if (team.equals("Gil Vicente")){
            logo2.setBackgroundResource(R.drawable.gilvicentelogo);
        } else if (team.equals("Maritimo")){
            logo2.setBackgroundResource(R.drawable.maritimologo);
        } else if (team.equals("Moreirense")){
            logo2.setBackgroundResource(R.drawable.moreirenselogo);
        } else if (team.equals("Portimonense")){
            logo2.setBackgroundResource(R.drawable.portimonenselogo);
        } else if (team.equals("Porto")){
            logo2.setBackgroundResource(R.drawable.portologo);
        } else if (team.equals("Rio Ave")){
            logo2.setBackgroundResource(R.drawable.rioavelogo);
        } else if (team.equals("Santa Clara")){
            logo2.setBackgroundResource(R.drawable.santaclaralogo);
        } else if (team.equals("Sporting")){
            logo2.setBackgroundResource(R.drawable.sportinglogo);
        } else if (team.equals("Tondela")){
            logo2.setBackgroundResource(R.drawable.tondelalogo);
        }  else if (team.equals("Setubal")){
            logo2.setBackgroundResource(R.drawable.setuballogo);
        }  else if (team.equals("Guimaraes")){
            logo2.setBackgroundResource(R.drawable.guimaraeslogo);
        }
    }

    //fetch Games from API
    public void handleGames(final String team){
        dialog = new ProgressDialog(Main.this);
        dialog.setMessage("A carregar os Jogos...");
        dialog.setCancelable(false);
        dialog.show();

        mService= Common.getAPI();
        mService.getClubGames(team).enqueue(new Callback<GameAPI>() {
            @Override
            public void onResponse(Call<GameAPI> call, Response<GameAPI> response) {
                GameAPI result=response.body();
                ArrayList<Game> aux = result.getGame();

                for(int i=0;i< aux.size();i++){
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("gScore",aux.get(i).getScore());
                    contentValues.put("Competition",aux.get(i).getCompetition());
                    contentValues.put("gDate",aux.get(i).getTimeanddate());
                    contentValues.put("gRound",aux.get(i).getRound());
                    contentValues.put("homeClub",aux.get(i).getHomeTeam());
                    contentValues.put("awayClub",aux.get(i).getAwayTeam());
                    contentValues.put("Coordinates",aux.get(i).getCoordinates());
                    contentValues.put("stadium",aux.get(i).getStadium());
                    oSQLiteDB.insert("Game",null,contentValues);
                }
                sp.edit().putString("date",String.valueOf(System.currentTimeMillis())).apply();
                populateRV(team);
            }

            @Override
            public void onFailure(Call<GameAPI> call, Throwable t) {
                Toast.makeText(Main.this, "Ocorreu um Erro\nVerifique a ligação à Internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    //fetch Stadiums from API
    public void handleStadiums(String team){
        mService=Common.getAPI();
        mService.getStadiums(team).enqueue(new Callback<StadiumAPI>() {
            @Override
            public void onResponse(Call<StadiumAPI> call, Response<StadiumAPI> response) {
                StadiumAPI result=response.body();
                ArrayList<Stadium> stadiums = result.getStadium();

                for (int i=0; i < stadiums.size();i++){
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("idStadium",i);
                    contentValues.put("sName",stadiums.get(i).getsName());
                    contentValues.put("sClub",stadiums.get(i).getsClub());
                    contentValues.put("sAdress",stadiums.get(i).getsAdress());
                    contentValues.put("sYear",stadiums.get(i).getsYear());
                    contentValues.put("sCapacity",stadiums.get(i).getsCapacity());
                    contentValues.put("sCoordinates", stadiums.get(i).getsCoordinates());
                    contentValues.put("sHistory",stadiums.get(i).getsHistory());
                    oSQLiteDB.insert("Stadium",null,contentValues);
                }

            }

            @Override
            public void onFailure(Call<StadiumAPI> call, Throwable t) {
                Toast.makeText(Main.this, "Ocorreu um Erro\nVerifique a ligação à Internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void populateRV(String team){
        Cursor oCursor = oSQLiteDB.rawQuery("SELECT * FROM GAME WHERE homeClub= ? OR awayClub= ?",new String[]{team,team} );
        boolean bCarryOn = oCursor.moveToFirst();

        while (bCarryOn) {
            games.add(new Game(oCursor.getString(0),oCursor.getString(1),oCursor.getString(2),oCursor.getString(3),oCursor.getString(4),oCursor.getString(5),oCursor.getString(6),oCursor.getInt(7)));
            bCarryOn = oCursor.moveToNext();
        }
        if(oCursor!=null && oCursor.getCount()>0){
            String home=games.get(0).getHomeTeam();
            club1name.setText(home);
            String away=games.get(0).getAwayTeam();
            club2name.setText(away);
            handleHomeTeam(home);
            handleAwayTeam(away);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.forLanguageTag("pt-PT"));
            Date date = null;
            try {
                date = format.parse(games.get(0).getTimeanddate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String day = (String) DateFormat.format("dd",   date);
            String monthString  = (String) DateFormat.format("MMM",  date);
            String monthINportuguese=handleMonth(monthString);// Jun
            String hours = (String) DateFormat.format("HH",date);
            String minutes = (String) DateFormat.format("mm", date);
            gametimeanddate.setText(day+" "+monthINportuguese+"\n"+hours+":"+minutes);
            coordinates=games.get(0).getCoordinates();
            stadium=games.get(0).getStadium();

            games.remove(0); //to remove first game since it goes to layout and not recycler view

            mAdapter.updateGames(games);

            if(!(dialog==null))
                if (dialog.isShowing())
                    dialog.dismiss();


        }

    }

    //to put months in portuguese
    private String handleMonth(String monthString) {
        if(monthString.equals("Jan"))
            return "Jan";
        else if (monthString.equals("Feb"))
            return "Fev";
        else if (monthString.equals("Mar"))
            return "Mar";
        else if (monthString.equals("Apr"))
            return "Abr";
        else if (monthString.equals("May"))
            return "Mai";
        else if (monthString.equals("Jun"))
            return "Jun";
        else if (monthString.equals("Jul"))
            return "Jul";
        else if (monthString.equals("Aug"))
            return "Ago";
        else if (monthString.equals("Sep"))
            return "Set";
        else if (monthString.equals("Oct"))
            return "Out";
        else if (monthString.equals("Nov"))
            return "Nov";
        else if (monthString.equals("Dec"))
            return "Dez";
        return monthString;
    }

}
