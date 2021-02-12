package pt.ubi.di.pdm.hojabola.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pt.ubi.di.pdm.hojabola.Model.Parking;
import pt.ubi.di.pdm.hojabola.R;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ViewHolder> {
    private ArrayList<Parking> parkings;
    private onItemClickListener mListener;

    public ParkingAdapter(ArrayList<Parking> parkings) {
        this.parkings = parkings;
    }

    public  interface  onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView image;
        public final TextView parkingTitle;
        public final TextView availableSpots;
        public final TextView parkingCoordinate;
        public final TextView isPayed;

        public ViewHolder (View view, final onItemClickListener listener){
            super(view);
            image=view.findViewById(R.id.image);
            parkingTitle=view.findViewById(R.id.parkingTitle);
            availableSpots=view.findViewById(R.id.availableSpots);
            parkingCoordinate=view.findViewById(R.id.parkingCoordinates);
            isPayed=view.findViewById(R.id.isPayed);

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parking,parent,false);
        ViewHolder vh=new ViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Parking currentParking=parkings.get(position);

        holder.parkingTitle.setText(currentParking.getpName());
        holder.availableSpots.setText("Lugares Disponíveis:"+String.valueOf(currentParking.getpAvailable()));
        String coordenadas=currentParking.getpCoordinates();
        if(coordenadas.equals("NULL"))
            holder.parkingCoordinate.setText(currentParking.getpAdress());
        else holder.parkingCoordinate.setText(currentParking.getpCoordinates());
        int gratis=currentParking.getIsPayed();
        if(gratis == 0)
            holder.isPayed.setText("Grátis");
        else holder.isPayed.setText("Pago");
        String encodedString=currentParking.getImage();
        if(encodedString.equals("NULL")){
            holder.image.setBackgroundResource(R.drawable.no_image);
        }else {
            byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.image.setImageBitmap(decodedByte);
        }
    }

    @Override
    public int getItemCount() {
        return parkings.size();
    }

    public void updateParkings(ArrayList<Parking> parkings){
        this.parkings=parkings;
        notifyDataSetChanged();
    }

}
