package com.example.shardo.projektzespolowy;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Shardo on 29.04.2016.
 */
public class Projekt implements Serializable{
    private Long id;
    private Date dataUtworzenia;
    private Date dataModyfikacji;
    private String nazwa;

    public Long getId() {
        return id;
    }

    public Date getDataUtworzenia() {
        return dataUtworzenia;
    }

    public Date getDataModyfikacji() {
        return dataModyfikacji;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public String getWersja() {
        return wersja;
    }

    public Set<User> getUsers() {
        return users;
    }

    private String opis;
    private String wersja;
    private Set<User> users;

}
