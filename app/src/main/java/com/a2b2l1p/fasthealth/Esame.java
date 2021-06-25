package com.a2b2l1p.fasthealth;

import java.io.Serializable;
import java.util.Calendar;

public class Esame implements Serializable {

    private String nome;
    private Calendar data;
    private float costo;

    public Esame(String nome){
        this.nome=nome;
        this.costo=(float)Math.floor(Math.random()*(200-20+1)+20);
        data= Calendar.getInstance();
        data.set(2021,(int)Math.floor(Math.random()*(12-0+1)+0),(int)Math.floor(Math.random()*(31-1+1)+1));
    }

    public String getNome() {
        return nome;
    }
    public float getCosto(){return costo;}
    public Calendar getData() {
        return data;
    }
}
