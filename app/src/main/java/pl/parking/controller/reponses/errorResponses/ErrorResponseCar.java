package pl.parking.controller.reponses.errorResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ErrorResponseCar implements Serializable {

    private static final long serialVersionUID = -3131191036942107228L;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public ErrorResponseCar() {}

    public ErrorResponseCar(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponseCar withErrorMessage(String errorMessage) {
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
        if ((other instanceof ErrorResponseCar) == false) {
            return false;
        }
        ErrorResponseCar rhs = ((ErrorResponseCar) other);
        return new EqualsBuilder().append(errorMessage, rhs.errorMessage).isEquals();
    }
}
