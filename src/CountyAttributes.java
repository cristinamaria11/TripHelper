import java.util.HashMap;

/*Class describing a county, containing a list of all the cities and
the name of the country it belongs to.*/
public class CountyAttributes {
    private HashMap<String, CityAttributes> cities;
    private String country;

    public CountyAttributes(HashMap<String, CityAttributes> cities, String country) {
        this.cities = cities;
        this.country = country;
    }

    public CountyAttributes(String country) {
        this(new HashMap<String, CityAttributes>(), country);
    }

    public HashMap<String, CityAttributes> getCities() {
        return cities;
    }

    public String getCountry() {
        return country;
    }

    public void addCity(String cityName, CityAttributes cityAttributes) {
        this.cities.put(cityName, cityAttributes);
    }
}
