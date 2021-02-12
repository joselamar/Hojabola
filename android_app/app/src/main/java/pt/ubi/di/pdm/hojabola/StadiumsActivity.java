package pt.ubi.di.pdm.hojabola;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ubi.di.pdm.hojabola.Adapter.stadiumSliderAdapter;
import pt.ubi.di.pdm.hojabola.Model.Stadium;

public class StadiumsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private stadiumSliderAdapter stadiumSliderAdapter;
    public ArrayList<String> slideStadiumName = new ArrayList<>();
    public ArrayList<String> slideStadiumClub = new ArrayList<>();
    public ArrayList<String> slideStadiumCapacity = new ArrayList<>();
    public ArrayList<String> slideStadiumYear = new ArrayList<>();
    public ArrayList<String> slideStadiumHistory = new ArrayList<>();
    private database db;
    private SQLiteDatabase oSQLiteDB;
    private androidx.appcompat.widget.Toolbar toolbar;
    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String team = getIntent().getStringExtra("club");
        if (team==null) {
            team = sp.getString("club", "LoginTheme");
        }
        handleTheme(team); //to set the theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadiums);

        linearLayout= findViewById(R.id.ll);
        viewPager= findViewById(R.id.viewPager);

        toolbar= findViewById(R.id.my_toolbarS);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Estádios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int [] drawableImages={R.drawable.avesstadium,R.drawable.stadiumbelenenses,R.drawable.stadiumbenfica,R.drawable.stadiumboavista,R.drawable.stadiumbraga,R.drawable.stadiumfamalicao,R.drawable.stadiumferreira,R.drawable.stadiumgilvicente,R.drawable.stadiummaritimo,R.drawable.stadiummoreirense,R.drawable.stadiumportimonense,R.drawable.stadiumporto,R.drawable.stadiumrioave,R.drawable.stadiumsantaclara,R.drawable.stadiumsporting,R.drawable.stadiumtondela,R.drawable.stadiumsetubal,R.drawable.stadiumguimaraes};

        db = new database(this);
        oSQLiteDB = db.getWritableDatabase();

        ArrayList<Stadium> stadiums= new ArrayList<>();

        Cursor oCursor = oSQLiteDB.rawQuery("SELECT * FROM Stadium",new String[]{} );
        Boolean bCarryOn=oCursor.moveToFirst();
        while (bCarryOn){
            stadiums.add(new Stadium(oCursor.getString(1),oCursor.getString(2),oCursor.getString(3),oCursor.getString(4),oCursor.getString(5),oCursor.getString(6),oCursor.getString(7)));
            bCarryOn=oCursor.moveToNext();
        }
        for (int i=0;i<stadiums.size();i++){
            slideStadiumName.add(stadiums.get(i).getsName());
            slideStadiumClub.add(stadiums.get(i).getsClub());
            slideStadiumCapacity.add(stadiums.get(i).getsCapacity());
            slideStadiumYear.add(stadiums.get(i).getsYear());
            slideStadiumHistory.add(stadiums.get(i).getsHistory());
        }


        stadiumSliderAdapter = new stadiumSliderAdapter(StadiumsActivity.this,slideStadiumName,slideStadiumClub,slideStadiumCapacity,slideStadiumYear,slideStadiumHistory,drawableImages);
        viewPager.setAdapter(stadiumSliderAdapter);

        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(viewListener);

    }

    private void addDotsIndicator(int position) {
        mDots = new TextView[18];
        linearLayout.removeAllViews();

        for (int i=0;i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));

            linearLayout.addView(mDots[i]);
        }

        mDots[position].setTextColor(getResources().getColor(android.R.color.white));
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

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
