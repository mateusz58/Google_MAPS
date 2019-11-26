package pl.parking.controller.reponses.successfulResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ResponseLogin implements Serializable {

    private static final long serialVersionUID = 4848677714961413104L;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("email")
    @Expose
    private String email;

    public ResponseLogin() {}

    public ResponseLogin(String token, Integer userId, String email) {
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

    public ResponseLogin withToken(String token) {
        this.token = token;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ResponseLogin withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResponseLogin withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("token", token)
                .append("userId", userId)
                .append("email", email)
                .toString();
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
        if ((other instanceof ResponseLogin) == false) {
            return false;
        }
        ResponseLogin rhs = ((ResponseLogin) other);
        return new EqualsBuilder()
                .append(email, rhs.email)
                .append(token, rhs.token)
                .append(userId, rhs.userId)
                .isEquals();
    }
}
