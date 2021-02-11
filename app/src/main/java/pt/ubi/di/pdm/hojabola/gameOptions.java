package pt.ubi.di.pdm.hojabola;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;


public class gameOptions extends AppCompatActivity implements OnMapReadyCallback {

    private Toolbar toolbar;
    private GoogleMap mMap;
    SharedPreferences sp;
    String [] aux;
    private Button gotogame,park,foodplaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp=getSharedPreferences("login",MODE_PRIVATE);
        String team=sp.getString("club","LoginTheme");
        handleTheme(team);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);

        final int stadium=getIntent().getIntExtra("stadium",0);
        final String coordinates=getIntent().getStringExtra("coordinates");
        aux=coordinates.split(",");
        final String title=getIntent().getStringExtra("title");

        gotogame= findViewById(R.id.gotogame);
        park= findViewById(R.id.park);
        foodplaces=findViewById(R.id.foodplaces);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        handleTitleColor(team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        gotogame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%s,%s", aux[0],aux[1]);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                try
                {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        Toast.makeText(getApplicationContext(),"Instale a aplicação Mapas",Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iActivity = new Intent(getApplicationContext(),parkingActivity.class);
                iActivity.putExtra("stadium",stadium);
                iActivity.putExtra("coordinates",coordinates);
                iActivity.putExtra("title", title);
                startActivity(iActivity);
                finish();
            }
        });

        foodplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iActivity = new Intent(getApplicationContext(),foodplacesActivity.class);
                iActivity.putExtra("stadium",stadium);
                iActivity.putExtra("coordinates",coordinates);
                iActivity.putExtra("title", title);
                startActivity(iActivity);
                finish();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in stadium and move the camera
        LatLng stadium = new LatLng(Double.valueOf(aux[0]),Double.valueOf(aux[1]));
        mMap.addMarker(new MarkerOptions().position(stadium).title("Estádio"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stadium,15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 15, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


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

    public void handleTitleColor(String team){
        if (team.equals("Aves")){
            toolbar.setTitleTextColor(R.style.aves);
        } else if (team.equals("Belenenses")){
            toolbar.setTitleTextColor(Color.WHITE);
        } else if (team.equals("Benfica")){
            toolbar.setTitleTextColor(R.style.benfica);
        } else if (team.equals("Boavista")){
            toolbar.setTitleTextColor(R.style.boavista);
        } else if (team.equals("Braga")){
            toolbar.setTitleTextColor(R.color.braga);
        } else if (team.equals("Famalicao")){
            toolbar.setTitleTextColor(R.style.famalicao);
        } else if (team.equals("Porto")){
            toolbar.setTitleTextColor(R.style.porto);
        } else if (team.equals("Feirense")){
            toolbar.setTitleTextColor(R.style.ferreira);
        } else if (team.equals("Marítimo")){
            toolbar.setTitleTextColor(R.style.maritimo);
        } else if (team.equals("Moreirense")){
            toolbar.setTitleTextColor(R.style.moreirense);
        } else if (team.equals("Gil Vicente")){
            toolbar.setTitleTextColor(R.style.gilvicente);
        } else if (team.equals("Portimonense")){
            toolbar.setTitleTextColor(R.style.portimonense);
        } else if (team.equals("Rio Ave")){
            toolbar.setTitleTextColor(R.style.rioave);
        } else if (team.equals("Santa Clara")){
            toolbar.setTitleTextColor(R.style.santaclara);
        } else if (team.equals("Sporting")){
            toolbar.setTitleTextColor(R.style.sporting);
        } else if (team.equals("Tondela")){
            toolbar.setTitleTextColor(R.style.tondela);
        }  else if (team.equals("Setubal")){
            toolbar.setTitleTextColor(R.style.setubal);
        }  else if (team.equals("Guimaraes")){
            toolbar.setTitleTextColor(R.style.guimaraes);
        }
    }

}
