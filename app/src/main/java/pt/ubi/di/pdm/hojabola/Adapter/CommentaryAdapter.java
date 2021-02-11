package pt.ubi.di.pdm.hojabola.Adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pt.ubi.di.pdm.hojabola.Model.Commentary;
import pt.ubi.di.pdm.hojabola.R;

public class CommentaryAdapter extends RecyclerView.Adapter<CommentaryAdapter.ViewHolder> {
    private ArrayList<Commentary> lCommentary;

    public CommentaryAdapter(ArrayList<Commentary> lCommentary) {
        this.lCommentary = lCommentary;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView commentaryDate;
        public final TextView comment;
        public final TextView commentaryASPOTS;

        public ViewHolder (View view){
            super(view);
            commentaryDate=view.findViewById(R.id.commentaryDate);
            comment=view.findViewById(R.id.commentaryRV);
            commentaryASPOTS=view.findViewById(R.id.commentaryASpots);
        }
    }
    @NonNull
    @Override
    public CommentaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commentary,parent,false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentaryAdapter.ViewHolder holder, int position) {
        Commentary currentCommentary=lCommentary.get(position);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("pt-PT"));
        Date date = null;
        try {
            date = format.parse(currentCommentary.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = (String) DateFormat.format("dd",   date);
        String month  = (String) DateFormat.format("MMM",  date); // Jun
        String monthINportuguese=handleMonth(month);
        String hours = (String) DateFormat.format("HH",date);
        String minutes = (String) DateFormat.format("mm", date);

        holder.commentaryDate.setText(day+"-"+monthINportuguese+" "+hours+":"+minutes);
        holder.comment.setText(currentCommentary.getComment());
        holder.commentaryASPOTS.setText("Lugares Disponíveis: "+ String.valueOf(currentCommentary.getaSpots()));
    }

    @Override
    public int getItemCount() {
        return lCommentary.size();
    }

    public void updateComments(ArrayList<Commentary> lCommentary){
        this.lCommentary=lCommentary;
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
