package pl.parking.controller.reponses.successfulResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ResponseRegister implements Serializable {

    private static final long serialVersionUID = -3324575282999158285L;

    @SerializedName("key")
    @Expose
    private String key;

    /** No args constructor for use in serialization */
    public ResponseRegister() {}

    public ResponseRegister(String key) {
        super();
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ResponseRegister withKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("key", key).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(key).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResponseRegister) == false) {
            return false;
        }
        ResponseRegister rhs = ((ResponseRegister) other);
        return new EqualsBuilder().append(key, rhs.key).isEquals();
    }
}
