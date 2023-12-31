package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;

    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    private Controle controle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void ecouteCalcul() {
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                Integer poids = 0, taille = 0, age = 0, sexe = 0;

                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {}

                if (rdHomme.isChecked()) {
                    sexe = 1;
                }

                if (poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }
                affichResult(poids, taille, age, sexe);
            }
        });

    }

    public void affichResult(Integer poids, Integer taille, Integer age, Integer sexe) {
        controle.creerProfil(poids, taille, age, sexe, this);
        float img = controle.getImg();
        String message = controle.getMessage();

        int image;
        if (message.equals("trop élevé")) {
            image = R.drawable.graisse;
            lblIMG.setTextColor(Color.RED);
        } else if (message.equals("trop faible")) {
            image = R.drawable.maigre;
            lblIMG.setTextColor(Color.RED);
        } else {
            image = R.drawable.normal;
            lblIMG.setTextColor(Color.GREEN);
        }

        imgSmiley.setImageResource(image);
        lblIMG.setText(String.format("%.01f", img) + " : IMG " + message);


    }

    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);

        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);

        lblIMG = (TextView) findViewById(R.id.lblIMG);

        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);

        btnCalc = (Button) findViewById(R.id.btnCalc);
        controle = Controle.getInstance(this);
        ecouteCalcul();
        recupProfil();
    }

    public void recupProfil() {
        if (controle.getPoids() == null) {
            return;
        }

        txtPoids.setText(""+controle.getPoids());
        txtTaille.setText(""+controle.getTaille());
        txtAge.setText(""+controle.getAge());
        if (controle.getSexe() == 1) {
            rdHomme.setChecked(true);
        } else {
            rdFemme.setChecked(true);
        }

        btnCalc.performClick();
    }

}