import java.util.ArrayList;
import java.util.Date;

public class PlaceAttributes {
    private float averagePrice;
    private ArrayList<String> activities;
    private Date start, end;
    private String city, county, country;

    public PlaceAttributes(float averagePrice, ArrayList<String> activities, Date start, Date end,
                           String city, String county, String country) {
        this.averagePrice = averagePrice;
        this.activities = activities;
        this.start = start;
        this.end = end;
        this.city = city;
        this.county = county;
        this.country = country;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }
}
