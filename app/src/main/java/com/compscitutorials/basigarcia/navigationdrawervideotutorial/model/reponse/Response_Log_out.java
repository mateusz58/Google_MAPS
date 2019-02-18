package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Response_Log_out implements Serializable
{

    @SerializedName("detail")
    @Expose
    private String detail;
    private final static long serialVersionUID = -8198195640638335061L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Response_Log_out() {
    }


    public Response_Log_out(String detail) {
        super();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Response_Log_out withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("detail", detail).toString();
    }

}