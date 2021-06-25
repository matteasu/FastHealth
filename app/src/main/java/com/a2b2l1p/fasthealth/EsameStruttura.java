package com.a2b2l1p.fasthealth;

import java.io.Serializable;
import java.util.ArrayList;

public class EsameStruttura implements Serializable {

    private String nomeEsame;
    private ArrayList<Strutture> s;

    public EsameStruttura (String nomeEsame,ArrayList<Strutture> s){
        this.nomeEsame=nomeEsame;
        this.s=s;
    }

    public ArrayList<Strutture> getS() {
        return s;
    }

    public String getNomeEsame() {
        return nomeEsame;
    }
}
