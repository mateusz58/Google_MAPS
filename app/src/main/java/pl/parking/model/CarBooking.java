package pl.parking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;

public class CarBooking implements Serializable {

    private static final long serialVersionUID = -7681549569650921491L;

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
    private Integer user;

    @SerializedName("number_of_cars")
    @Expose
    private Integer numberOfCars;

    @SerializedName("booking")
    @Expose
    private ArrayList<Car> booking = new ArrayList<Car>();

    @SerializedName("Date_From")
    @Expose
    private String dateFrom;

    @SerializedName("Date_To")
    @Expose
    private String dateTo;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("Parking_name")
    @Expose
    private String parkingName;

    @SerializedName("Parking_street")
    @Expose
    private String parkingStreet;

    @SerializedName("Parking_city")
    @Expose
    private String parkingCity;

    public CarBooking() {}

    public CarBooking(
            Integer id,
            Integer parking,
            Float cost,
            Integer user,
            Integer numberOfCars,
            ArrayList<Car> booking,
            String dateFrom,
            String dateTo,
            Boolean active,
            String parkingName,
            String parkingStreet,
            String parkingCity) {
        super();
        this.id = id;
        this.parking = parking;
        this.cost = cost;
        this.user = user;
        this.numberOfCars = numberOfCars;
        this.booking = booking;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.active = active;
        this.parkingName = parkingName;
        this.parkingStreet = parkingStreet;
        this.parkingCity = parkingCity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CarBooking withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public CarBooking withParking(Integer parking) {
        this.parking = parking;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public CarBooking withCost(Float cost) {
        this.cost = cost;
        return this;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public CarBooking withUser(Integer user) {
        this.user = user;
        return this;
    }

    public Integer getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(Integer numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public CarBooking withNumberOfCars(Integer numberOfCars) {
        this.numberOfCars = numberOfCars;
        return this;
    }

    public ArrayList<Car> getBooking() {
        return booking;
    }

    public void setBooking(ArrayList<Car> booking) {
        this.booking = booking;
    }

    public CarBooking withBooking(ArrayList<Car> booking) {
        this.booking = booking;
        return this;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public CarBooking withDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public CarBooking withDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CarBooking withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public CarBooking withParkingName(String parkingName) {
        this.parkingName = parkingName;
        return this;
    }

    public String getParkingStreet() {
        return parkingStreet;
    }

    public void setParkingStreet(String parkingStreet) {
        this.parkingStreet = parkingStreet;
    }

    public CarBooking withParkingStreet(String parkingStreet) {
        this.parkingStreet = parkingStreet;
        return this;
    }

    public String getParkingCity() {
        return parkingCity;
    }

    public void setParkingCity(String parkingCity) {
        this.parkingCity = parkingCity;
    }

    public CarBooking withParkingCity(String parkingCity) {
        this.parkingCity = parkingCity;
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
                .append("booking", booking)
                .append("dateFrom", dateFrom)
                .append("dateTo", dateTo)
                .append("active", active)
                .append("parkingName", parkingName)
                .append("parkingStreet", parkingStreet)
                .append("parkingCity", parkingCity)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(parkingStreet)
                .append(parking)
                .append(booking)
                .append(parkingName)
                .append(parkingCity)
                .append(dateTo)
                .append(cost)
                .append(id)
                .append(numberOfCars)
                .append(active)
                .append(dateFrom)
                .append(user)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CarBooking) == false) {
            return false;
        }
        CarBooking rhs = ((CarBooking) other);
        return new EqualsBuilder()
                .append(parkingStreet, rhs.parkingStreet)
                .append(parking, rhs.parking)
                .append(booking, rhs.booking)
                .append(parkingName, rhs.parkingName)
                .append(parkingCity, rhs.parkingCity)
                .append(dateTo, rhs.dateTo)
                .append(cost, rhs.cost)
                .append(id, rhs.id)
                .append(numberOfCars, rhs.numberOfCars)
                .append(active, rhs.active)
                .append(dateFrom, rhs.dateFrom)
                .append(user, rhs.user)
                .isEquals();
    }
}
