package com.infinitid.veterinaria_rp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinitid.veterinaria_rp.R;
import com.infinitid.veterinaria_rp.pojo.Perfil;

import java.util.ArrayList;

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {
    public static class PerfilViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvLike;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvLike = (TextView) itemView.findViewById(R.id.tvlikeCV);
        }
    }

    public ArrayList<Perfil> fotos;

    public PerfilAdaptador(ArrayList<Perfil> fotos) {
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public PerfilAdaptador.PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false); //asocia layout con RecyclerView
        PerfilAdaptador.PerfilViewHolder perfilViewHolder = new PerfilAdaptador.PerfilViewHolder(v);
        return perfilViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilAdaptador.PerfilViewHolder perfilViewHolder, int position) {
        perfilViewHolder.tvLike.setText(String.valueOf(fotos.get(position).getLike()));
        perfilViewHolder.imgFoto.setImageResource(fotos.get(position).getFotoPerfil());
    }

    @Override
    public int getItemCount() {

        return fotos.size();
    }

}
