package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Car implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("Cost")
    @Expose
    private Double Cost;

    @SerializedName("Date_From")
    @Expose
    private String dateFrom;
    @SerializedName("Date_To")
    @Expose
    private String dateTo;
    @SerializedName("registration_plate")



    @Expose
    private String registrationPlate;
    @SerializedName("booking")
    @Expose
    private Integer booking;
    @SerializedName("status")
    @Expose
    private String status;
    private final static long serialVersionUID = 5825035112313312337L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Car() {
    }

    /**
     *
     * @param id
     * @param booking
     * @param status
     * @param dateTo
     * @param dateFrom
     * @param registrationPlate
     */
    public Car(Integer id, String dateFrom, String dateTo, String registrationPlate, Integer booking, String status,Double Cost) {
        super();
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.registrationPlate = registrationPlate;
        this.booking = booking;
        this.status = status;
        this.Cost=Cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Car withDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public String getDateTo() {
        return dateTo;
    }

    public Double getCost() {
        return Cost;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public void setCost(Double Cost) {
        this.Cost = Cost;
    }

    public Car withDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public Car withRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
        return this;
    }

    public Integer getBooking() {
        return booking;
    }

    public void setBooking(Integer booking) {
        this.booking = booking;
    }

    public Car withBooking(Integer booking) {
        this.booking = booking;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Car withStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("dateFrom", dateFrom).append("dateTo", dateTo).append("registrationPlate", registrationPlate).append("booking", booking).append("status", status).toString();
    }

}