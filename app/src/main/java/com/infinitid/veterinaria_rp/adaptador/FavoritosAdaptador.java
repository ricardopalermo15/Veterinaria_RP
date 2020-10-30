package com.infinitid.veterinaria_rp.adaptador;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.infinitid.veterinaria_rp.R;
import com.infinitid.veterinaria_rp.pojo.Mascota;

import java.util.ArrayList;

public class FavoritosAdaptador extends RecyclerView.Adapter<FavoritosAdaptador.FavoritosViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public  FavoritosAdaptador(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
     }

    @Override
    public FavoritosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false); //asocia layout con RecyclerView
        return new FavoritosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavoritosAdaptador.FavoritosViewHolder favoritosViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        favoritosViewHolder.imgFoto.setImageResource(mascota.getFoto());
        favoritosViewHolder.tvNombre.setText(mascota.getNombre());
        favoritosViewHolder.tvLike.setText(String.valueOf(mascota.getLikes()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class FavoritosViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvLike;

        public FavoritosViewHolder(View itemView) {
            super(itemView);
            imgFoto  = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvLike   = (TextView) itemView.findViewById(R.id.tvlikeCV);
        }
    }
}
