package com.a2b2l1p.fasthealth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ActivityPrenotazioni extends AppCompatActivity {
    ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
    SearchView sw;
    private AlertDialog.Builder dialogBuilder,dialogBP;
    private AlertDialog dialog,dialogP;


    //elementi dialog
    TextView np,d,s,nm,cA,npa,note,amm;
    EditText nC,sC,CCV,nT;
    Button an,po;
    ImageView close;
    Button p,mA;
    RecyclerView rW;
    adapterPrenotazioni aRW;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utente u;
        ImageView back;

        Prenotazione p = new Prenotazione("Colonoscopia", "Policlinico Dulio Casula", "13:40", "W40", "Sergio Pinto", null, (float) 69.49);
        RecyclerView.LayoutManager lMRW;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni);

        u = (Utente) getIntent().getSerializableExtra("utente");
        u.getPrenotazioni().add(p);
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
            creaDialog(position);
        });




    }

    public void creaDialog(int position){
        dialogBuilder=new AlertDialog.Builder(this);
        final View popup=getLayoutInflater().inflate(R.layout.popup,null);
        np= popup.findViewById(R.id.popupNomePrest);
        d=popup.findViewById(R.id.popupData);
        s=popup.findViewById(R.id.popupStruct);
        nm=popup.findViewById(R.id.popupNomeMed);
        cA=popup.findViewById(R.id.popupCA);
        npa=popup.findViewById(R.id.persAtt);
        note=popup.findViewById(R.id.noteMedT);
        close=popup.findViewById(R.id.popupClose);
        p=popup.findViewById(R.id.paga);
        mA=popup.findViewById(R.id.modificaAPP);


        np.setText(prenotazioni.get(position).getNomeEsame());
        s.setText(prenotazioni.get(position).getNomeStruttura());
        nm.setText(prenotazioni.get(position).getNomeMedico());
        cA.setText(prenotazioni.get(position).getCodAcc());
        d.setText(prenotazioni.get(position).getOra());
        note.setText(prenotazioni.get(position).getNoteMed().equals("") ?"Nessuna nota da parte del medico":prenotazioni.get(position).getNoteMed());
        close.setOnClickListener(v->dialog.dismiss());
        p.setVisibility(prenotazioni.get(position).isPagato()?View.GONE:View.VISIBLE);

        p.setOnClickListener(v->{
            dialogBP=new AlertDialog.Builder(this);
            final View popupP=getLayoutInflater().inflate(R.layout.popup_pagamento,null);
            //TextView nC,sC,CCV,nT;
            //    Button an,po;
            nC=popupP.findViewById(R.id.pagCC);
            sC=popupP.findViewById(R.id.pagSS);
            CCV=popupP.findViewById(R.id.pagCCV);
            nT=popupP.findViewById(R.id.pagTit);
            an=popupP.findViewById(R.id.annulla);
            po=popupP.findViewById(R.id.payNow);
            amm=popupP.findViewById(R.id.pagAmmount);


            amm.setText(amm.getText()+""+prenotazioni.get(position).getCosto()+"€");

            po.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prenotazioni.get(position).setPagato(true);
                    p.setVisibility(prenotazioni.get(position).isPagato()?View.GONE:View.VISIBLE);
                    aRW.notifyDataSetChanged();

                    dialogP.dismiss();


                }
            });
            an.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogP.dismiss();
                }
            });
            dialogBP.setView(popupP);
            dialogP=dialogBP.create();
            dialogP.show();
        });

        dialogBuilder.setView(popup);
        dialog=dialogBuilder.create();
        dialog.show();

    }
}