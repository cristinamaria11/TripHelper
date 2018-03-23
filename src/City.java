import java.util.Comparator;
import java.util.TreeSet;

public class City {

    private String name;
    private County county;
    private TreeSet<Place> places;

    public City(String name, County county,TreeSet<Place> places) {
        this.name = name;
        this.county = county;
        this.places = places;
    }

    public City(String name, County county) {
        this(name, county,new TreeSet<>(new Comparator<Place>() {
            @Override
            public int compare(Place p1, Place p2) {
                return (int) (p1.getAveragePrice() - p2.getAveragePrice());
            }
        }));
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

    public TreeSet<Place> getPlaces() {
        return places;
    }

    public void addPlace(Place x) {
        this.places.add(x);
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
