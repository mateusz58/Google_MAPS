package pl.parking.controller.reponses.errorResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponseRegisterUser implements Serializable {

    private static final long serialVersionUID = 4353713472948791931L;

    @SerializedName("username")
    @Expose
    private List<String> username = new ArrayList<String>();

    @SerializedName("email")
    @Expose
    private List<String> email = new ArrayList<String>();

    public ErrorResponseRegisterUser(List<String> username, List<String> email) {
        super();
        this.username = username;
        this.email = email;
    }

    public ErrorResponseRegisterUser() {}

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("email", email)
                .toString();
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
        if ((other instanceof ErrorResponseRegisterUser) == false) {
            return false;
        }
        ErrorResponseRegisterUser rhs = ((ErrorResponseRegisterUser) other);
        return new EqualsBuilder()
                .append(username, rhs.username)
                .append(email, rhs.email)
                .isEquals();
    }
}
