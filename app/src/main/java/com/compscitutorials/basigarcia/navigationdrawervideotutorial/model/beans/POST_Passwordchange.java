package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class POST_Passwordchange implements Serializable
{

    @SerializedName("new_password1")
    @Expose
    private String newPassword1;
    @SerializedName("new_password2")
    @Expose
    private String newPassword2;
    @SerializedName("old_password")
    @Expose
    private String oldPassword;
    private final static long serialVersionUID = -726094328609255889L;

    /**
     * No args constructor for use in serialization
     *
     */
    public POST_Passwordchange() {
    }

    /**
     *
     * @param newPassword2
     * @param newPassword1
     * @param oldPassword
     */
    public POST_Passwordchange(String newPassword1, String newPassword2, String oldPassword) {
        super();
        this.newPassword1 = newPassword1;
        this.newPassword2 = newPassword2;
        this.oldPassword = oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public POST_Passwordchange withNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
        return this;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public POST_Passwordchange withNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
        return this;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public POST_Passwordchange withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("newPassword1", newPassword1).append("newPassword2", newPassword2).append("oldPassword", oldPassword).toString();
    }

}
