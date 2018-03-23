import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

public class HandleInputFiles {

    String locationsFile;

    public HandleInputFiles(String locationsFile) {
        this.locationsFile = locationsFile;
    }

    public void readLocations(TreeSet<Place> locations) {
        FileReader file = null;
        String line = new String();
        String locationName, cityName, countyName, countryName;
        float averagePrice = 0;
        HashSet<String> activities = new HashSet<String>();
        Date availableFrom, availableUntil;
        String []parts = null;
        String []splitActivities =null;
        String []dates = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Country country = new Country();
        County county = new County();
        City city = new City();
        Place place = new Place();

        locationName = new String();
        cityName = new String();
        countyName = new String();
        countryName = new String();
        availableFrom = new Date();
        availableUntil = new Date();
        try {
            file = new FileReader(this.locationsFile);
            BufferedReader br = new BufferedReader(file);
            while((line = br.readLine()) != null) {
                parts = line.split(";");
                locationName = parts[0];
                cityName = parts[1];
                countyName = parts[2];
                countryName = parts[3];
                averagePrice = Float.valueOf(parts[4]);
                splitActivities = parts[5].split(",");
                for(int i = 0; i < splitActivities.length; i++) {
                    activities.add(splitActivities[i]);
                }
                dates = parts[6].split(",");
                availableFrom = dateFormat.parse(dates[0]);
                availableUntil = dateFormat.parse(dates[1]);
                country = new Country(countryName);
                county = new County(countyName, country);
                country.addCounty(county);
                city = new City(cityName, county);
                county.addCity(city);
                place = new Place(locationName, city, averagePrice, activities, availableFrom, availableUntil);
                city.addPlace(place);
                locations.add(place);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
