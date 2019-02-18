package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Response_Password_reset implements Serializable
{

    @SerializedName("detail")
    @Expose
    private String detail;
    private final static long serialVersionUID = -8198195640638335061L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Response_Password_reset() {
    }

    /**
     *
     * @param detail
     */
    public Response_Password_reset(String detail) {
        super();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Response_Password_reset withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("detail", detail).toString();
    }

}