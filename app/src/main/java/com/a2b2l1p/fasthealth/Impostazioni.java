package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Impostazioni extends AppCompatActivity {

    ImageView backBtn;
    TextView n,c;
    EditText nP,cnP;
    Button conf,logout;
    Utente u;
    ArrayList <Utente> utenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);
        utenti = (ArrayList<Utente>) getIntent().getSerializableExtra("lista");
        u=(Utente)getIntent().getSerializableExtra("utente");
        backBtn=findViewById(R.id.impostazioniIndietro);
        n=findViewById(R.id.impostazioniNome);
        c=findViewById(R.id.impostazioniCognome);
        nP=findViewById(R.id.impostazioniPsw);
        cnP=findViewById(R.id.impostazioniCpsw);
        conf=findViewById(R.id.impostazioniBCPS);
        logout=findViewById(R.id.impostazioniLogout);

        n.setText(u.getNome());
        c.setText(u.getCognome());
        backBtn.setOnClickListener(v->{
            Intent returnIntent=getIntent();
            returnIntent.putExtra("utente",u);
            returnIntent.putExtra("lista",utenti);
            returnIntent.putExtra("esci",false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
        conf.setOnClickListener(v->{
            if(verificaFrom()) {
                utenti.remove(u);
                u.setNome(u.getNome()+"MM");
                u.setPassword(nP.getText().toString());
                utenti.add(u);
                Context c = this.getApplicationContext();
                CharSequence t = "Password modificata";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(c, t, duration).show();
            }

        });
        logout.setOnClickListener(v->{
            Intent returnIntent=getIntent();
            returnIntent.putExtra("lista",utenti);
            returnIntent.putExtra("esci",true);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

    }


    protected boolean verificaFrom() {
        boolean err = true;

        if (nP.getText().length() == 0) {
            err = false;
            nP.setError("Password mancante");
        } else nP.setError(null);
        if (cnP.getText().length() == 0) {
            err = false;
            cnP.setError("Conferma della password mancante");
        } else cnP.setError(null);
        if (!cnP.getText().toString().equals(cnP.getText().toString())) {
            err = false;
            nP.setError("password non corrispondenti");
            cnP.setError("password non corrispondenti");
        }

        return err;
    }
}