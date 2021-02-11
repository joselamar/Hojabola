package pt.ubi.di.pdm.hojabola.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ubi.di.pdm.hojabola.Model.Game;
import pt.ubi.di.pdm.hojabola.R;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private ArrayList<Game> lGames;
    private onItemClickListener mListener;

    public ResultAdapter() { }

    public ResultAdapter(ArrayList<Game> lGames) { this.lGames = lGames; }

    public  interface  onItemClickListener { void onItemClick(int position); }

    public void setOnItemClickListener(ResultAdapter.onItemClickListener listener){ mListener=listener; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView score;
        public final TextView homeName;
        public final TextView awayName;
        public final ImageView homeImage;
        public final ImageView awayImage;

        public ViewHolder (View view, final onItemClickListener listener){
            super(view);
            score=view.findViewById(R.id.score);
            homeName=view.findViewById(R.id.homeNameResult);
            awayName=view.findViewById(R.id.awayNameResult);
            homeImage=view.findViewById(R.id.homeResultImage);
            awayImage=view.findViewById(R.id.awayResultImage);

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_result,viewGroup,false);
        ViewHolder vh= new ViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Game currentGame=lGames.get(i);

        viewHolder.homeName.setText(currentGame.getHomeTeam());
        viewHolder.awayName.setText(currentGame.getAwayTeam());
        viewHolder.score.setText(currentGame.getScore());
        if (currentGame.getHomeTeam().equals("Aves")){
            viewHolder.homeImage.setImageResource(R.drawable.aveslogo);
        } else if (currentGame.getHomeTeam().equals("Belenenses")){
            viewHolder.homeImage.setImageResource(R.drawable.belenenseslogo);
        } else if (currentGame.getHomeTeam().equals("Benfica")){
            viewHolder.homeImage.setImageResource(R.drawable.benficalogo);
        } else if (currentGame.getHomeTeam().equals("Boavista")){
            viewHolder.homeImage.setImageResource(R.drawable.boavistalogo);
        } else if (currentGame.getHomeTeam().equals("Braga")){
            viewHolder.homeImage.setImageResource(R.drawable.bragalogo);
        } else if (currentGame.getHomeTeam().equals("Famalicao")){
            viewHolder.homeImage.setImageResource(R.drawable.famalicaologo);
        } else if (currentGame.getHomeTeam().equals("Ferreira")){
            viewHolder.homeImage.setImageResource(R.drawable.pacosferreiralogo);
        } else if (currentGame.getHomeTeam().equals("Gil Vicente")){
            viewHolder.homeImage.setImageResource(R.drawable.gilvicentelogo);
        } else if (currentGame.getHomeTeam().equals("Maritimo")){
            viewHolder.homeImage.setImageResource(R.drawable.maritimologo);
        } else if (currentGame.getHomeTeam().equals("Moreirense")){
            viewHolder.homeImage.setImageResource(R.drawable.moreirenselogo);
        } else if (currentGame.getHomeTeam().equals("Portimonense")){
            viewHolder.homeImage.setImageResource(R.drawable.portimonenselogo);
        } else if (currentGame.getHomeTeam().equals("Porto")){
            viewHolder.homeImage.setImageResource(R.drawable.portologo);
        } else if (currentGame.getHomeTeam().equals("Rio Ave")){
            viewHolder.homeImage.setImageResource(R.drawable.rioavelogo);
        } else if (currentGame.getHomeTeam().equals("Santa Clara")){
            viewHolder.homeImage.setImageResource(R.drawable.santaclaralogo);
        } else if (currentGame.getHomeTeam().equals("Sporting")){
            viewHolder.homeImage.setImageResource(R.drawable.sportinglogo);
        } else if (currentGame.getHomeTeam().equals("Tondela")){
            viewHolder.homeImage.setImageResource(R.drawable.tondelalogo);
        }  else if (currentGame.getHomeTeam().equals("Setubal")){
            viewHolder.homeImage.setImageResource(R.drawable.setuballogo);
        }  else if (currentGame.getHomeTeam().equals("Guimaraes")){
            viewHolder.homeImage.setImageResource(R.drawable.guimaraeslogo);
        }
        if (currentGame.getAwayTeam().equals("Aves")){
            viewHolder.awayImage.setImageResource(R.drawable.aveslogo);
        } else if (currentGame.getAwayTeam().equals("Belenenses")){
            viewHolder.awayImage.setImageResource(R.drawable.belenenseslogo);
        } else if (currentGame.getAwayTeam().equals("Benfica")){
            viewHolder.awayImage.setImageResource(R.drawable.benficalogo);
        } else if (currentGame.getAwayTeam().equals("Boavista")){
            viewHolder.awayImage.setImageResource(R.drawable.boavistalogo);
        } else if (currentGame.getAwayTeam().equals("Braga")){
            viewHolder.awayImage.setImageResource(R.drawable.bragalogo);
        } else if (currentGame.getAwayTeam().equals("Famalicao")){
            viewHolder.awayImage.setImageResource(R.drawable.famalicaologo);
        } else if (currentGame.getAwayTeam().equals("Ferreira")){
            viewHolder.awayImage.setImageResource(R.drawable.pacosferreiralogo);
        } else if (currentGame.getAwayTeam().equals("Gil Vicente")){
            viewHolder.awayImage.setImageResource(R.drawable.gilvicentelogo);
        }  else if (currentGame.getAwayTeam().equals("Maritimo")){
            viewHolder.awayImage.setImageResource(R.drawable.maritimologo);
        } else if (currentGame.getAwayTeam().equals("Moreirense")){
            viewHolder.awayImage.setImageResource(R.drawable.moreirenselogo);
        } else if (currentGame.getAwayTeam().equals("Portimonense")){
            viewHolder.awayImage.setImageResource(R.drawable.portimonenselogo);
        } else if (currentGame.getAwayTeam().equals("Porto")){
            viewHolder.awayImage.setImageResource(R.drawable.portologo);
        } else if (currentGame.getAwayTeam().equals("Rio Ave")){
            viewHolder.awayImage.setImageResource(R.drawable.rioavelogo);
        } else if (currentGame.getAwayTeam().equals("Santa Clara")){
            viewHolder.awayImage.setImageResource(R.drawable.santaclaralogo);
        } else if (currentGame.getAwayTeam().equals("Sporting")){
            viewHolder.awayImage.setImageResource(R.drawable.sportinglogo);
        } else if (currentGame.getAwayTeam().equals("Tondela")){
            viewHolder.awayImage.setImageResource(R.drawable.tondelalogo);
        }  else if (currentGame.getAwayTeam().equals("Setubal")){
            viewHolder.awayImage.setImageResource(R.drawable.setuballogo);
        }  else if (currentGame.getAwayTeam().equals("Guimaraes")){
            viewHolder.awayImage.setImageResource(R.drawable.guimaraeslogo);
        }

    }

    @Override
    public int getItemCount() { return lGames.size(); }

    public void updateResults(ArrayList<Game> games) {
        this.lGames = games;
        notifyDataSetChanged();
    }
}
