package pt.ubi.di.pdm.hojabola.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pt.ubi.di.pdm.hojabola.Model.Standings;
import pt.ubi.di.pdm.hojabola.R;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.ViewHolder> {
    private ArrayList<Standings> standings;
    private onItemClickListener mListener;

    public StandingsAdapter(ArrayList<Standings> standings) { this.standings = standings; }

    public  interface  onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(StandingsAdapter.onItemClickListener listener){
        mListener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView StandingImage;
        public final TextView position;
        public final TextView standinClubName;
        public final TextView standingGamesPlayed;
        public final TextView standingGoals;
        public final TextView standingPoints;

        public ViewHolder (View view, final StandingsAdapter.onItemClickListener listener){
            super(view);
            StandingImage=view.findViewById(R.id.standingImage);
            position=view.findViewById(R.id.position);
            standinClubName=view.findViewById(R.id.standingClubName);
            standingGamesPlayed=view.findViewById(R.id.standingGamesPlayed);
            standingGoals=view.findViewById(R.id.standingGoals);
            standingPoints=view.findViewById(R.id.standingPoints);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position=getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public StandingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_standing,parent,false);
        StandingsAdapter.ViewHolder vh=new StandingsAdapter.ViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsAdapter.ViewHolder holder, int position) {
        Standings currentStanding=standings.get(position);

        holder.position.setText(currentStanding.getPosition()+".");
        holder.standinClubName.setText(currentStanding.getClubName());
        holder.standingGamesPlayed.setText(currentStanding.getGamesPlayed());
        holder.standingGoals.setText(currentStanding.getGoals());
        holder.standingPoints.setText(currentStanding.getPoints());
        if (currentStanding.getClubName().equals("Aves")){
            holder.StandingImage.setImageResource(R.drawable.aveslogo);
        } else if (currentStanding.getClubName().equals("Belenenses")){
            holder.StandingImage.setImageResource(R.drawable.belenenseslogo);
        } else if (currentStanding.getClubName().equals("Benfica")){
            holder.StandingImage.setImageResource(R.drawable.benficalogo);
        } else if (currentStanding.getClubName().equals("Boavista")){
            holder.StandingImage.setImageResource(R.drawable.boavistalogo);
        } else if (currentStanding.getClubName().equals("Braga")){
            holder.StandingImage.setImageResource(R.drawable.bragalogo);
        } else if (currentStanding.getClubName().equals("Famalicao")){
            holder.StandingImage.setImageResource(R.drawable.famalicaologo);
        } else if (currentStanding.getClubName().equals("Ferreira")){
            holder.StandingImage.setImageResource(R.drawable.pacosferreiralogo);
        } else if (currentStanding.getClubName().equals("Gil Vicente")){
            holder.StandingImage.setImageResource(R.drawable.gilvicentelogo);
        } else if (currentStanding.getClubName().equals("Maritimo")){
            holder.StandingImage.setImageResource(R.drawable.maritimologo);
        } else if (currentStanding.getClubName().equals("Moreirense")){
            holder.StandingImage.setImageResource(R.drawable.moreirenselogo);
        } else if (currentStanding.getClubName().equals("Portimonense")){
            holder.StandingImage.setImageResource(R.drawable.portimonenselogo);
        } else if (currentStanding.getClubName().equals("Porto")){
            holder.StandingImage.setImageResource(R.drawable.portologo);
        } else if (currentStanding.getClubName().equals("Rio Ave")){
            holder.StandingImage.setImageResource(R.drawable.rioavelogo);
        } else if (currentStanding.getClubName().equals("Santa Clara")){
            holder.StandingImage.setImageResource(R.drawable.santaclaralogo);
        } else if (currentStanding.getClubName().equals("Sporting")){
            holder.StandingImage.setImageResource(R.drawable.sportinglogo);
        } else if (currentStanding.getClubName().equals("Tondela")){
            holder.StandingImage.setImageResource(R.drawable.tondelalogo);
        }  else if (currentStanding.getClubName().equals("Setubal")){
            holder.StandingImage.setImageResource(R.drawable.setuballogo);
        }  else if (currentStanding.getClubName().equals("Guimaraes")){
            holder.StandingImage.setImageResource(R.drawable.guimaraeslogo);
        }
    }

    @Override
    public int getItemCount() {
        return standings.size();
    }

    public void updateStandings(ArrayList<Standings> standings){
        this.standings=standings;
        notifyDataSetChanged();
    }
}
