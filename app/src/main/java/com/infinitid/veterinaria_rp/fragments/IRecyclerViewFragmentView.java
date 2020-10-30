package com.infinitid.veterinaria_rp.fragments;

import com.infinitid.veterinaria_rp.adaptador.MascotaAdaptador;
import com.infinitid.veterinaria_rp.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> contactos);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
