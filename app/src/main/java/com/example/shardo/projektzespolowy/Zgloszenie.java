package com.example.shardo.projektzespolowy;

import com.example.shardo.projektzespolowy.ENUMS.EStatusZgloszenia;
import com.example.shardo.projektzespolowy.ENUMS.EZgloszenieWewZew;
import com.example.shardo.projektzespolowy.ENUMS.ETypZgloszenia;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shardo on 04.06.2016.
 */
public class Zgloszenie implements Serializable{
    private Long id;
    private Date dataUtworzenia;
    private Date dataModyfikacji;
    private String opis;
    private Priorytet priorytet;
    private Projekt projekt;
    private EStatusZgloszenia status;
    private ETypZgloszenia typZgloszenia;
    private EZgloszenieWewZew zgloszenieWewZew;
    private User user;

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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Priorytet getPriorytet() {
        return priorytet;
    }

    public void setPriorytet(Priorytet priorytet) {
        this.priorytet = priorytet;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public EStatusZgloszenia getStatus() {
        return status;
    }

    public void setStatus(EStatusZgloszenia status) {
        this.status = status;
    }

    public ETypZgloszenia getTypZgloszenia() {
        return typZgloszenia;
    }

    public void setTypZgloszenia(ETypZgloszenia typZgloszenia) {
        this.typZgloszenia = typZgloszenia;
    }

    public EZgloszenieWewZew getZgloszenieWewZew() {
        return zgloszenieWewZew;
    }

    public void setZgloszenieWewZew(EZgloszenieWewZew zgloszenieWewZew) {
        this.zgloszenieWewZew = zgloszenieWewZew;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

