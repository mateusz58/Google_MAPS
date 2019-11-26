package pl.parking.controller.reponses.errorResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponsePasswordChange implements Serializable {

    private static final long serialVersionUID = 8276204281212852408L;

    @SerializedName("new_password1")
    @Expose
    private List<String> newPassword1 = new ArrayList<String>();

    /** No args constructor for use in serialization */
    public ErrorResponsePasswordChange() {}

    /** @param newPassword1 */
    public ErrorResponsePasswordChange(List<String> newPassword1) {
        super();
        this.newPassword1 = newPassword1;
    }

    public List<String> getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(List<String> newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public ErrorResponsePasswordChange withNewPassword1(List<String> newPassword1) {
        this.newPassword1 = newPassword1;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("newPassword1", newPassword1).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(newPassword1).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorResponsePasswordChange) == false) {
            return false;
        }
        ErrorResponsePasswordChange rhs = ((ErrorResponsePasswordChange) other);
        return new EqualsBuilder().append(newPassword1, rhs.newPassword1).isEquals();
    }
}
