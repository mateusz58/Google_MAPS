package pl.parking.controller.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class PostRequestPasswordChange implements Serializable {

    private static final long serialVersionUID = -726094328609255889L;

    @SerializedName("new_password1")
    @Expose
    private String newPassword1;

    @SerializedName("new_password2")
    @Expose
    private String newPassword2;

    @SerializedName("old_password")
    @Expose
    private String oldPassword;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("newPassword1", newPassword1)
                .append("newPassword2", newPassword2)
                .append("oldPassword", oldPassword)
                .toString();
    }
}
