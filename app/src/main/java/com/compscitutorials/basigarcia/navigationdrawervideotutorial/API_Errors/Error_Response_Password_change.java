package com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Error_Response_Password_change implements Serializable
{

    @SerializedName("new_password1")
    @Expose
    private List<String> newPassword1 = new ArrayList<String>();
    private final static long serialVersionUID = 8276204281212852408L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Error_Response_Password_change() {
    }

    /**
     *
     * @param newPassword1
     */
    public Error_Response_Password_change(List<String> newPassword1) {
        super();
        this.newPassword1 = newPassword1;
    }

    public List<String> getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(List<String> newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public Error_Response_Password_change withNewPassword1(List<String> newPassword1) {
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
        if ((other instanceof Error_Response_Password_change) == false) {
            return false;
        }
        Error_Response_Password_change rhs = ((Error_Response_Password_change) other);
        return new EqualsBuilder().append(newPassword1, rhs.newPassword1).isEquals();
    }

}
