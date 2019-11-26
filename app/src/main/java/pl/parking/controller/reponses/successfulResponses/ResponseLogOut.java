package pl.parking.controller.reponses.successfulResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ResponseLogOut implements Serializable {

    private static final long serialVersionUID = -8198195640638335061L;

    @SerializedName("detail")
    @Expose
    private String detail;

    /** No args constructor for use in serialization */
    public ResponseLogOut() {}

    public ResponseLogOut(String detail) {
        super();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ResponseLogOut withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("detail", detail).toString();
    }
}
