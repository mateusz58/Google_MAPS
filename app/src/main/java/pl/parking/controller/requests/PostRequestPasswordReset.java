package pl.parking.controller.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class PostRequestPasswordReset implements Serializable {

    private static final long serialVersionUID = -712166614907308678L;

    @SerializedName("email")
    @Expose
    private String email;

    /** No args constructor for use in serialization */
    public PostRequestPasswordReset() {}

    /** @param email */
    public PostRequestPasswordReset(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PostRequestPasswordReset withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("email", email).toString();
    }
}
