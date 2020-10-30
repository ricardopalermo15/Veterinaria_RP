package com.infinitid.veterinaria_rp.db;

import android.content.ContentValues;
import android.content.Context;

import com.infinitid.veterinaria_rp.R;
import com.infinitid.veterinaria_rp.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {

    private Context context;
    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerTodosLasMascotas();
    }

    public ArrayList<Mascota> obtenerFavoritos() {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerFavoritos();
    }
    public void insertarMascotas(BaseDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.bulldog);
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Iara");
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Marimar");
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.cocker);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Guada");
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.cocker);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Keeper");
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.dalmata);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Perita");
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.chihuahua);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Grandote");
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.grandanes);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Laiza");
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.labrador);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLA_MASCOTA_NOMBRE, "Laika");
        contentValues.put(ConstantesDB.TABLA_MASCOTA_FOTO, R.drawable.siberiano);
        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);

        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesDB.TABLA_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesDB.TABLA_LIKES_MASCOTA_NUMERO_LIKES, 1);

        db.insertarLikeMascota(contentValues);

        contentValues = new ContentValues();

        contentValues.put(ConstantesDB.TABLA_FAVORITOS_ID_MASCOTA, mascota.getId());

        db.insertarFavorito(contentValues);
    }


    public int obtenerLikesMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }
}
