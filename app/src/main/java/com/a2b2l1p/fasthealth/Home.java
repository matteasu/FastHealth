package com.a2b2l1p.fasthealth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Home extends AppCompatActivity {
    TextView testoHeader;
    Button prenota, ricerca, prenotazioni;
    ImageView settingsBtn;
    Utente u;
    ArrayList<Utente> utenti;
    int homeCreaPrenotazione = 3;
    int homeRicercaStruttura = 4;
    int homeGestionePrenotazioni = 5;
    int homeImpostazioni = 6;

    private ArrayList<EsameStruttura> esami = new ArrayList<>();
    private ArrayList<StrutturaEsame> struttue = new ArrayList<>();
    private final Strutture[] ss = new Strutture[7];
    private final Esame[] ee = new Esame[15];


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        inizializzaStrutture();
        testoHeader = findViewById(R.id.homeTxt);
        prenota = findViewById(R.id.homePrenota);
        ricerca = findViewById(R.id.homeSearch);

        settingsBtn = findViewById(R.id.homeSettings);
        prenotazioni = findViewById(R.id.homePrenotazioni);
        utenti = (ArrayList<Utente>) getIntent().getSerializableExtra("lista");
        u = (Utente) getIntent().getSerializableExtra("utente");
        testoHeader.setText(testoHeader.getText() + "\n" + u.getNome());


        prenota.setOnClickListener(v -> {
            Intent ricercaPr = new Intent(Home.this, ricercaPrestazione.class);
            ricercaPr.putExtra("utente", u);
            ricercaPr.putExtra("esami", esami);
            startActivityForResult(ricercaPr, homeCreaPrenotazione);
        });
        ricerca.setOnClickListener(v -> {
            Intent ricercaS=new Intent(Home.this,ricercaStruttura.class);
            ricercaS.putExtra("utente",u);
            ricercaS.putExtra("strutture",struttue);
            startActivityForResult(ricercaS,homeRicercaStruttura);
        });

        prenotazioni.setOnClickListener(v -> {
            Intent apriPrenotazioni = new Intent(Home.this, ActivityPrenotazioni.class);
            apriPrenotazioni.putExtra("utente", u);
            apriPrenotazioni.putExtra("lista", utenti);
            startActivityForResult(apriPrenotazioni, homeGestionePrenotazioni);
        });
        settingsBtn.setOnClickListener(v -> {
            Intent apriImpostazioni = new Intent(Home.this, Impostazioni.class);
            apriImpostazioni.putExtra("utente", u);
            apriImpostazioni.putExtra("lista", utenti);
            startActivityForResult(apriImpostazioni, homeImpostazioni);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_home);
        //recupero dei dati aggiornati
        if (requestCode == homeImpostazioni) {
            if (resultCode == Activity.RESULT_OK) {
                utenti = (ArrayList<Utente>) data.getSerializableExtra("lista");
                if ((boolean) data.getSerializableExtra("esci")) {
                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("lista", utenti);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                u = (Utente) data.getSerializableExtra("utente");
            }

        }

        if (requestCode == homeGestionePrenotazioni) {
            if (resultCode == Activity.RESULT_OK) {
                u = (Utente) data.getSerializableExtra("utente");
                if (utenti.remove(u))
                    utenti.add(u);
            }
        }

        if (requestCode == homeCreaPrenotazione || requestCode ==homeRicercaStruttura) {
            if (resultCode == Activity.RESULT_OK) {
                u = (Utente) data.getSerializableExtra("utente");
                if (utenti.remove(u))
                    utenti.add(u);
            }
            Intent apriPrenotazioni = new Intent(Home.this, ActivityPrenotazioni.class);

            apriPrenotazioni.putExtra("utente", u);

            apriPrenotazioni.putExtra("lista", utenti);
            startActivityForResult(apriPrenotazioni, homeGestionePrenotazioni);
        }

        testoHeader = findViewById(R.id.homeTxt);
        prenota = findViewById(R.id.homePrenota);
        ricerca = findViewById(R.id.homeSearch);

        settingsBtn = findViewById(R.id.homeSettings);
        prenotazioni = findViewById(R.id.homePrenotazioni);

        testoHeader.setText(testoHeader.getText() + "\n" + u.getNome());
        //utenti = (ArrayList<Utente>) getIntent().getSerializableExtra("lista");


        prenota.setOnClickListener(v -> {
            Intent ricercaPr = new Intent(Home.this, ricercaPrestazione.class);
            ricercaPr.putExtra("utente", u);
            ricercaPr.putExtra("esami", esami);
            startActivityForResult(ricercaPr, homeCreaPrenotazione);
        });
        ricerca.setOnClickListener(v -> {
            Intent ricercaS=new Intent(Home.this,ricercaStruttura.class);
            ricercaS.putExtra("utente",u);
            ricercaS.putExtra("strutture",struttue);
            startActivityForResult(ricercaS,homeRicercaStruttura);
        });
        prenotazioni.setOnClickListener(v -> {
            Intent apriPrenotazioni = new Intent(Home.this, ActivityPrenotazioni.class);

            apriPrenotazioni.putExtra("utente", u);

            apriPrenotazioni.putExtra("lista", utenti);
            startActivityForResult(apriPrenotazioni, homeGestionePrenotazioni);
        });
        settingsBtn.setOnClickListener(v -> {
            Intent apriImpostazioni = new Intent(Home.this, Impostazioni.class);
            apriImpostazioni.putExtra("utente", u);
            apriImpostazioni.putExtra("lista", utenti);
            startActivityForResult(apriImpostazioni, homeImpostazioni);
        });


    }//onActivityResult

    @RequiresApi(api = Build.VERSION_CODES.N)
    void inizializzaStrutture() {
        ss[0] = new Strutture("Policlinico Universitario\nDulio Casula");
        ss[1] = new Strutture("Studio Radiologico Casciu");
        ss[2] = new Strutture("Centro Diagnostico Dolianova");
        ss[3] = new Strutture("Studio Radiologico Deriu");
        ss[4] = new Strutture("Ospedale G. Brotzu");
        ss[5] = new Strutture("Ospedale Santissima Trinnità");
        ss[6] = new Strutture("ASL Quartu");

        ee[0] = new Esame("Ecografia Anse Intestinali");
        ee[1] = new Esame("MOC");
        ee[2] = new Esame("Analisi Ematochimiche");
        ee[3] = new Esame("Entero RM");
        ee[4] = new Esame("Visita Occulistica");
        ee[5] = new Esame("Gastroscopia");
        ee[6] = new Esame("Colonscopia con biopsia");
        ee[7] = new Esame("Colonscopia");
        ee[8] = new Esame("Visita Dermatologica");
        ee[9] = new Esame("Ecocardiologia");
        ee[10] = new Esame("Angiografia");
        ee[11] = new Esame("Breath Test");
        ee[12] = new Esame("Visita Allergologica");
        ee[13] = new Esame("Polisonnografia");
        ee[14] = new Esame("Visita Proctologica");

        ArrayList<Strutture> tuttecos = new ArrayList<>(Arrays.asList(ss));
        ArrayList<Strutture> poliBrotz = new ArrayList<>();
        ArrayList<Strutture> poliCash = new ArrayList<>();
        ArrayList<Strutture> poliBrotzT = new ArrayList<>();
        ArrayList<Strutture> onlyPoli = new ArrayList<>();
        ArrayList<Strutture> poliASLBrotz = new ArrayList<>();
        ArrayList<Strutture> poliCDDBT = new ArrayList<>();
        ArrayList<Strutture> bt = new ArrayList<>();
        ArrayList<Strutture> apbt = new ArrayList<>();

        ArrayList<Esame> esamiPoli = new ArrayList<>();
        ArrayList<Esame> esamiCash = new ArrayList<>();
        ArrayList<Esame> esamiDolia = new ArrayList<>();
        ArrayList<Esame> esamiDeriu = new ArrayList<>();
        ArrayList<Esame> esamiBrotzu = new ArrayList<>();
        ArrayList <Esame> esamiT =new ArrayList<>();
        ArrayList <Esame> esamiASL = new ArrayList<>();




        poliCash.add(ss[0]);
        poliCash.add(ss[1]);
        poliBrotz.add(ss[0]);
        poliBrotz.add(ss[4]);
        poliBrotzT.add(ss[0]);
        poliBrotzT.add(ss[4]);
        poliBrotzT.add(ss[5]);
        onlyPoli.add(ss[0]);
        poliASLBrotz.add(ss[0]);
        poliASLBrotz.add(ss[6]);
        poliASLBrotz.add(ss[4]);
        poliCDDBT.add(ss[0]);
        poliCDDBT.add(ss[1]);
        poliCDDBT.add(ss[3]);
        poliCDDBT.add(ss[2]);
        poliCDDBT.add(ss[4]);
        poliCDDBT.add(ss[5]);
        bt.add(ss[4]);
        bt.add(ss[5]);
        apbt.add(ss[6]);
        apbt.add(ss[0]);
        apbt.add(ss[4]);
        apbt.add(ss[5]);


        esamiPoli.add(ee[0]);
        esamiPoli.add(ee[1]);
        esamiPoli.add(ee[2]);
        esamiPoli.add(ee[3]);
        esamiPoli.add(ee[4]);
        esamiPoli.add(ee[5]);
        esamiPoli.add(ee[6]);
        esamiPoli.add(ee[7]);
        esamiPoli.add(ee[8]);
        esamiPoli.add(ee[9]);
        esamiPoli.add(ee[11]);
        esamiPoli.add(ee[12]);
        esamiPoli.add(ee[13]);
        esamiPoli.add(ee[14]);

        esamiCash.add(ee[0]);
        esamiCash.add(ee[1]);
        esamiCash.add(ee[9]);

        esamiDolia.add(ee[1]);
        esamiDolia.add(ee[9]);

        esamiDeriu.add(ee[1]);
        esamiDeriu.add(ee[9]);

        esamiBrotzu.add(ee[1]);
        esamiBrotzu.add(ee[2]);
        esamiBrotzu.add(ee[3]);
        esamiBrotzu.add(ee[4]);
        esamiBrotzu.add(ee[5]);
        esamiBrotzu.add(ee[6]);
        esamiBrotzu.add(ee[7]);
        esamiBrotzu.add(ee[8]);
        esamiBrotzu.add(ee[9]);
        esamiBrotzu.add(ee[10]);
        esamiBrotzu.add(ee[12]);
        esamiBrotzu.add(ee[14]);

        esamiT.add(ee[1]);
        esamiT.add(ee[2]);
        esamiT.add(ee[8]);
        esamiT.add(ee[9]);
        esamiT.add(ee[10]);
        esamiT.add(ee[12]);

        esamiASL.add(ee[1]);
        esamiASL.add(ee[4]);
        esamiASL.add(ee[12]);







        esami.add(new EsameStruttura("Ecografia Anse Intestinali", poliCash));
        esami.add(new EsameStruttura("MOC", tuttecos));
        esami.add(new EsameStruttura("Analisi Ematochimiche", poliBrotzT));
        esami.add(new EsameStruttura("Entero RM", poliBrotz));
        esami.add(new EsameStruttura("Visita Occulistica", poliASLBrotz));
        esami.add(new EsameStruttura("Gastroscopia", poliBrotz));
        esami.add(new EsameStruttura("Colonscopia con biopsia", poliBrotz));
        esami.add(new EsameStruttura("Colonscopia", poliBrotz));
        esami.add(new EsameStruttura("Visita Dermatologica", poliBrotzT));
        esami.add(new EsameStruttura("Ecocardiologia", poliCDDBT));
        esami.add(new EsameStruttura("Angiografia", bt));
        esami.add(new EsameStruttura("Breath Test", onlyPoli));
        esami.add(new EsameStruttura("Visita Allergologica", apbt));
        esami.add(new EsameStruttura("Polisonnografia", onlyPoli));
        esami.add(new EsameStruttura("Visita Proctologica", poliBrotz));





        struttue.add(new StrutturaEsame("Policlinico Universitario\nDulio Casula",esamiPoli));
        struttue.add(new StrutturaEsame("Studio Radiologico Casciu",esamiCash));
        struttue.add(new StrutturaEsame("Centro Diagnostico Dolianova",esamiDolia));
        struttue.add(new StrutturaEsame("Studio Radiologico Deriu",esamiDeriu));
        struttue.add(new StrutturaEsame("Ospedale G. Brotzu",esamiBrotzu));
        struttue.add(new StrutturaEsame("Ospedale Santissima Trinnità",esamiT));
        struttue.add(new StrutturaEsame("ASL Quartu",esamiASL));

        esami.sort(Comparator.comparing(EsameStruttura::getNomeEsame));
        struttue.sort(Comparator.comparing(StrutturaEsame::getDistanza));

    }


}

