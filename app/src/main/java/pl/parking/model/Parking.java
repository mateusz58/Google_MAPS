package pl.parking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Parking implements Serializable {

    private static final long serialVersionUID = -6792314033556052338L;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("parking_name")
    @Expose
    private String parkingName;

    @SerializedName("parking_Street")
    @Expose
    private String parkingStreet;

    @SerializedName("parking_City")
    @Expose
    private String parkingCity;

    @SerializedName("x")
    @Expose
    private Double x;

    @SerializedName("y")
    @Expose
    private Double y;

    @SerializedName("free_places")
    @Expose
    private Integer freePlaces;

    @SerializedName("HOUR_COST")
    @Expose
    private Double hOURCOST;

    @SerializedName("number_of_places")
    @Expose
    private Integer numberOfPlaces;

    @SerializedName("user_parking")
    @Expose
    private Integer userParking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parking withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public Parking withParkingName(String parkingName) {
        this.parkingName = parkingName;
        return this;
    }

    public String getParkingStreet() {
        return parkingStreet;
    }

    public void setParkingStreet(String parkingStreet) {
        this.parkingStreet = parkingStreet;
    }

    public Parking withParkingStreet(String parkingStreet) {
        this.parkingStreet = parkingStreet;
        return this;
    }

    public String getParkingCity() {
        return parkingCity;
    }

    public void setParkingCity(String parkingCity) {
        this.parkingCity = parkingCity;
    }

    public Parking withParkingCity(String parkingCity) {
        this.parkingCity = parkingCity;
        return this;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Parking withX(Double x) {
        this.x = x;
        return this;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Parking withY(Double y) {
        this.y = y;
        return this;
    }

    public Integer getFreePlaces() {
        return freePlaces;
    }

    public Double getHourCost() {
        return hOURCOST;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("parkingName", parkingName)
                .append("parkingStreet", parkingStreet)
                .append("parkingCity", parkingCity)
                .append("x", x)
                .append("y", y)
                .append("freePlaces", freePlaces)
                .append("hourCost", hOURCOST)
                .append("numberOfPlaces", numberOfPlaces)
                .append("userParking", userParking)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(parkingStreet)
                .append(id)
                .append(hOURCOST)
                .append(parkingName)
                .append(parkingCity)
                .append(userParking)
                .append(numberOfPlaces)
                .append(freePlaces)
                .append(y)
                .append(x)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Parking) == false) {
            return false;
        }
        Parking rhs = ((Parking) other);
        return new EqualsBuilder()
                .append(parkingStreet, rhs.parkingStreet)
                .append(id, rhs.id)
                .append(hOURCOST, rhs.hOURCOST)
                .append(parkingName, rhs.parkingName)
                .append(parkingCity, rhs.parkingCity)
                .append(userParking, rhs.userParking)
                .append(numberOfPlaces, rhs.numberOfPlaces)
                .append(freePlaces, rhs.freePlaces)
                .append(y, rhs.y)
                .append(x, rhs.x)
                .isEquals();
    }
}
