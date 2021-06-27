package com.a2b2l1p.fasthealth;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

public class Prenotazione implements Serializable {

    private String nomeEsame;
    private String nomeStruttura;
    private String nomeMedico;
    private int persCoda;
    private String ora;
    private float costo;
    private String codAcc;
    private String noteMed;
    private boolean pagato;
    private Calendar data;




    public Prenotazione(Prenotazione p ){
        this.nomeEsame=p.getNomeEsame();
        this.nomeStruttura=p.getNomeStruttura();
        this.nomeMedico=p.getNomeMedico();
        this.persCoda=p.getPersCoda();
        this.ora=p.getOra();
        this.costo=p.getCosto();
        this.codAcc=p.getCodAcc();
        this.noteMed=p.getNoteMed();
        this.pagato=p.isPagato();
        this.data=p.getData();
    }


    public Prenotazione(String nomeEsame, String nomeStruttura, String ora,  String codAcc, String nomeMedico, Calendar data,float costo) {
        this.nomeEsame = nomeEsame;
        this.nomeStruttura = nomeStruttura;
        this.ora = ora;
        this.codAcc = codAcc;
        this.data = data;
        this.pagato=false;
        this.costo=costo;
        this.nomeMedico=nomeMedico;
        this.noteMed="";
        this.persCoda= (int) Math.floor(Math.random()*(10-0+1)+0);
    }

    public Prenotazione(String nomeEsame, String nomeStruttura, String ora, String codAcc, Calendar data,String noteMed,String nomeMedico,float costo) {
        this.nomeEsame = nomeEsame;
        this.nomeStruttura = nomeStruttura;
        this.ora = ora;
        this.codAcc = codAcc;
        this.data = data;
        this.costo=costo;
        this.pagato=false;
        this.nomeMedico=nomeMedico;
        this.noteMed=noteMed;
        this.persCoda= (int) Math.floor(Math.random()*(10-0+1)+0);
    }

    public String getNomeMedico(){return nomeMedico;}
    public void  setNomeMedico(String nome){this.nomeMedico=nome;}

    public String getNomeEsame() {
        return nomeEsame;
    }

    public void setNomeEsame(String nomeEsame) {
        this.nomeEsame = nomeEsame;
    }

    public String getNomeStruttura() {
        return nomeStruttura;
    }

    public void setNomeStruttura(String nomeStruttura) {
        this.nomeStruttura = nomeStruttura;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getCodAcc() {
        return codAcc;
    }

    public void setCodAcc(String codAcc) {
        this.codAcc = codAcc;
    }

    public String getNoteMed() {
        return noteMed;
    }

    public void setNoteMed(String noteMed) {
        this.noteMed = noteMed;
    }

    public boolean isPagato() {
        return pagato;
    }

    public void setPagato(boolean pagato) {
        this.pagato = pagato;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public float getCosto(){return costo;}

    public void setCosto(float costo){this.costo=costo;}

    public int getPersCoda(){return persCoda;}

    @Override
    public boolean equals(Object u){
        boolean r=false;
        if(u!=null){
            if(u instanceof Prenotazione){
                Prenotazione uC=(Prenotazione) u;
                if(this.getNomeEsame().equals(uC.getNomeEsame())){
                    if(this.getNomeStruttura().equals(uC.getNomeStruttura())){
                        r=true;
                    }
                }
            }
        }

        return r;
    }




}
