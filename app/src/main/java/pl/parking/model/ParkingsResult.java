package pl.parking.model;

import com.google.gson.annotations.SerializedName;

public class ParkingsResult {

    @SerializedName("ID")
    private Integer iD;

    @SerializedName("Ilosc_miejsc")
    private Integer iloscmiejsc;

    @SerializedName("Miasto")
    private String miasto;

    @SerializedName("Nazwa")
    private String nazwa;

    @SerializedName("Nr_ulicy")
    private Integer nr_ulicy;

    @SerializedName("Ulica")
    private String ulica;

    @SerializedName("Wspolrzedna_X")
    private Double X;

    @SerializedName("Wspolrzedna_Y")
    private Double Y;

    public Integer getID() {
        return iD;
    }

    public Integer getIloscmiejsc() {
        return iloscmiejsc;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Integer getNr_ulicy() {
        return nr_ulicy;
    }

    public String getUlica() {
        return ulica;
    }

    public Double getX() {
        return X;
    }

    public Double getY() {
        return Y;
    }

    /** Builder for ParkingsResult */
    public static class _ParkingsResultBuilder {
        private ParkingsResult toBuild = new ParkingsResult();

        public _ParkingsResultBuilder() {}

        public ParkingsResult build() {
            return toBuild;
        }

        public _ParkingsResultBuilder iD(Integer value) {
            toBuild.iD = value;
            return this;
        }

        public _ParkingsResultBuilder iloscmiejsc(Integer value) {
            toBuild.iloscmiejsc = value;
            return this;
        }

        public _ParkingsResultBuilder miasto(String value) {
            toBuild.miasto = value;
            return this;
        }

        public _ParkingsResultBuilder nazwa(String value) {
            toBuild.nazwa = value;
            return this;
        }

        public _ParkingsResultBuilder nr_ulicy(Integer value) {
            toBuild.nr_ulicy = value;
            return this;
        }

        public _ParkingsResultBuilder ulica(String value) {
            toBuild.ulica = value;
            return this;
        }

        public _ParkingsResultBuilder wspolrzedna_X(Double value) {
            toBuild.X = value;
            return this;
        }

        public _ParkingsResultBuilder wspolrzedna_Y(Double value) {
            toBuild.Y = value;
            return this;
        }
    }
}
