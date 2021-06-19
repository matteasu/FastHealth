package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
TextView testoHeader;
Button prenota,ricerca,prenotazioni;
ImageView settingsBtn;
Utente u;
ArrayList<Utente> utenti;
int homeCreaPrenotazione=3;
int homeRicercaStruttura=4;
int homeGestionePrenotazioni=5;
int homeImpostazioni=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        testoHeader=findViewById(R.id.homeTxt);
        prenota=findViewById(R.id.homePrenota);
        ricerca=findViewById(R.id.homeSearch);

        settingsBtn=findViewById(R.id.homeSettings);
        prenotazioni=findViewById(R.id.homePrenotazioni);
        utenti = (ArrayList<Utente>) getIntent().getSerializableExtra("lista");
        u=(Utente)getIntent().getSerializableExtra("utente");
        testoHeader.setText(testoHeader.getText()+"\n"+u.getNome());





        prenota.setOnClickListener(v->{
            Context c = this.getApplicationContext();
            CharSequence t = "prenota";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(c, t, duration).show();
        });
        ricerca.setOnClickListener(v->{
            Context c = this.getApplicationContext();
            CharSequence t = "ricerca";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(c, t, duration).show();
        });
        prenotazioni.setOnClickListener(v->{
            Context c = this.getApplicationContext();
            CharSequence t = "prenotazioni";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(c, t, duration).show();
        });
        settingsBtn.setOnClickListener(v->{
            Intent apriImpostazioni = new Intent(Home.this, Impostazioni.class);
            apriImpostazioni.putExtra("utente", u);
            apriImpostazioni.putExtra("lista", utenti);
            startActivityForResult(apriImpostazioni, homeImpostazioni);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_home);
        //recupero dei dati aggiornati
        if (requestCode == homeImpostazioni) {
            if (resultCode == Activity.RESULT_OK) {
                utenti = (ArrayList<Utente>) data.getSerializableExtra("lista");
                if((boolean)data.getSerializableExtra("esci")){
                    Intent returnIntent=getIntent();
                    returnIntent.putExtra("lista",utenti);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                u=(Utente)data.getSerializableExtra("utente");
            }

        }

        testoHeader=findViewById(R.id.homeTxt);
        prenota=findViewById(R.id.homePrenota);
        ricerca=findViewById(R.id.homeSearch);

        settingsBtn=findViewById(R.id.homeSettings);
        prenotazioni=findViewById(R.id.homePrenotazioni);

        testoHeader.setText(testoHeader.getText()+"\n"+u.getNome());
        //utenti = (ArrayList<Utente>) getIntent().getSerializableExtra("lista");







        prenota.setOnClickListener(v->{
            Context c = this.getApplicationContext();
            CharSequence t = "prenota";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(c, t, duration).show();
        });
        ricerca.setOnClickListener(v->{
            Context c = this.getApplicationContext();
            CharSequence t = "ricerca";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(c, t, duration).show();
        });
        prenotazioni.setOnClickListener(v->{
            Context c = this.getApplicationContext();
            CharSequence t = "prenotazioni";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(c, t, duration).show();
        });
        settingsBtn.setOnClickListener(v->{
            Intent apriImpostazioni = new Intent(Home.this, Impostazioni.class);
            apriImpostazioni.putExtra("utente", u);
            apriImpostazioni.putExtra("lista", utenti);
            startActivityForResult(apriImpostazioni, homeImpostazioni);
        });



    }//onActivityResult
}