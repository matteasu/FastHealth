package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;

import java.util.ArrayList;

public class ricercaStruttura extends AppCompatActivity {
    SearchView sw;
    RecyclerView rW;
    adapterricercaStruct aRW;
    ImageView back;
    Utente u;
    ArrayList<StrutturaEsame> strutture;
    int ricercaSE=8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_struttura);
        RecyclerView.LayoutManager lMRW;
        u = (Utente) getIntent().getSerializableExtra("utente");
        strutture = (ArrayList<StrutturaEsame>) getIntent().getSerializableExtra("strutture");
        rW = findViewById(R.id.rStrutRW);
        back = findViewById(R.id.rSsIndietro);
        sw = findViewById(R.id.srchrSSS);
        lMRW = new LinearLayoutManager(this);
        aRW = new adapterricercaStruct(strutture);
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

        back.setOnClickListener(v->{
            Intent returnIntent = getIntent();
            returnIntent.putExtra("utente", getIntent().getSerializableExtra("utente"));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
        aRW.setOnItemClickListener(position -> {
            Intent sceltaEsame = new Intent(ricercaStruttura.this, sceltaEsame.class);
            sceltaEsame.putExtra("utente", u);
            sceltaEsame.putExtra("struttura", strutture.get(position));
            startActivityForResult(sceltaEsame,ricercaSE );
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_ricerca_struttura);
        //recupero dei dati aggiornati
        if (requestCode == ricercaSE) {
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
        strutture = (ArrayList<StrutturaEsame>) getIntent().getSerializableExtra("strutture");
        rW = findViewById(R.id.rStrutRW);
        back = findViewById(R.id.rSsIndietro);
        sw = findViewById(R.id.srchrSSS);
        lMRW = new LinearLayoutManager(this);
        aRW = new adapterricercaStruct(strutture);
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

        back.setOnClickListener(v->{
            Intent returnIntent = getIntent();
            returnIntent.putExtra("utente", getIntent().getSerializableExtra("utente"));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
        aRW.setOnItemClickListener(position -> {
            Intent sceltaEsame = new Intent(ricercaStruttura.this, sceltaEsame.class);
            sceltaEsame.putExtra("utente", u);
            sceltaEsame.putExtra("struttura", strutture.get(position));
            startActivityForResult(sceltaEsame,ricercaSE );
        });

    }//onActivityResult
}