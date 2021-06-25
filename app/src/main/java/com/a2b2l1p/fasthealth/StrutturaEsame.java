package com.a2b2l1p.fasthealth;

import java.io.Serializable;
import java.util.ArrayList;

public class StrutturaEsame implements Serializable {

    private String nomeStruttura;
    private ArrayList<Esame> e;
    private float distanza;
    public StrutturaEsame(String nomeStruttura, ArrayList<Esame> e){
        this.nomeStruttura=nomeStruttura;
        this.e=e;
        this.distanza = (float) Math.floor(Math.random()*(10-1+1)+1);
    }

    public ArrayList<Esame> getE() {
        return e;
    }

    public String getNomeStruttura() {
        return nomeStruttura;
    }
    public float getDistanza(){return distanza;}
}
