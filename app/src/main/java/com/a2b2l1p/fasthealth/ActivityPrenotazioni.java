package com.a2b2l1p.fasthealth;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ActivityPrenotazioni extends AppCompatActivity {
    ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
    SearchView sw;
    private AlertDialog.Builder dialogBuilder,dialogBP;
    private AlertDialog dialog,dialogP;


    //elementi dialog
    TextView np,d,s,nm,cA,npa,note,amm,No;
    EditText nC,sC,CCV,nT;
    Button an,po;
    ImageView close;
    Button p,mA;
    RecyclerView rW;
    adapterPrenotazioni aRW;
    int prenM=9;
    Utente u;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageView back;

        //Prenotazione p = new Prenotazione("Colonoscopia", "Policlinico Dulio Casula", "13:40", "W40", "Sergio Pinto", null, (float) 69.49);
        RecyclerView.LayoutManager lMRW;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni);

        u = (Utente) getIntent().getSerializableExtra("utente");
        //u.getPrenotazioni().add(p);
        prenotazioni = u.getPrenotazioni();
        No=findViewById(R.id.noPrenotazioni);
        if(prenotazioni.size()!=0){
            No.setVisibility(View.GONE);
        }
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
        nm.setText("DR. "+prenotazioni.get(position).getNomeMedico());
        cA.setText(prenotazioni.get(position).getCodAcc());
        npa.setText(npa.getText()+""+prenotazioni.get(position).getPersCoda());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        d.setText(format.format(prenotazioni.get(position).getData().getTime())+"\n"+prenotazioni.get(position).getOra());
        note.setText(prenotazioni.get(position).getNoteMed().equals("") ?"Nessuna nota da parte del medico":prenotazioni.get(position).getNoteMed());
        close.setOnClickListener(v->dialog.dismiss());
        p.setVisibility(prenotazioni.get(position).isPagato()?View.GONE:View.VISIBLE);


        mA.setOnClickListener(v2->{
            Intent modifica= new Intent(ActivityPrenotazioni.this,modificaData.class);
            modifica.putExtra("prenotazione",prenotazioni.get(position));
            modifica.putExtra("utente",u);
            dialog.dismiss();
            startActivityForResult(modifica,prenM);
            //finish();
        });


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

            po.setOnClickListener(v1 -> {
                if(verificaFrom()) {
                    prenotazioni.get(position).setPagato(true);
                    p.setVisibility(prenotazioni.get(position).isPagato() ? View.GONE : View.VISIBLE);
                    npa.setVisibility(View.GONE);
                    aRW.notifyDataSetChanged();
                    dialogP.dismiss();
                    Context c = this.getApplicationContext();
                    CharSequence t = "Pagamento effetuato con successo";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(c, t, duration).show();
                    }

            });





            an.setOnClickListener(v12 -> dialogP.dismiss());
            dialogBP.setView(popupP);
            dialogP=dialogBP.create();
            v.clearFocus();
            dialogP.show();
        });

        dialogBuilder.setView(popup);
        dialog=dialogBuilder.create();
        dialog.show();

    }

    protected boolean verificaFrom() {
        boolean err = true;
        if(nC.getText().length()==0){
            nC.setError("Numero di carta mancante");
            err=false;
        }else nC.setError(null);
        if(sC.getText().length()==0){
            sC.setError("Scadenza della carta mancante");
            err=false;
        }else sC.setError(null);
        if(CCV.getText().length()==0){
            CCV.setError("CCV mancante");
            err=false;
        }else CCV.setError(null);
        if(nT.getText().length()==0){
            nT.setError("Nome del titolare mancante");
            err=false;
        }else nT.setError(null);
        return err;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_prenotazioni);
        RecyclerView.LayoutManager lMRW;
        rW = findViewById(R.id.prenotazioniRW);
        lMRW = new LinearLayoutManager(this);

        if(requestCode==prenM){
            if(resultCode== Activity.RESULT_OK){
                u = (Utente) data.getSerializableExtra("utente");
                //prenotazioni = u.getPrenotazioni();
                u.rimuoviPrenotazione((Prenotazione)data.getSerializableExtra("prenotazione"));
                u.addPrenotazione((Prenotazione)data.getSerializableExtra("prenotazione"));
                prenotazioni = u.getPrenotazioni();
                aRW.notifyDataSetChanged();
                No=findViewById(R.id.noPrenotazioni);
                if(prenotazioni.size()!=0){
                    No.setVisibility(View.GONE);
                }
                Context c = this.getApplicationContext();
                CharSequence t = "Appuntamento modificato con successo";
                int duration = Toast.LENGTH_LONG;
                Toast.makeText(c, t, duration).show();
            }else{
                u = (Utente) data.getSerializableExtra("utente");
                prenotazioni = u.getPrenotazioni();
            }
        }




        ImageView back;
        aRW = new adapterPrenotazioni(prenotazioni);
        aRW.notifyDataSetChanged();
        //Prenotazione p = new Prenotazione("Colonoscopia", "Policlinico Dulio Casula", "13:40", "W40", "Sergio Pinto", null, (float) 69.49);




        //u.getPrenotazioni().add(p);


        back=findViewById(R.id.prenotazioniIndietro);
        back.setOnClickListener(v->{
            Intent returnIntent=getIntent();
            returnIntent.putExtra("utente",u);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

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

        aRW.setOnItemClickListener(this::creaDialog);
    }//onActivityResult
}