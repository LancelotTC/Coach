package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;

    private static AccesLocal instance;

    private SQLiteDatabase bd;



    private AccesLocal(Context contexte) {
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, versionBase);
    }

    public static AccesLocal getInstance(Context contexte) {
        if (instance == null) {
            instance = new AccesLocal(contexte);
        }
        return instance;
    }

    public void ajout(Profil profil) {
        bd = accesBD.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("datemesure", profil.getDateMesure().toString());
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());

        bd.insert("profil", null, values);

        bd.close();
    }

    public Profil recupDernier() {
        Profil profil = null;
        bd = accesBD.getReadableDatabase();
        String req = "select datemesure, poids, taille, age, sexe from profil";
        Cursor curseur = bd.rawQuery(req, null);

        curseur.moveToLast();
        if (curseur.isAfterLast()) {
            return null;
        }

        Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));
        Integer poids = curseur.getInt(1);
        Integer taille = curseur.getInt(2);
        Integer age = curseur.getInt(3);
        Integer sexe = curseur.getInt(4);

        return new Profil(poids, taille, age, sexe, dateMesure);

    }


}
