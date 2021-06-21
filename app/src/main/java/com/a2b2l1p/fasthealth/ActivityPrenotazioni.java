package com.a2b2l1p.fasthealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ActivityPrenotazioni extends AppCompatActivity {
    ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
    SearchView sw;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utente u;
        ImageView back;
        RecyclerView rW;
        adapterPrenotazioni aRW;
        Prenotazione pp=new Prenotazione("BBBBB","aa","aa","aa",null);
        RecyclerView.LayoutManager lMRW;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni);

        u = (Utente) getIntent().getSerializableExtra("utente");
        prenotazioni = u.getPrenotazioni();
        rW = findViewById(R.id.prenotazioniRW);
        back=findViewById(R.id.prenotazioniIndietro);
        back.setOnClickListener(v->{
            Intent returnIntent=getIntent();
            returnIntent.putExtra("utente",u);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
        lMRW = new LinearLayoutManager(this);
        aRW = new adapterPrenotazioni(prenotazioni);
        //GESTIONE BARRA DI RICERCA
       /* sw = findViewById(R.id.searchBBBB);
        sw.setImeOptions(EditorInfo.IME_ACTION_DONE);
        sw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                aRW.getFilter().filter(newText);
                return false;
            }
        });*/

        rW.setLayoutManager(lMRW);
        rW.setAdapter(aRW);

        aRW.setOnItemClickListener(position -> {
            u.getPrenotazioni().remove(position);
            //lanciare nuova attività coi dettagli prenotazione
        });


    }
}