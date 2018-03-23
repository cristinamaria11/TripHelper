import java.util.Date;
import java.util.HashSet;

public class Place {
    private String name;
    private City city;
    private float averagePrice;
    private HashSet<String> activities;
    private Date availableFrom, availableUntil;

    public Place(String name, City city,float averagePrice, HashSet<String> activities,
                Date availableFrom, Date availableUntil) {
        this.name = name;
        this.city = city;
        this.averagePrice = averagePrice;
        this.activities = activities;
        this.availableFrom = availableFrom;
        this.availableUntil = availableUntil;
    }

    public Place() {
        this(new String(), new City(), 0, new HashSet<String>(), new Date(), new Date());
    }

    public String getName() {
        return name;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public HashSet<String> getActivities() {
        return activities;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Place)) {
            return false;
        }

        Place c = (Place) o;

        return this.name.equalsIgnoreCase(c.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
