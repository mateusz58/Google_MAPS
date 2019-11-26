package pl.parking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Booking implements Serializable {

    private static final long serialVersionUID = 6909003807465914184L;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("parking")
    @Expose
    private Integer parking;

    @SerializedName("Cost")
    @Expose
    private Float cost;

    @SerializedName("user")
    @Expose
    private Object user;

    @SerializedName("number_of_cars")
    @Expose
    private Integer numberOfCars;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("Date_From")
    @Expose
    private String dateFrom;

    @SerializedName("Date_To")
    @Expose
    private String dateTo;

    public Booking() {}

    public Booking(
            Integer id,
            Integer parking,
            Float cost,
            Object user,
            Integer numberOfCars,
            Boolean active,
            String dateFrom,
            String dateTo) {
        super();
        this.id = id;
        this.parking = parking;
        this.cost = cost;
        this.user = user;
        this.numberOfCars = numberOfCars;
        this.active = active;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Booking withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public Booking withParking(Integer parking) {
        this.parking = parking;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Booking withCost(Float cost) {
        this.cost = cost;
        return this;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Booking withUser(Object user) {
        this.user = user;
        return this;
    }

    public Integer getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(Integer numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public Booking withNumberOfCars(Integer numberOfCars) {
        this.numberOfCars = numberOfCars;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Booking withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Booking withDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Booking withDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("parking", parking)
                .append("cost", cost)
                .append("user", user)
                .append("numberOfCars", numberOfCars)
                .append("active", active)
                .append("dateFrom", dateFrom)
                .append("dateTo", dateTo)
                .toString();
    }
}
