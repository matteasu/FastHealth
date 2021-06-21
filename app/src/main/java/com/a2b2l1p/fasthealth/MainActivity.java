package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button loginB;
    TextView register;
    ArrayList<Utente> utenti =new ArrayList<>();
    Utente uLog;
    String usr,psw;
    int loginRegistrazione=1;
    int loginHome=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //utenti.add(new Utente("m","a","admin","a","aa"));
        //recupero elementi view
        username=findViewById(R.id.loginUsername);
        password=findViewById(R.id.loginPassword);
        loginB=findViewById(R.id.loginButton);
        register=findViewById(R.id.loginRegister);

        register.setOnClickListener(v->{
            Intent registrazione = new Intent(MainActivity.this,Registrazione.class);
            registrazione.putExtra("lista",utenti);
            startActivityForResult(registrazione,loginRegistrazione);
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_main);
        //recupero dei dati aggiornati
        if (requestCode == loginRegistrazione || requestCode == loginHome) {
            if (resultCode == Activity.RESULT_OK) {
                utenti = (ArrayList<Utente>) data.getSerializableExtra("lista");
            }

        }

        username=findViewById(R.id.loginUsername);
        password=findViewById(R.id.loginPassword);
        loginB=findViewById(R.id.loginButton);
        register=findViewById(R.id.loginRegister);


        login();


        register.setOnClickListener(v->{
            Intent registrazione = new Intent(MainActivity.this,Registrazione.class);
            registrazione.putExtra("lista",utenti);
            startActivityForResult(registrazione,loginRegistrazione);
        });

    }//onActivityResult

    /**
     * funzione per la verifica dei dati nel form di login
     *
     * @return true in caso di successo, false in caso di campi mancanti
     */
    public boolean verificaLogin() {
        boolean err = true;
        if (username.getText().length() == 0) {
            username.setError("Username mancante");
            err = false;
        } else {
            username.setError(null);
        }
        if (password.getText().length() == 0) {
            err = false;
            password.setError("Password mancante");
        } else {
            password.setError(null);
        }
        return err;
    }

    /**
     * funzione per il recupero di un utente dalla lista degli utenti
     *
     * @param usr username da cercare
     * @param psw relativa password
     * @return utente associato alle credenziali, altrimenti null
     */
    public Utente recuperaUtente(String usr, String psw) {
        boolean trovato = false;
        boolean errPSW = false;
        Utente app, ret;
        ret = null;
        Iterator<Utente> iU = utenti.iterator();

        while (iU.hasNext() && !trovato) {
            if ((app = iU.next()) != null) {

                if (app.getUsername().equals(usr)) {
                    trovato = true;
                    if (app.getPassword().equals(psw)) {
                        ret = app;
                    } else errPSW = true;
                }
            }
        }


        if (!trovato) {
            username.setError("utente inesistente");
        } else if (trovato && errPSW) {
            password.setError("password sbagliata");
        }

        return ret;
    }
    void login(){
        loginB.setOnClickListener(v->{
            if (verificaLogin()) {
                usr = username.getText().toString();
                psw = password.getText().toString();
                if ((uLog = recuperaUtente(usr, psw)) != null) {
                    Intent showResult = new Intent(MainActivity.this, Home.class);
                    showResult.putExtra("utente", uLog);
                    showResult.putExtra("lista", utenti);
                    startActivityForResult(showResult, loginHome);
                }
            }
        });
    }
}