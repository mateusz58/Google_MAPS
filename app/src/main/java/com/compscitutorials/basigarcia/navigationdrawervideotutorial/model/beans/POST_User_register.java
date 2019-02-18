package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;



import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class POST_User_register implements Serializable
{

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password1")
    @Expose
    private String password1;
    @SerializedName("password2")
    @Expose
    private String password2;
    private final static long serialVersionUID = -5758604465817457387L;

    /**
     * No args constructor for use in serialization
     *
     */
    public POST_User_register() {
    }

    /**
     *
     * @param password1
     * @param username
     * @param password2
     * @param email
     */
    public POST_User_register(String username, String email, String password1, String password2) {
        super();
        this.username = username;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public POST_User_register withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public POST_User_register withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public POST_User_register withPassword1(String password1) {
        this.password1 = password1;
        return this;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public POST_User_register withPassword2(String password2) {
        this.password2 = password2;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("username", username).append("email", email).append("password1", password1).append("password2", password2).toString();
    }

}