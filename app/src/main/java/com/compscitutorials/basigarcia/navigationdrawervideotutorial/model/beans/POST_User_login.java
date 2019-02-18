package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class POST_User_login {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    private final static long serialVersionUID = -4848011088301170296L;

    /**
     * No args constructor for use in serialization
     *
     */
    public POST_User_login() {
    }

    /**
     *
     * @param username
     * @param email
     * @param password
     */
    public POST_User_login(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public POST_User_login withUsername(String username) {
        this.username = username;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public POST_User_login withEmail(String email) {
        this.email = email;
        return this;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public POST_User_login withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("username", username).append("email", email).append("password", password).toString();
    }

}
