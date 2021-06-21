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
import java.util.Calendar;

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

        Prenotazione p=new Prenotazione("Colonoscopia","Policlinico Dulio Casula","13:40","W40","Sergio Pinto",null);
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
        Prenotazione pp=new Prenotazione("BBBBB","aa","aa","aa","ciao",null);
        prenotazioni.setOnClickListener(v->{
            Intent apriPrenotazioni = new Intent(Home.this, ActivityPrenotazioni.class);
            p.setPagato(true);
            u.addPrenotazione(p);
            u.addPrenotazione(pp);
            apriPrenotazioni.putExtra("utente", u);

            apriPrenotazioni.putExtra("lista", utenti);
            startActivityForResult(apriPrenotazioni, homeGestionePrenotazioni);
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

        if(requestCode==homeGestionePrenotazioni){
            if(resultCode==Activity.RESULT_OK){
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
            Intent apriPrenotazioni = new Intent(Home.this, ActivityPrenotazioni.class);

            apriPrenotazioni.putExtra("utente", u);

            apriPrenotazioni.putExtra("lista", utenti);
            startActivityForResult(apriPrenotazioni, homeGestionePrenotazioni);
        });
        settingsBtn.setOnClickListener(v->{
            Intent apriImpostazioni = new Intent(Home.this, Impostazioni.class);
            apriImpostazioni.putExtra("utente", u);
            apriImpostazioni.putExtra("lista", utenti);
            startActivityForResult(apriImpostazioni, homeImpostazioni);
        });



    }//onActivityResult
}