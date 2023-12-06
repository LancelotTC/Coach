package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;

public final class Controle {
    private static Controle instance;
    private static Profil profil;
    private static AccesLocal accesLocal;


    static String nomFic = "saveprofil";

    private Controle(Context contexte) {
        super();
        accesLocal = AccesLocal.getInstance(contexte);
        profil = accesLocal.recupDernier();
//        recupSerialize(contexte);
    }

    public final static Controle getInstance(Context contexte) {
        if (Controle.instance == null) {
            Controle.instance = new Controle(contexte);
        }

        return Controle.instance;
    }

    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte) {
        profil = new Profil(poids, taille, age, sexe, new Date());
        accesLocal.ajout(profil);
//        Serializer.serialize(nomFic, profil, contexte);
    }

    public float getImg() {
        float img = 0;
        if (profil != null) {
            img = profil.getImg();
        }
        return img;
    }

    public String getMessage() {
        String message = "";

        if (profil != null) {
            message = profil.getMessage();
        }

        return message;
    }

    public Integer getPoids() {
        if (profil != null) {
            return profil.getPoids();
        }
        return null;
    }
    public Integer getTaille() {
        if (profil != null) {
            return profil.getTaille();
        }
        return null;
    }
    public Integer getAge() {
        if (profil != null) {
            return profil.getAge();
        }
        return null;
    }
    public Integer getSexe() {
        if (profil != null) {
            return profil.getSexe();
        }
        return null;
    }

    private static void recupSerialize(Context contexte) {
        profil = (Profil)Serializer.deSerialize(nomFic, contexte);
    }

}
