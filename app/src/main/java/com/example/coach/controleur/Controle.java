package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {
    private static Controle instance;
    private static Profil profil;

    private Controle() {
        super();
    }

    public final static Controle getInstance() {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
        }

        return Controle.instance;
    }

    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(poids, taille, age, sexe);
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

}
