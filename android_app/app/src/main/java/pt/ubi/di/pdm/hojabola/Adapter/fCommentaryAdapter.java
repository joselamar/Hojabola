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
import pt.ubi.di.pdm.hojabola.Model.fCommentary;
import pt.ubi.di.pdm.hojabola.R;

public class fCommentaryAdapter extends RecyclerView.Adapter<fCommentaryAdapter.ViewHolder> {
    private ArrayList<fCommentary> lfCommentary;

    public fCommentaryAdapter(ArrayList<fCommentary> lfCommentary) {
        this.lfCommentary = lfCommentary;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView fCommentaryDate;
        public final TextView comment;
        public final TextView fCommentaryClassification;

        public ViewHolder (View view){
            super(view);
            fCommentaryDate=view.findViewById(R.id.fCommentaryDate);
            comment=view.findViewById(R.id.fCommentaryRV);
            fCommentaryClassification=view.findViewById(R.id.fCommentaryClassification);
        }
    }
    @NonNull
    @Override
    public fCommentaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fcommentary,parent,false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull fCommentaryAdapter.ViewHolder holder, int position) {
        fCommentary currentfCommentary=lfCommentary.get(position);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("pt-PT"));
        Date date = null;
        try {
            date = format.parse(currentfCommentary.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = (String) DateFormat.format("dd",   date);
        String month  = (String) DateFormat.format("MMM",  date); // Jun
        String monthINportuguese=handleMonth(month);
        String hours = (String) DateFormat.format("HH",date);
        String minutes = (String) DateFormat.format("mm", date);

        holder.fCommentaryDate.setText(day+"-"+monthINportuguese+" "+hours+":"+minutes);
        holder.comment.setText(currentfCommentary.getComment());
        holder.fCommentaryClassification.setText("Classificação: "+ String.valueOf(currentfCommentary.getClassification()));
    }

    @Override
    public int getItemCount() {
        return lfCommentary.size();
    }

    public void updateComments(ArrayList<fCommentary> lfCommentary){
        this.lfCommentary=lfCommentary;
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
