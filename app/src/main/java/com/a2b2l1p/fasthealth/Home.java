package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
TextView testoHeader;
Button prenota,ricerca,prenotazioni;
ImageView backBtn,settingsBtn;
Utente u;
int homeCreaPrenotazione=3;
int homeRicercaStruttura=4;
int homeGestionePrenotazioni=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        testoHeader=findViewById(R.id.homeTxt);
        prenota=findViewById(R.id.homePrenota);
        ricerca=findViewById(R.id.homeSearch);
        backBtn=findViewById(R.id.homeIndietro);
        settingsBtn=findViewById(R.id.homeSettings);
        prenotazioni=findViewById(R.id.homePrenotazioni);
        u=(Utente)getIntent().getSerializableExtra("utente");
        testoHeader.setText(testoHeader.getText()+"\n"+u.getNome());



        backBtn.setOnClickListener(v->{
            finish();
        });

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
    }
}