package pl.parking.controller.reponses.errorResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponseLogin implements Serializable {

    private static final long serialVersionUID = 6067475683110150336L;

    @SerializedName("non_field_errors")
    @Expose
    private List<String> nonFieldErrors = new ArrayList<String>();

    public ErrorResponseLogin() {}

    public ErrorResponseLogin(List<String> nonFieldErrors) {
        super();
        this.nonFieldErrors = nonFieldErrors;
    }

    public List<String> getNonFieldErrors() {
        return nonFieldErrors;
    }

    public void setNonFieldErrors(List<String> nonFieldErrors) {
        this.nonFieldErrors = nonFieldErrors;
    }

    public ErrorResponseLogin withNonFieldErrors(List<String> nonFieldErrors) {
        this.nonFieldErrors = nonFieldErrors;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("nonFieldErrors", nonFieldErrors).toString();
    }
}
