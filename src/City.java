import java.util.Date;
import java.util.HashSet;

public class City {

    private String name;
    private County county;
    private HashSet<String> places;

    public City(String name, County county,HashSet<String> places) {
        this.name = name;
        this.county = county;
        this.places = places;
    }

    public City(String name, County county) {
        this(name, county,new HashSet<String>());
    }

    public City() {
        this(new String(), new County());
    }

    public String getName() {
        return name;
    }

    public County getCounty() {
        return county;
    }

    public HashSet<String> getPlaces() {
        return places;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof City)) {
            return false;
        }

        City c = (City) o;

        return this.name.equalsIgnoreCase(c.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
