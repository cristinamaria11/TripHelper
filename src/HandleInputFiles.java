import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HandleInputFiles {

    String placesFile;
    String commandsFile;
    String outputFile;

    public HandleInputFiles(String placesFile, String commandsFile, String output_file) {
        this.placesFile = placesFile;
        this.commandsFile = commandsFile;
        this.outputFile = output_file;
    }

    public void readLocations(HashMap<String, CountryAttributes> countries,
                              HashMap<String, PlaceAttributes> places) {
        FileReader file = null;
        String line = new String();
        String placeName, cityName, countyName, countryName;
        float averagePrice = 0;
        ArrayList<String> activities = new ArrayList<String>();
        Date availableFrom, availableUntil;
        String []parts = null;
        String []splitActivities =null;
        String []dates = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CountryAttributes countryAttributes;
        CountyAttributes countyAttributes;
        CityAttributes cityAttributes;
        PlaceAttributes placeAttributes;

        placeName = new String();
        cityName = new String();
        countyName = new String();
        countryName = new String();
        availableFrom = new Date();
        availableUntil = new Date();
        try {
            file = new FileReader(this.placesFile);
            BufferedReader br = new BufferedReader(file);
            while((line = br.readLine()) != null) {
                parts = line.split(";");
                placeName = parts[0];
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

                if(countries.containsKey(countryName)) {
                    countryAttributes = countries.get(countryName);
                }
                else {
                    countries.put(countryName, new CountryAttributes());
                    countryAttributes = new CountryAttributes();
                }

                HashMap<String, CountyAttributes> counties = countryAttributes.getCounties();
                if(counties.containsKey(countyName)) {
                    countyAttributes = counties.get(countyName);
                }
                else {
                    countyAttributes = new CountyAttributes(countryName);
                    counties.put(countyName, countyAttributes);
                }

                HashMap<String, CityAttributes> cities = countyAttributes.getCities();
                if(cities.containsKey(cityName)) {
                    cityAttributes = cities.get(cityName);
                }
                else {
                    cityAttributes = new CityAttributes(countyName, countryName);
                    cities.put(cityName, cityAttributes);
                }

                HashMap<String, PlaceAttributes> existentPlaces = cityAttributes.getPlaces();
                placeAttributes = new PlaceAttributes(averagePrice, activities, availableFrom, availableUntil, cityName, countyName, countryName);
                existentPlaces.put(placeName, placeAttributes);
                places.put(placeName, placeAttributes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void readCommands(HashMap<String, CountryAttributes> countries,
                             HashMap<String, PlaceAttributes> places) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FileReader file = null;
        String line = new String();
        String locationName, cityName, countyName, countryName;
        Date availableFrom, availableUntil;
        String []parts = null;
        ExecuteCommands exec = new ExecuteCommands();

        try {
            file = new FileReader(this.commandsFile);
            BufferedReader br = new BufferedReader(file);
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            while((line = br.readLine()) != null) {
                parts = line.split(":");
                if(parts[0].compareTo("Display all") == 0) {
                    exec.displayAll(countries, bw);
                    continue;
                }
                if(parts[0].compareTo("Find location named") == 0) {
                    continue;
                }
                if(parts[0].compareTo("Find locations in city") == 0) {
                    continue;
                }
                if(parts[0].compareTo("Order by price in city") == 0) {
                    continue;
                }
                if(parts[0].compareTo("Find cheapest places by activity") == 0) {
                    continue;
                }
                if(parts[0].contains("Availability")) {
                    String []location = parts[0].split(" ");
                    String []locationParts = location[2].split("/");
                    countryName = locationParts[0];
                    countyName = locationParts[1];
                    cityName = locationParts[2];
                    String []dateParts = parts[1].split("-");
                    availableFrom = dateFormat.parse(dateParts[0]);
                    availableUntil = dateFormat.parse(dateParts[1]);
                    continue;
                }
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
