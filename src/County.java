import java.util.HashSet;

public class County {
    private String name;
    private Country country;
    private HashSet<City> cities;

    public County(String name, Country country,HashSet<City> cities) {
        this.name = name;
        this.country = country;
        this.cities = cities;
    }

    public County(String name, Country country) {
        this(name, country, new HashSet<City>());
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

    public HashSet<City> getCities() {
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
