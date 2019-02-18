package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse;



import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Response_Login implements Serializable
{

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("email")
    @Expose
    private String email;
    private final static long serialVersionUID = 4848677714961413104L;
    /**
     * No args constructor for use in serialization
     *
     */
    public Response_Login() {
    }

    /**
     *
     * @param email
     * @param token
     * @param userId
     */
    public Response_Login(String token, Integer userId, String email) {
        super();
        this.token = token;
        this.userId = userId;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Response_Login withToken(String token) {
        this.token = token;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Response_Login withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Response_Login withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("userId", userId).append("email", email).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(email).append(token).append(userId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Response_Login) == false) {
            return false;
        }
        Response_Login rhs = ((Response_Login) other);
        return new EqualsBuilder().append(email, rhs.email).append(token, rhs.token).append(userId, rhs.userId).isEquals();
    }
}
