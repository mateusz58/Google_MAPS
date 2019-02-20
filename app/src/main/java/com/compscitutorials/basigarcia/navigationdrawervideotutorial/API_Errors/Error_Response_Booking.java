package com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors;


import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Error_Response_Booking extends Throwable implements Serializable
{

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    private final static long serialVersionUID = -3131191036942107228L;

    public Error_Response_Booking() {
    }

    public Error_Response_Booking(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error_Response_Booking withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("errorMessage", errorMessage).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(errorMessage).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Error_Response_Booking) == false) {
            return false;
        }
        Error_Response_Booking rhs = ((Error_Response_Booking) other);
        return new EqualsBuilder().append(errorMessage, rhs.errorMessage).isEquals();
    }

}