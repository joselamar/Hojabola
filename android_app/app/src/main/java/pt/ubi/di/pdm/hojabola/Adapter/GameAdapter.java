package pt.ubi.di.pdm.hojabola.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pt.ubi.di.pdm.hojabola.Model.Game;
import pt.ubi.di.pdm.hojabola.R;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private ArrayList<Game> lGames;
    private onItemClickListener mListener;

    public GameAdapter() { }

    public GameAdapter(ArrayList<Game> Games){ lGames= Games; }


    public  interface  onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView timeanddate;
        public final TextView homecurrentGame;
        public final TextView awaycurrentGame;
        public final ImageView homeImage;
        public final ImageView awayImage;

        public ViewHolder (View view, final onItemClickListener listener){
            super(view);
            timeanddate=view.findViewById(R.id.timeAndDate);
            homecurrentGame=view.findViewById(R.id.homeName);
            awaycurrentGame=view.findViewById(R.id.awayName);
            homeImage=view.findViewById(R.id.home);
            awayImage=view.findViewById(R.id.away);

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_game,viewGroup,false);
        ViewHolder vh= new ViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Game currentGame= lGames.get(i);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("pt-PT"));
        Date date = null;
        try {
            date = format.parse(currentGame.getTimeanddate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = (String) DateFormat.format("dd",   date);
        String month  = (String) DateFormat.format("MMM",  date); // Jun
        String monthINportuguese=handleMonth(month);
        String hours = (String) DateFormat.format("HH",date);
        String minutes = (String) DateFormat.format("mm", date);


        viewHolder.timeanddate.setText(day+" "+monthINportuguese+"\n"+hours+":"+minutes);
        viewHolder.homecurrentGame.setText(currentGame.getHomeTeam());
        viewHolder.awaycurrentGame.setText(currentGame.getAwayTeam());
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

    public void updateGames(ArrayList<Game> games) {
        this.lGames = games;
        notifyDataSetChanged();
    }

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
