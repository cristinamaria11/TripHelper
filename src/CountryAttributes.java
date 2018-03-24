import java.util.HashMap;

/*Class describing a country, containing a list of all the counties.*/
public class CountryAttributes {
    private HashMap<String, CountyAttributes> counties;

    public CountryAttributes(HashMap<String, CountyAttributes> counties) {
        this.counties = counties;
    }

    public CountryAttributes() {
        this(new HashMap<String, CountyAttributes>());
    }

    public HashMap<String, CountyAttributes> getCounties() {
        return counties;
    }

    public void addCounty(String countyName, CountyAttributes countyAttributes) {
        this.counties.put(countyName, countyAttributes);
    }
}
