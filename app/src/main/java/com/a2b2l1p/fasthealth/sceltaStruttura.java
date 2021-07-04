package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.util.Calendar;

public class sceltaStruttura extends AppCompatActivity {
    SearchView sw;
    RecyclerView rW;
    adapterEsamiStrut aRW;
    ImageView back;
    Utente u;
    EsameStruttura e;
    AlertDialog.Builder dialogB;
    AlertDialog dialog;
    TextView nomeConf;
    Chip no,ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView.LayoutManager lMRW;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_struttura);
        u=(Utente)getIntent().getSerializableExtra("utente");
        e=(EsameStruttura)getIntent().getSerializableExtra("esame");
        //assegnamento rw
        rW=findViewById(R.id.rSRWRW);
        back=findViewById(R.id.rSrIndietro);
        //assegnamento back
        //assegnamento sw
        sw=findViewById(R.id.srchrSS);
        lMRW = new LinearLayoutManager(this);
        aRW=new adapterEsamiStrut(e.getS());
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

        aRW.setOnItemClickListener(this::creaDialog);

        back.setOnClickListener(v->{
            Intent returnI =getIntent();
            returnI.putExtra("utente",u);
            setResult(RESULT_CANCELED,returnI);
            finish();
        });
    }


    void creaDialog(int position){
        dialogB=new AlertDialog.Builder(this);
        final View popup=getLayoutInflater().inflate(R.layout.popup_conferma,null);
        nomeConf=popup.findViewById(R.id.confEsame);
        no=popup.findViewById(R.id.confNo);
        ok=popup.findViewById(R.id.confOK);
        nomeConf.setText(nomeConf.getText()+"\n"+e.getNomeEsame());

        no.setOnClickListener(v->{
            dialog.dismiss();
        });

        ok.setOnClickListener(v->{



            String ora=(int)Math.floor(Math.random()*(20-8+1)+8)+":"+(int)Math.floor(Math.random()*(59-10+1)+10);
            Prenotazione p=new Prenotazione(e.getNomeEsame(),e.getS().get(position).getNome(),ora,"W20","Mario Rossi",e.getS().get(position).getC(),e.getS().get(position).getCosto());
            u.addPrenotazione(p);
            Intent a=getIntent();
            a.putExtra("utente",u);
            setResult(Activity.RESULT_OK, a);
            finish();
            
        });


        dialogB.setView(popup);
        dialog=dialogB.create();
        dialog.show();

    }
}