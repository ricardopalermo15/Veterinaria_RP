package com.infinitid.veterinaria_rp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.infinitid.veterinaria_rp.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesDB.DATABASE_NAME, null, ConstantesDB.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascotas = "CREATE TABLE " + ConstantesDB.TABLA_MASCOTA + "(" +
                ConstantesDB.TABLA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLA_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesDB.TABLA_MASCOTA_FOTO + " INTEGEER)";

        String queryCrearTablaLikeMascotas = "CREATE TABLE " + ConstantesDB.TABLA_LIKES_MASCOTA + "(" +
                ConstantesDB.TABLA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLA_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesDB.TABLA_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER," +
                "FOREIGN KEY (" + ConstantesDB.TABLA_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesDB.TABLA_MASCOTA + "(" + ConstantesDB.TABLA_MASCOTA_ID +
                "))";

        String queryCrearTablaFavoritos = "CREATE TABLE " + ConstantesDB.TABLA_FAVORITOS + "(" +
                ConstantesDB.TABLA_FAVORITOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLA_FAVORITOS_ID_MASCOTA + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesDB.TABLA_FAVORITOS_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesDB.TABLA_MASCOTA + "(" + ConstantesDB.TABLA_MASCOTA_ID +
                "))";

        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaLikeMascotas);
        db.execSQL(queryCrearTablaFavoritos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_LIKES_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_FAVORITOS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodosLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesDB.TABLA_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros =  db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesDB.TABLA_LIKES_MASCOTA_NUMERO_LIKES + ") as Likes FROM " +
                    ConstantesDB.TABLA_LIKES_MASCOTA + " WHERE " +
                    ConstantesDB.TABLA_LIKES_MASCOTA_ID_MASCOTA + " = " + mascotaActual.getId();

            Cursor registrosLikes =  db.rawQuery(queryLikes, null);

            if (registrosLikes.moveToNext()) {
                mascotaActual.setLikes(registrosLikes.getInt(0));
            } else {
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);

        }

        db.close();

        return mascotas;
    }


    public ArrayList<Mascota> obtenerFavoritos() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT " + ConstantesDB.TABLA_MASCOTA + ".* FROM " +
                ConstantesDB.TABLA_FAVORITOS + " INNER JOIN " + ConstantesDB.TABLA_MASCOTA +
                " ON " + ConstantesDB.TABLA_FAVORITOS + "." + ConstantesDB.TABLA_FAVORITOS_ID_MASCOTA +
                " = " + ConstantesDB.TABLA_MASCOTA + "." + ConstantesDB.TABLA_MASCOTA_ID +
                " ORDER BY " + ConstantesDB.TABLA_FAVORITOS + "." + ConstantesDB.TABLA_FAVORITOS_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros =  db.rawQuery(query, null);

        int cant = 1;
        while (registros.moveToNext()) {

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesDB.TABLA_LIKES_MASCOTA_NUMERO_LIKES + ") as Likes FROM " +
                    ConstantesDB.TABLA_LIKES_MASCOTA + " WHERE " +
                    ConstantesDB.TABLA_LIKES_MASCOTA_ID_MASCOTA + " = " + mascotaActual.getId();

            Cursor registrosLikes =  db.rawQuery(queryLikes, null);

            if (registrosLikes.moveToNext()) {
                mascotaActual.setLikes(registrosLikes.getInt(0));
            } else {
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);
            cant += 1;

            if (cant > 5) {
                break;
            }
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLA_MASCOTA,null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLA_LIKES_MASCOTA,null, contentValues);
        db.close();
    }

    public void insertarFavorito(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLA_FAVORITOS,null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota) {
        int likes = 0;

        String query = "SELECT COUNT(" + ConstantesDB.TABLA_LIKES_MASCOTA_NUMERO_LIKES + ") FROM " +
                ConstantesDB.TABLA_LIKES_MASCOTA + " WHERE " +
                ConstantesDB.TABLA_LIKES_MASCOTA_ID_MASCOTA + " = " + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}
