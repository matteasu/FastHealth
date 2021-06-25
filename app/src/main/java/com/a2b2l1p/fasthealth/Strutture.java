package com.a2b2l1p.fasthealth;

import java.io.Serializable;
import java.util.Calendar;

public class Strutture implements Serializable {

    private String nome;
    //private float distanza;
    private Calendar c;
    private float costo;


    public Strutture (String nome){
        this.nome=nome;
        //distanza= (float) Math.floor(Math.random()*(10-0+1)+0);
        c=Calendar.getInstance();
        c.set(2021,(int)Math.floor(Math.random()*(12-0+1)+0),(int)Math.floor(Math.random()*(31-1+1)+1));
        costo =(float)Math.floor(Math.random()*(200-20+1)+20);

    }

    public String getNome() {
        return nome;
    }

    public Calendar getC(){return  c;}
    public float getCosto(){return costo;}

}
