package com.a2b2l1p.fasthealth;

import java.io.Serializable;
import java.util.Calendar;

public class Prenotazione implements Serializable {

    private String nomeEsame;
    private String nomeStruttura;
    private String nomeMedico;
    private String ora;
    private String codAcc;
    private String noteMed;
    private boolean pagato;
    private Calendar data;

    public Prenotazione(String nomeEsame, String nomeStruttura, String ora,  String codAcc, String nomeMedico, Calendar data) {
        this.nomeEsame = nomeEsame;
        this.nomeStruttura = nomeStruttura;
        this.ora = ora;
        this.codAcc = codAcc;
        this.data = data;
        this.pagato=false;
        this.nomeMedico=nomeMedico;
        this.noteMed="";
    }

    public Prenotazione(String nomeEsame, String nomeStruttura, String ora, String codAcc, Calendar data,String noteMed,String nomeMedico) {
        this.nomeEsame = nomeEsame;
        this.nomeStruttura = nomeStruttura;
        this.ora = ora;
        this.codAcc = codAcc;
        this.data = data;
        this.pagato=false;
        this.nomeMedico=nomeMedico;
        this.noteMed=noteMed;
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






}
