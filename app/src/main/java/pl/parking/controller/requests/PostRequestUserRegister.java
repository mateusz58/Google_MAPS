package pl.parking.controller.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class PostRequestUserRegister implements Serializable {

    private static final long serialVersionUID = -5758604465817457387L;

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

    public PostRequestUserRegister() {}

    public PostRequestUserRegister(
            String username, String email, String password1, String password2) {
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

    public PostRequestUserRegister withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PostRequestUserRegister withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public PostRequestUserRegister withPassword1(String password1) {
        this.password1 = password1;
        return this;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public PostRequestUserRegister withPassword2(String password2) {
        this.password2 = password2;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("email", email)
                .append("password1", password1)
                .append("password2", password2)
                .toString();
    }
}
