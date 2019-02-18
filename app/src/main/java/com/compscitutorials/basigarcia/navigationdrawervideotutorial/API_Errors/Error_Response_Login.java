package com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Error_Response_Login implements Serializable
{

    @SerializedName("non_field_errors")
    @Expose
    private List<String> nonFieldErrors = new ArrayList<String>();
    private final static long serialVersionUID = 6067475683110150336L;


    public Error_Response_Login() {
    }

    public Error_Response_Login(List<String> nonFieldErrors) {
        super();
        this.nonFieldErrors = nonFieldErrors;
    }

    public List<String> getNonFieldErrors() {
        return nonFieldErrors;
    }

    public void setNonFieldErrors(List<String> nonFieldErrors) {
        this.nonFieldErrors = nonFieldErrors;
    }

    public Error_Response_Login withNonFieldErrors(List<String> nonFieldErrors) {
        this.nonFieldErrors = nonFieldErrors;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("nonFieldErrors", nonFieldErrors).toString();
    }

}
