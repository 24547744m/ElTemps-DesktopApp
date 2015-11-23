package pojos;

/**
 * Created by dam on 13/11/15.
 */
public class Time {
    private String dateFrom;
    private String dateTo;
    private String windDirection;
    private String windSpeed;
    private String temperature;
    private String humidity;
    private String clouds;
    private String idImage;


    public Time(String dateFrom, String dateTo, String windDirection, String windSpeed, String temperature,
                String humidity, String clouds, String idImage) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.humidity = humidity;
        this.clouds = clouds;
        this.idImage = idImage;
    }
    public Time(){}


    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }
}
