import java.util.ArrayList;
import java.util.Date;

/*Class describing a place, containing all the attributes a place has.*/
public class PlaceAttributes {
    private float averagePrice;
    private ArrayList<String> activities;
    private Date start, end;
    private String place, city, county, country;

    public PlaceAttributes(float averagePrice, ArrayList<String> activities, Date start, Date end,
                           String place, String city, String county, String country) {
        this.averagePrice = averagePrice;
        this.activities = activities;
        this.start = start;
        this.end = end;
        this.place = place;
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

    /*Check if the current place is available between the given dates.*/
    public boolean availableBetween(Date start, Date end) {
        boolean available = true;
        if(this.start.after(start) || this.end.before(end)) {
            available = false;
        }
        return available;
    }

    @Override
    public String toString() {
        return this.place;
    }
}
