package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class modificaData extends AppCompatActivity {

    Calendar c = Calendar.getInstance();
    ArrayList<Calendar> date = new ArrayList<>();
    ImageView back,modificaD;
    Prenotazione p;
    RecyclerView rw;
    TextView noD;
    RecyclerView.LayoutManager lMRW;
    adapterDate adapterR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_data);
        Utente u= (Utente)getIntent().getSerializableExtra("utente");
        p=(Prenotazione)getIntent().getSerializableExtra("prenotazione");
        back=findViewById(R.id.modificaInd);
        modificaD=findViewById(R.id.modificaD);
        noD=findViewById(R.id.noDate);
        rw=findViewById(R.id.modificaRW);
        lMRW=new LinearLayoutManager(this);
        rw.setLayoutManager(lMRW);
        generaDate();
        adapterR=new adapterDate(date);
        if(date.size()!=0) {
            noD.setVisibility(View.GONE);
            rw.setAdapter(adapterR);
        }
        back.setOnClickListener(v->{
            Intent r=getIntent();
            r.putExtra("utente",u);
            setResult(Activity.RESULT_CANCELED,r);
            finish();
        });
        adapterR.setOnItemClickListener((position,nuovoOrario) -> {
            p.setData(date.get(position));
            p.setOra(nuovoOrario);
            Intent r=getIntent();
            r.putExtra("utente",u);
            r.putExtra("prenotazione",p);
            setResult(Activity.RESULT_OK,r);
            finish();
        });


    }


    void generaDate() {
        Calendar cc = Calendar.getInstance();
        for (int i = 0; i < (int) Math.floor(Math.random() * (10 - 0 + 1) + 0); i++) {
            cc.set(2021, (int) Math.floor(Math.random() * (12 - c.get(Calendar.MONTH) + 1) + 0),
                    (int) Math.floor(Math.random() * (31 - c.get(Calendar.DAY_OF_MONTH) + 1) + 0));
            date.add(cc);

        }
    }


}