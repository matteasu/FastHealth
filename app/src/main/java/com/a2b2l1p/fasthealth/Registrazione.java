package com.a2b2l1p.fasthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Registrazione extends AppCompatActivity {

    EditText Nome, Cognome, Username, Password, Cpassword, CF;
    Button confReg;
    ArrayList<Utente> utenti;
    Utente n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        utenti = (ArrayList<Utente>) getIntent().getSerializableExtra("lista");
        Nome = findViewById(R.id.regNome);
        Cognome = findViewById(R.id.regCognome);
        Username = findViewById(R.id.regUsername);
        Password = findViewById(R.id.regPassword);
        Cpassword = findViewById(R.id.regCpassword);
        CF = findViewById(R.id.regCF);
        confReg = findViewById(R.id.regButton);

        confReg.setOnClickListener(v -> {
            if (verificaFrom()) {
                n = new Utente(Nome.getText().toString(), Cognome.getText().toString(),
                        Username.getText().toString(), Password.getText().toString(), CF.getText().toString());
                utenti.add(n);
                Intent returnIntent=getIntent();
                returnIntent.putExtra("lista",utenti);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

    }

    protected boolean verificaFrom() {
        boolean err = true;
        if (Nome.getText().length() == 0) {
            Nome.setError("Nome mancante");
            err = false;
        } else Nome.setError(null);

        if (Cognome.getText().length() == 0) {
            Cognome.setError("Cognome mancante");
            err = false;
        } else Cognome.setError(null);
        if (Username.getText().length() == 0) {
            Username.setError("Username mancante");
            err = false;
        } else Username.setError(null);
        if (Password.getText().length() == 0) {
            err = false;
            Password.setError("Password mancante");
        } else Password.setError(null);
        if (Cpassword.getText().length() == 0) {
            err = false;
            Cpassword.setError("Conferma della password mancante");
        } else Cpassword.setError(null);
        if (!Password.getText().toString().equals(Cpassword.getText().toString())) {
            err = false;
            Password.setError("password non corrispondenti");
            Cpassword.setError("password non corrispondenti");
        }
        if (CF.getText().length() == 0) {
            CF.setError("Codice Fiscale mancante");
            err = false;
        } else {
            if (CF.getText().length() < 16) {
                CF.setError("Codice Fiscale troppo corto");
                err = false;
            } else if (CF.getText().length() > 16) {
                CF.setError("Codice Fiscale troppo lungo");
                err = false;
            } else CF.setError(null);
        }
        return err;
    }
}