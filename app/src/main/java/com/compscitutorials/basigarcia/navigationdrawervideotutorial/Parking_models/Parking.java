
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
        "id",
        "parking_name",
        "parking_Street",
        "parking_City",
        "x",
        "y",
        "free_places"
})
public class Parking implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("parking_name")
    private String parkingName;
    @JsonProperty("parking_Street")
    private String parkingStreet;
    @JsonProperty("parking_City")
    private String parkingCity;
    @JsonProperty("x")
    private Float x;
    @JsonProperty("y")
    private Float y;
    @JsonProperty("free_places")
    private Integer freePlaces;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3769811512371374582L;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("parking_name")
    public String getParkingName() {
        return parkingName;
    }

    @JsonProperty("parking_name")
    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    @JsonProperty("parking_Street")
    public String getParkingStreet() {
        return parkingStreet;
    }

    @JsonProperty("parking_Street")
    public void setParkingStreet(String parkingStreet) {
        this.parkingStreet = parkingStreet;
    }

    @JsonProperty("parking_City")
    public String getParkingCity() {
        return parkingCity;
    }

    @JsonProperty("parking_City")
    public void setParkingCity(String parkingCity) {
        this.parkingCity = parkingCity;
    }

    @JsonProperty("x")
    public Float getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(Float x) {
        this.x = x;
    }

    @JsonProperty("y")
    public Float getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(Float y) {
        this.y = y;
    }

    @JsonProperty("free_places")
    public Integer getFreePlaces() {
        return freePlaces;
    }

    @JsonProperty("free_places")
    public void setFreePlaces(Integer freePlaces) {
        this.freePlaces = freePlaces;
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