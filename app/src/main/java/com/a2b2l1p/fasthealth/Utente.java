package com.a2b2l1p.fasthealth;

import java.io.Serializable;

public class Utente implements Serializable {

    private String Nome;
    private String Cognome;
    private String Username;
    private String Password;
    private String CF;
    public Utente(String nome, String cognome, String username, String password, String CF) {
        Nome = nome;
        Cognome = cognome;
        Username = username;
        Password = password;
        this.CF = CF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }
    @Override
    public boolean equals(Object u){
        boolean r=false;
        if(u!=null){
            if(u instanceof Utente){
                Utente uC=(Utente)u;
                if(this.getUsername().equals(uC.getUsername())){
                    if(this.getPassword().equals(uC.getPassword())){
                        r=true;
                    }
                }
            }
        }

        return r;
    }
}
