package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


public class POST_PasswordReset implements Serializable
    {

        @SerializedName("email")
        @Expose
        private String email;
        private final static long serialVersionUID = -712166614907308678L;

        /**
         * No args constructor for use in serialization
         *
         */
        public POST_PasswordReset() {
        }

        /**
         *
         * @param email
         */
        public POST_PasswordReset(String email) {
            super();
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public POST_PasswordReset withEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("email", email).toString();
        }

    }

