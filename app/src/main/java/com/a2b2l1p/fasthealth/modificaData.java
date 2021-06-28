package com.a2b2l1p.fasthealth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class modificaData extends AppCompatActivity {

    Calendar c = Calendar.getInstance();
    ArrayList<Calendar> date = new ArrayList<>();
    ImageView back, modificaD;
    Prenotazione p;
    RecyclerView rw;
    TextView noD;
    RecyclerView.LayoutManager lMRW;
    adapterDate adapterR;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_data);
        Utente u = (Utente) getIntent().getSerializableExtra("utente");
        p = (Prenotazione) getIntent().getSerializableExtra("prenotazione");
        back = findViewById(R.id.modificaInd);
        modificaD = findViewById(R.id.modificaD);
        noD = findViewById(R.id.noDate);
        rw = findViewById(R.id.modificaRW);
        lMRW = new LinearLayoutManager(this);
        rw.setLayoutManager(lMRW);
        generaDate();
        date.sort((o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        adapterR = new adapterDate(date);
        if (date.size() != 0) {
            noD.setVisibility(View.GONE);
            rw.setAdapter(adapterR);
        }
        back.setOnClickListener(v -> {
            Intent r = getIntent();
            r.putExtra("utente", u);
            setResult(Activity.RESULT_CANCELED, r);
            finish();
        });
        adapterR.setOnItemClickListener((position, nuovoOrario) -> {
            p.setData(date.get(position));
            p.setOra(nuovoOrario);
            Intent r = getIntent();
            r.putExtra("utente", u);
            r.putExtra("prenotazione", p);
            setResult(Activity.RESULT_OK, r);
            finish();
        });
        modificaD.setOnClickListener(v -> {
            showDialog();

        });


    }


    void generaDate() {
        Calendar cc = Calendar.getInstance();
        int n=(int) Math.floor(Math.random() * (10 - 0 + 1) + 0);
        int m,d;
        for (int i = 0; i < n; i++) {
            cc=Calendar.getInstance();
            m=(int) Math.floor(Math.random() * (12 - (c.get(Calendar.MONTH)+1) + 1) + (c.get(Calendar.MONTH)+1));
            d=(int) Math.floor(Math.random() * (31 - c.get(Calendar.DAY_OF_MONTH) + 1) + c.get(Calendar.DAY_OF_MONTH));
            cc.set(2021,m,d);
            date.add(cc);
            //cc.clear();
        }
    }

    void showDialog() {
        DialogFragment newFragment = DatePickerFragment.newInstance();

        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void doPositiveClick(Calendar d) {
        date.clear();
        generaDate();
        date.sort((o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        adapterR.notifyDataSetChanged();
        if(date.size()==0){
            noD.setVisibility(View.VISIBLE);
        }

    }

    public void doNegativeClick(Calendar d) {
        // Do stuff here.

    }

    public void updateDN(Calendar d) {
        c = d;



    }


}