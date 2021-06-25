package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class ricercaPrestazione extends AppCompatActivity {

    SearchView sw;
    RecyclerView rW;
    adapterEsami aRW;
    ImageView back;
    Utente u;
    ArrayList<EsameStruttura> esami;
    int ricercaPS = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView.LayoutManager lMRW;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_prestazione);
        u = (Utente) getIntent().getSerializableExtra("utente");
        esami = (ArrayList<EsameStruttura>) getIntent().getSerializableExtra("esami");
        rW = findViewById(R.id.rprenotazioniRW);
        back = findViewById(R.id.rPrIndietro);
        sw = findViewById(R.id.srchrP);
        lMRW = new LinearLayoutManager(this);
        aRW = new adapterEsami(esami);
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
        });
        rW.setLayoutManager(lMRW);
        rW.setAdapter(aRW);
        back.setOnClickListener(v -> {
            Intent returnIntent = getIntent();
            returnIntent.putExtra("utente", getIntent().getSerializableExtra("utente"));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

        aRW.setOnItemClickListener(position -> {
            Intent sceltaStruttura = new Intent(ricercaPrestazione.this, sceltaStruttura.class);
            sceltaStruttura.putExtra("utente", u);
            sceltaStruttura.putExtra("esame", esami.get(position));
            startActivityForResult(sceltaStruttura, ricercaPS);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_ricerca_prestazione);
        //recupero dei dati aggiornati
        if (requestCode == ricercaPS) {
            if (resultCode == Activity.RESULT_OK) {
                u = (Utente) data.getSerializableExtra("utente");
                Intent returnIntent = getIntent();
                returnIntent.putExtra("utente", u);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }else if(resultCode==Activity.RESULT_CANCELED){
                u = (Utente) data.getSerializableExtra("utente");
            }


        }
        RecyclerView.LayoutManager lMRW;

        u = (Utente) getIntent().getSerializableExtra("utente");
        esami = (ArrayList<EsameStruttura>) getIntent().getSerializableExtra("esami");
        rW = findViewById(R.id.rprenotazioniRW);
        back = findViewById(R.id.rPrIndietro);
        sw = findViewById(R.id.srchrP);
        lMRW = new LinearLayoutManager(this);
        aRW = new adapterEsami(esami);
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
        });
        rW.setLayoutManager(lMRW);
        rW.setAdapter(aRW);
        back.setOnClickListener(v -> {
            Intent returnIntent = getIntent();
            returnIntent.putExtra("utente", getIntent().getSerializableExtra("utente"));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

        aRW.setOnItemClickListener(position -> {
            Intent sceltaStruttura = new Intent(ricercaPrestazione.this, sceltaStruttura.class);
            sceltaStruttura.putExtra("utente", u);
            sceltaStruttura.putExtra("esame", esami.get(position));
            startActivityForResult(sceltaStruttura, ricercaPS);
        });


    }//onActivityResult
}