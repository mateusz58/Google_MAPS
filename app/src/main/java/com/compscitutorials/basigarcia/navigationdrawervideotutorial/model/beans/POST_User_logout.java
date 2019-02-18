package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;



import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class POST_User_logout implements Serializable
{

    @SerializedName("detail")
    @Expose
    private String detail;
    private final static long serialVersionUID = -5240498501997083700L;

    /**
     * No args constructor for use in serialization
     *
     */
    public POST_User_logout() {
    }

    /**
     *
     * @param detail
     */
    public POST_User_logout(String detail) {
        super();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public POST_User_logout withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("detail", detail).toString();
    }

}