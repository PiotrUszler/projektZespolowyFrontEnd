package com.example.shardo.projektzespolowy;

import java.io.Serializable;

/**
 * Created by Shardo on 27.04.2016.
 */
public class User implements Serializable{
    private String id;
    private String dataUtworzenia;
    private String dataModyfikacji;
    private String aktywny;
    private String email;
    private String imie;
    private String nazwisko;
    private String password;
    private String typUzytkownika;

    public String getId() {
        return id;
    }

    public String getDataUtworzenia() {
        return dataUtworzenia;
    }

    public String getDataModyfikacji() {
        return dataModyfikacji;
    }

    public String getAktywny() {
        return aktywny;
    }

    public String getEmail() {
        return email;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPassword() {
        return password;
    }

    public String getTypUzytkownika() {
        return typUzytkownika;
    }
}
