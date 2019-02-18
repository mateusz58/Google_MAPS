package com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Error_Response_Token implements Serializable
{

    @SerializedName("detail")
    @Expose
    private String detail;
    private final static long serialVersionUID = -8198195640638335061L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Error_Response_Token() {
    }

    /**
     *
     * @param detail
     */
    public Error_Response_Token(String detail) {
        super();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Error_Response_Token withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("detail", detail).toString();
    }

}