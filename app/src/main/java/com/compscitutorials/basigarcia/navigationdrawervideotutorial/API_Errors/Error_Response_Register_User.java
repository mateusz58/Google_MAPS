package com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Error_Response_Register_User implements Serializable
{

    @SerializedName("username")
    @Expose
    private List<String> username = new ArrayList<String>();
    @SerializedName("email")
    @Expose
    private List<String> email = new ArrayList<String>();
    private final static long serialVersionUID = 4353713472948791931L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Error_Response_Register_User() {
    }

    /**
     *
     * @param username
     * @param email
     */
    public Error_Response_Register_User(List<String> username, List<String> email) {
        super();
        this.username = username;
        this.email = email;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public Error_Response_Register_User withUsername(List<String> username) {
        this.username = username;
        return this;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public Error_Response_Register_User withEmail(List<String> email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("username", username).append("email", email).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(username).append(email).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Error_Response_Register_User) == false) {
            return false;
        }
        Error_Response_Register_User rhs = ((Error_Response_Register_User) other);
        return new EqualsBuilder().append(username, rhs.username).append(email, rhs.email).isEquals();
    }

}