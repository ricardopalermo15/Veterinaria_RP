package com.infinitid.veterinaria_rp.db;

public class ConstantesDB {
    public static final String DATABASE_NAME = "veterinaria";
    public static final int DATABASE_VERSION = 5;

    public static final String TABLA_MASCOTA = "mascota";
    public static final String TABLA_MASCOTA_ID = "id";
    public static final String TABLA_MASCOTA_NOMBRE = "nombre";
    public static final String TABLA_MASCOTA_FOTO = "foto";

    public static final String TABLA_LIKES_MASCOTA = "mascota_likes";
    public static final String TABLA_LIKES_MASCOTA_ID = "id";
    public static final String TABLA_LIKES_MASCOTA_ID_MASCOTA = "id_mascota";
    public static final String TABLA_LIKES_MASCOTA_NUMERO_LIKES = "numero_likes";

    public static final String TABLA_FAVORITOS = "favoritos";
    public static final String TABLA_FAVORITOS_ID = "id";
    public static final String TABLA_FAVORITOS_ID_MASCOTA = "id_mascota";
}
