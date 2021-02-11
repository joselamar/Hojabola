package pt.ubi.di.pdm.hojabola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import pt.ubi.di.pdm.hojabola.Adapter.foodplacesSliderAdapter;
import pt.ubi.di.pdm.hojabola.Model.FoodPlaces;
import pt.ubi.di.pdm.hojabola.Model.FoodPlacesAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class foodplacesActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private foodplacesSliderAdapter foodplacesSliderAdapter;
    int stadium;
    String coordinates,title;
    api4app mService;
    private ProgressDialog dialog;
    private ArrayList<FoodPlaces> foodPlaces=new ArrayList<>();
    private TextView addCommentaryR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String team = getIntent().getStringExtra("club");
        if (team==null) {
            team = sp.getString("club", "LoginTheme");
        }
        handleTheme(team); //to set the theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodplaces);

        //intens for not losing the connection between gameOptios Parking and comments
        stadium=getIntent().getIntExtra("stadium",0);
        coordinates=getIntent().getStringExtra("coordinates");
        title=getIntent().getStringExtra("title");

        viewPager=(ViewPager)findViewById(R.id.viewPagerFP);
        toolbar=(Toolbar)findViewById(R.id.my_toolbarFP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Restaurantes e Bares");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mService= Common.getAPI();

        final int [] drawableImages={R.drawable.tabernarochapereira,R.drawable.churrasqueira};

        dialog = new ProgressDialog(foodplacesActivity.this);
        dialog.setMessage("A carregar os Restaurantes e Bares...");
        dialog.setCancelable(false);
        dialog.show();
        mService.getFoodPlaces(stadium).enqueue(new Callback<FoodPlacesAPI>() {
            @Override
            public void onResponse(Call<FoodPlacesAPI> call, Response<FoodPlacesAPI> response) {
                FoodPlacesAPI result = response.body();
                ArrayList<FoodPlaces> aux = result.getFoodPlaces();
                for(int i=0;i<aux.size();i++){
                    FoodPlaces p = new FoodPlaces(aux.get(i).getIdFoodPlace(),aux.get(i).getfName(),aux.get(i).getfClassification(),aux.get(i).getfCoordinates(),aux.get(i).getfDistance(),aux.get(i).getfCommentary());
                    foodPlaces.add(p);
                }

                    foodplacesSliderAdapter= new foodplacesSliderAdapter(foodplacesActivity.this,foodPlaces,drawableImages);
                    viewPager.setAdapter(foodplacesSliderAdapter);

                    if(!(dialog==null))
                        if (dialog.isShowing())
                            dialog.dismiss();

            }

            @Override
            public void onFailure(Call<FoodPlacesAPI> call, Throwable t) {
                if(!(dialog==null))
                    if (dialog.isShowing())
                        dialog.dismiss();
                Toast.makeText(foodplacesActivity.this, "Ocorreu um erro\nVerifique a sua coneção à Internet", Toast.LENGTH_LONG).show();
            }
        });

        if(foodPlaces.size()!=0){
            addCommentaryR=(TextView)findViewById(R.id.addCommentR);
            addCommentaryR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent iActivity = new Intent(getApplicationContext(),addCommentaryFoodPlace.class);
                    iActivity.putExtra("idFoodPlace",foodPlaces.get(viewPager.getCurrentItem()).getIdFoodPlace());
                    iActivity.putExtra("stadium",stadium);
                    iActivity.putExtra("coordinates",coordinates);
                    iActivity.putExtra("title", title);
                    startActivity(iActivity);
                }
            });

        }
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
