package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse;

import java.io.Serializable;



import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Response_Register implements Serializable
    {

        @SerializedName("key")
        @Expose
        private String key;
        private final static long serialVersionUID = -3324575282999158285L;

        /**
         * No args constructor for use in serialization
         *
         */
        public Response_Register() {
        }

        public Response_Register(String key) {
            super();
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Response_Register withKey(String key) {
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
            if ((other instanceof Response_Register) == false) {
                return false;
            }
            Response_Register rhs = ((Response_Register) other);
            return new EqualsBuilder().append(key, rhs.key).isEquals();
        }

    }


