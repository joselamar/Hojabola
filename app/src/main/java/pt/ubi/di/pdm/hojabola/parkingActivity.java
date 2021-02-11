package pt.ubi.di.pdm.hojabola;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.ubi.di.pdm.hojabola.Adapter.ParkingAdapter;
import pt.ubi.di.pdm.hojabola.Model.Parking;
import pt.ubi.di.pdm.hojabola.Model.ParkingAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class parkingActivity extends AppCompatActivity {
    SharedPreferences sp;
    private Toolbar toolbar;
    private TextView addParking;
    api4app mService;
    private RecyclerView rvParking;
    private RecyclerView.LayoutManager mLayoutManager;
    private ParkingAdapter mAdapter;
    ArrayList<Parking> parkings=new ArrayList<>();
    private ProgressDialog dialog;
    int stadium;
    String coordinates,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp=getSharedPreferences("login",MODE_PRIVATE);
        String team=sp.getString("club","LoginTheme");
        handleTheme(team);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        mService= Common.getAPI();

        //intens for not losing the connection between gameOptios Parking and comments
        stadium=getIntent().getIntExtra("stadium",0);
        coordinates=getIntent().getStringExtra("coordinates");
        title=getIntent().getStringExtra("title");

        toolbar = (Toolbar)findViewById(R.id.my_toolbarP);
        rvParking=(RecyclerView) findViewById(R.id.rvParking);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Estacionamentos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addParking= findViewById(R.id.addParking);

        addParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iActivity = new Intent(getApplicationContext(),addParking.class);
                iActivity.putExtra("stadium",stadium);
                iActivity.putExtra("coordinates",coordinates);
                iActivity.putExtra("title", title);
                startActivity(iActivity);
                finish();
            }
        });

        mLayoutManager= new LinearLayoutManager(this);
        mAdapter = new ParkingAdapter(parkings);
        rvParking.setLayoutManager(mLayoutManager);
        rvParking.setAdapter(mAdapter);

        dialog = new ProgressDialog(parkingActivity.this);
        dialog.setMessage("A carregar os Estacionamentos...");
        dialog.setCancelable(false);
        dialog.show();
        mService.getParkings(stadium).enqueue(new Callback<ParkingAPI>() {
            @Override
            public void onResponse(Call<ParkingAPI> call, Response<ParkingAPI> response) {
               ParkingAPI result=response.body();
                ArrayList<Parking> aux= result.getParking();
                for(int i=0;i<aux.size();i++){
                    Parking p=new Parking(aux.get(i).getIdParking(),aux.get(i).getpName(),aux.get(i).getpAdress(),aux.get(i).getpSpots(),aux.get(i).getpAvailable(),aux.get(i).getpCoordinates(),aux.get(i).getIsPayed(),aux.get(i).getImage());
                    parkings.add(p);
                }


                mAdapter.updateParkings(parkings);

                if(!(dialog==null))
                    if (dialog.isShowing())
                        dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ParkingAPI> call, Throwable t) {
                if(!(dialog==null))
                    if (dialog.isShowing())
                        dialog.dismiss();
                Toast.makeText(parkingActivity.this, "Ocorreu um erro\nVerifique a sua coneção à Internet", Toast.LENGTH_LONG).show();
            }
        });

        mAdapter.setOnItemClickListener(new ParkingAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                finish();
                Intent iActivity = new Intent(getApplication(),parkingComentary.class);
                iActivity.putExtra("stadium",stadium);
                iActivity.putExtra("idParking",parkings.get(position).getIdParking());
                iActivity.putExtra("coordinates",coordinates);
                iActivity.putExtra("title", title);
                startActivity(iActivity);
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
        Intent iActivity = new Intent(this,gameOptions.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
        return true;
    }

    public void onBackPressed() {
        finish();
        Intent iActivity = new Intent(this,gameOptions.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
    }

}
