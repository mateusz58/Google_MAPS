

        package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import org.apache.commons.lang3.builder.EqualsBuilder;
        import org.apache.commons.lang3.builder.HashCodeBuilder;
        import org.apache.commons.lang3.builder.ToStringBuilder;
        public class car_booking implements Serializable
        {


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
            private List<Booking> booking = new ArrayList<Booking>();
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
            private final static long serialVersionUID = -7681549569650921491L;

            /**
             * No args constructor for use in serialization
             *
             */
            public car_booking() {
            }

            /**
             *
             * @param parkingStreet
             * @param id
             * @param parking
             * @param booking
             * @param numberOfCars
             * @param parkingName
             * @param parkingCity
             * @param dateTo
             * @param active
             * @param dateFrom
             * @param user
             * @param cost
             */
            public car_booking(Integer id, Integer parking, Float cost, Integer user, Integer numberOfCars, List<Booking> booking, String dateFrom, String dateTo, Boolean active, String parkingName, String parkingStreet, String parkingCity) {
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

            public car_booking withId(Integer id) {
                this.id = id;
                return this;
            }

            public Integer getParking() {
                return parking;
            }

            public void setParking(Integer parking) {
                this.parking = parking;
            }

            public car_booking withParking(Integer parking) {
                this.parking = parking;
                return this;
            }

            public Float getCost() {
                return cost;
            }

            public void setCost(Float cost) {
                this.cost = cost;
            }

            public car_booking withCost(Float cost) {
                this.cost = cost;
                return this;
            }

            public Integer getUser() {
                return user;
            }

            public void setUser(Integer user) {
                this.user = user;
            }

            public car_booking withUser(Integer user) {
                this.user = user;
                return this;
            }

            public Integer getNumberOfCars() {
                return numberOfCars;
            }

            public void setNumberOfCars(Integer numberOfCars) {
                this.numberOfCars = numberOfCars;
            }

            public car_booking withNumberOfCars(Integer numberOfCars) {
                this.numberOfCars = numberOfCars;
                return this;
            }

            public List<Booking> getBooking() {
                return booking;
            }

            public void setBooking(List<Booking> booking) {
                this.booking = booking;
            }

            public car_booking withBooking(List<Booking> booking) {
                this.booking = booking;
                return this;
            }

            public String getDateFrom() {
                return dateFrom;
            }

            public void setDateFrom(String dateFrom) {
                this.dateFrom = dateFrom;
            }

            public car_booking withDateFrom(String dateFrom) {
                this.dateFrom = dateFrom;
                return this;
            }

            public String getDateTo() {
                return dateTo;
            }

            public void setDateTo(String dateTo) {
                this.dateTo = dateTo;
            }

            public car_booking withDateTo(String dateTo) {
                this.dateTo = dateTo;
                return this;
            }

            public Boolean getActive() {
                return active;
            }

            public void setActive(Boolean active) {
                this.active = active;
            }

            public car_booking withActive(Boolean active) {
                this.active = active;
                return this;
            }

            public String getParkingName() {
                return parkingName;
            }

            public void setParkingName(String parkingName) {
                this.parkingName = parkingName;
            }

            public car_booking withParkingName(String parkingName) {
                this.parkingName = parkingName;
                return this;
            }

            public String getParkingStreet() {
                return parkingStreet;
            }

            public void setParkingStreet(String parkingStreet) {
                this.parkingStreet = parkingStreet;
            }

            public car_booking withParkingStreet(String parkingStreet) {
                this.parkingStreet = parkingStreet;
                return this;
            }

            public String getParkingCity() {
                return parkingCity;
            }

            public void setParkingCity(String parkingCity) {
                this.parkingCity = parkingCity;
            }

            public car_booking withParkingCity(String parkingCity) {
                this.parkingCity = parkingCity;
                return this;
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this).append("id", id).append("parking", parking).append("cost", cost).append("user", user).append("numberOfCars", numberOfCars).append("booking", booking).append("dateFrom", dateFrom).append("dateTo", dateTo).append("active", active).append("parkingName", parkingName).append("parkingStreet", parkingStreet).append("parkingCity", parkingCity).toString();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder().append(parkingStreet).append(parking).append(booking).append(parkingName).append(parkingCity).append(dateTo).append(cost).append(id).append(numberOfCars).append(active).append(dateFrom).append(user).toHashCode();
            }

            @Override
            public boolean equals(Object other) {
                if (other == this) {
                    return true;
                }
                if ((other instanceof car_booking) == false) {
                    return false;
                }
                car_booking rhs = ((car_booking) other);
                return new EqualsBuilder().append(parkingStreet, rhs.parkingStreet).append(parking, rhs.parking).append(booking, rhs.booking).append(parkingName, rhs.parkingName).append(parkingCity, rhs.parkingCity).append(dateTo, rhs.dateTo).append(cost, rhs.cost).append(id, rhs.id).append(numberOfCars, rhs.numberOfCars).append(active, rhs.active).append(dateFrom, rhs.dateFrom).append(user, rhs.user).isEquals();
            }

        }