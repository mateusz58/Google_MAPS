package pl.parking.controller.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class PostRequestUserLogOut implements Serializable {

    private static final long serialVersionUID = -5240498501997083700L;

    @SerializedName("detail")
    @Expose
    private String detail;

    /** No args constructor for use in serialization */
    public PostRequestUserLogOut() {}

    /** @param detail */
    public PostRequestUserLogOut(String detail) {
        super();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public PostRequestUserLogOut withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("detail", detail).toString();
    }
}
