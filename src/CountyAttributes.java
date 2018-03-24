import java.util.HashMap;

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
