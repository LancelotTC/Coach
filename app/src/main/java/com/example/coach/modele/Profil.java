package com.example.coach.modele;

import static java.lang.Math.pow;

public class Profil {
    private Integer sexe;
    private Integer poids;
    private Integer taille;
    private Integer age;

    private float img = 0;
    private String message = "";

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public float getImg() {
        if (img == 0) {
            // convertion en mètres
            float tailleMetre = ((float)taille)/100;
            img = (float)((1.2 * poids/(tailleMetre * tailleMetre)) + (0.23 * age) - (10.83 * sexe) - 5.4);
        }
        return img;
    }

    public String getMessage() {
        if (message.equals("")) {
            message = "normal";
            Integer min = minFemme, max = maxFemme;
            if (sexe == 1) {
                min = minHomme;
                max = maxHomme;
            }

            img = getImg();

            if (img < min) {
                message = "trop faible";
            } else {
                if (img > max) {
                    message = "trop élevé";
                }
            }
        }
        return message;
    }


}
