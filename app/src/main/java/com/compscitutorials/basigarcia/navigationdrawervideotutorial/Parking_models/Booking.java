package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "parking",
        "Date_From",
        "Date_To",
        "Cost",
        "user",
        "registration_plate"
})
public class Booking implements Serializable {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("parking")
    private Integer parking;
    @JsonProperty("Date_From")
    private String dateFrom;
    @JsonProperty("Date_To")
    private String dateTo;
    @JsonProperty("Cost")
    private Float cost;
    @JsonProperty("user")
    private Integer user;
    @JsonProperty("registration_plate")
    private String registrationPlate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6561442664912166462L;

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("parking")
    public Integer getParking() {
        return parking;
    }

    @JsonProperty("parking")
    public void setParking(Integer parking) {
        this.parking = parking;
    }

    @JsonProperty("Date_From")
    public String getDateFrom() {
        return dateFrom;
    }

    @JsonProperty("Date_From")
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    @JsonProperty("Date_To")
    public String getDateTo() {
        return dateTo;
    }

    @JsonProperty("Date_To")
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @JsonProperty("Cost")
    public Float getCost() {
        return cost;
    }

    @JsonProperty("Cost")
    public void setCost(Float cost) {
        this.cost = cost;
    }

    @JsonProperty("user")
    public Integer getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(Integer user) {
        this.user = user;
    }

    @JsonProperty("registration_plate")
    public String getRegistrationPlate() {
        return registrationPlate;
    }

    @JsonProperty("registration_plate")
    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
