package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

/**
 * Created by Admin on 2016-03-18.
 */
public class User {

    public long userId;
    public String username;
    public String password;
     public String imie;
    public String email;
    public String nazwisko;


/**
 * Set nazwisko.
 *
 * @param n Nazwisko
 */
public final void setNazwisko(final java.lang.String n) {
    nazwisko = n;
}
    /**
     * Set imie.
     *
     * @param i Imie
     */
    public final void setImie(final java.lang.String i) {
        imie = i;
    }
    /**
     * Get imie.
     *
     * @return Imie
     */
    public final java.lang.String getImie() {
        return imie;
    }

/**
 * Get nazwisko.
 *
 * @return Nazwisko
 */
public final java.lang.String getNazwisko() {
    return nazwisko;
}

    public User(){}

    public User(long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }


    public final void setUserId(final long u) {
        userId = u;
    }

    public final Long getUserId() {
        return userId;
    }

    public final void setUsername(final java.lang.String u) {
        username = u;
    }

    public final java.lang.String getUsername() {
        return username;
    }

    public final void setPassword(final java.lang.String p) {
        password = p;
    }

    public final java.lang.String getPassword() {
        return password;
    }


}
