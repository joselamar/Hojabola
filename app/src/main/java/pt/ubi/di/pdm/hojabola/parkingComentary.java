package pt.ubi.di.pdm.hojabola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.ubi.di.pdm.hojabola.Adapter.CommentaryAdapter;
import pt.ubi.di.pdm.hojabola.Model.Commentary;
import pt.ubi.di.pdm.hojabola.Model.CommentaryAPI;
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

public class parkingComentary extends AppCompatActivity {
    SharedPreferences sp;
    private Toolbar toolbar;
    private TextView addCommentary;
    int stadium,idParking;
    private RecyclerView rvParkingCommentary;
    private RecyclerView.LayoutManager mLayoutManager;
    private CommentaryAdapter mAdapter;
    ArrayList<Commentary> commentaries=new ArrayList<>();
    private ProgressDialog dialog;
    private api4app mService;
    String coordinates,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp=getSharedPreferences("login",MODE_PRIVATE);
        String team=sp.getString("club","LoginTheme");
        handleTheme(team);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_comentary);

        stadium=getIntent().getIntExtra("stadium",0);
        idParking=getIntent().getIntExtra("idParking",0);
        coordinates=getIntent().getStringExtra("coordinates");
        title=getIntent().getStringExtra("title");

        rvParkingCommentary=findViewById(R.id.rvParkingCommentary);
        mService= Common.getAPI();

        toolbar = (Toolbar)findViewById(R.id.my_toolbarCP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comentários");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addCommentary= findViewById(R.id.addParkingCommentary);

        addCommentary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iActivity = new Intent(getApplicationContext(),addCommentary.class);
                iActivity.putExtra("idParking",idParking);
                iActivity.putExtra("stadium",stadium);
                iActivity.putExtra("coordinates",coordinates);
                iActivity.putExtra("title", title);
                startActivity(iActivity);
                finish();
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter= new CommentaryAdapter(commentaries);
        rvParkingCommentary.setLayoutManager(mLayoutManager);
        rvParkingCommentary.setAdapter(mAdapter);

        dialog = new ProgressDialog(parkingComentary.this);
        dialog.setMessage("A carregar os comentários...");
        dialog.setCancelable(false);
        dialog.show();

        mService.getCommentary(idParking).enqueue(new Callback<CommentaryAPI>() {
            @Override
            public void onResponse(Call<CommentaryAPI> call, Response<CommentaryAPI> response) {
                CommentaryAPI result=response.body();
                ArrayList<Commentary> aux= result.getCommentary();

                for(int i=0;i<aux.size();i++) {
                Commentary c=new Commentary(aux.get(i).getDate(),aux.get(i).getComment(),aux.get(i).getaSpots());
                commentaries.add(c);
                }
                mAdapter.updateComments(commentaries);

                if(!(dialog==null))
                    if (dialog.isShowing())
                        dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommentaryAPI> call, Throwable t) {
                if(!(dialog==null))
                    if (dialog.isShowing())
                        dialog.dismiss();
                Toast.makeText(parkingComentary.this, "Ocorreu um erro\nVerifique a sua coneção à Internet", Toast.LENGTH_LONG).show();
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
        Intent iActivity = new Intent(this,parkingActivity.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
        return true;
    }

    public void onBackPressed() {
        finish();
        Intent iActivity = new Intent(this,parkingActivity.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
    }

}
