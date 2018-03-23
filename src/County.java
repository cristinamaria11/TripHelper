import java.util.Comparator;
import java.util.TreeSet;

public class County {
    private String name;
    private Country country;
    private TreeSet<City> cities;

    public County(String name, Country country,TreeSet<City> cities) {
        this.name = name;
        this.country = country;
        this.cities = cities;
    }

    public County(String name, Country country) {
        this(name, country, new TreeSet<>(new Comparator<City>() {
            @Override
            public int compare(City c1, City c2) {
                return c1.getName().compareTo(c2.getName());
            }
        }));
    }

    public County() {
        this(new String(), new Country());
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public TreeSet<City> getCities() {
        return cities;
    }

    public void addCity(City x) {
        this.cities.add(x);
    }

    public void removeCity(City x) {
        this.cities.remove(x);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof County)) {
            return false;
        }

        County c = (County) o;

        return this.name.equalsIgnoreCase(c.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
