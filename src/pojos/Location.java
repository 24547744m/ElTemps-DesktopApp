package pojos;

/**
 * Created by dam on 13/11/15.
 */
public class Location {
    private String city;
    private String country;
    private String location;

    public Location(String city, String country, String location) {
        this.city = city;
        this.country = country;
        this.location = location;
    }
    public Location(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
