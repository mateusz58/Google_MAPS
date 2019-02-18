package com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Error_Response_Car implements Serializable
{

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    private final static long serialVersionUID = -3131191036942107228L;

    public Error_Response_Car() {
    }

    public Error_Response_Car(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error_Response_Car withErrorMessage(String errorMessage) {
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
        if ((other instanceof Error_Response_Car) == false) {
            return false;
        }
        Error_Response_Car rhs = ((Error_Response_Car) other);
        return new EqualsBuilder().append(errorMessage, rhs.errorMessage).isEquals();
    }

}