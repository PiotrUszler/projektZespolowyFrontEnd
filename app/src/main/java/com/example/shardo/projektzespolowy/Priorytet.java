package com.example.shardo.projektzespolowy;

import com.example.shardo.projektzespolowy.ENUMS.EJednostkaCzasu;
import com.example.shardo.projektzespolowy.ENUMS.ETypPriorytetu;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by Shardo on 04.06.2016.
 */
public class Priorytet implements Serializable{
    private Long id;
    private Date dataUtworzenia;
    private Date dataModyfikacji;
    private ETypPriorytetu typ;
    private EJednostkaCzasu jednostka;
    private int ilosc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Date dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public Date getDataModyfikacji() {
        return dataModyfikacji;
    }

    public void setDataModyfikacji(Date dataModyfikacji) {
        this.dataModyfikacji = dataModyfikacji;
    }

    public ETypPriorytetu getTyp() {
        return typ;
    }

    public void setTyp(ETypPriorytetu typ) {
        this.typ = typ;
    }

    public EJednostkaCzasu getJednostka() {
        return jednostka;
    }

    public void setJednostka(EJednostkaCzasu jednostka) {
        this.jednostka = jednostka;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
