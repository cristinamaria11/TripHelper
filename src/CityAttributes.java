import java.util.HashMap;

public class CityAttributes {

    private HashMap<String, PlaceAttributes> places;
    private String county;
    private String country;

    public CityAttributes (HashMap<String, PlaceAttributes> places, String county, String country) {
        this.places = places;
        this.county = county;
        this.country = country;
    }

    public CityAttributes(String county, String country) {
        this(new HashMap<String, PlaceAttributes>(), county, country);
    }

    public HashMap<String, PlaceAttributes> getPlaces() {
        return places;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public void addPlace(String placeName, PlaceAttributes placeAttributes) {
        this.places.put(placeName, placeAttributes);
    }
}
