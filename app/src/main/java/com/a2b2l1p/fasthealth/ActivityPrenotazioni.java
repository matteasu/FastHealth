package com.a2b2l1p.fasthealth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    //elementi dialog
    TextView np,d,s,nm,cA,npa,note;
    ImageView close;
    Button p,mA;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utente u;
        ImageView back;
        RecyclerView rW;
        adapterPrenotazioni aRW;
        Prenotazione pp=new Prenotazione("BBBBB","aa","aa","aa","ciao",null);
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
        dialogBuilder.setView(popup);
        dialog=dialogBuilder.create();
        dialog.show();

    }
}