import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HandleInputFiles {

    /*Files containing all the places, commands and the one
    * that has to contain the final results.*/
    String placesFile;
    String commandsFile;
    String outputFile;

    public HandleInputFiles(String placesFile, String commandsFile, String output_file) {
        this.placesFile = placesFile;
        this.commandsFile = commandsFile;
        this.outputFile = output_file;
    }

    /*Reads every line of the locations file, splits it and initialises the entire
    hierarchy: countries->counties->cities->places.*/
    public void readLocations(HashMap<String, CountryAttributes> countries,
                              HashMap<String, PlaceAttributes> places) {
        FileReader file = null;
        String line = new String();
        String placeName, cityName, countyName, countryName;
        float averagePrice = 0;
        ArrayList<String> activities;
        Date availableFrom, availableUntil;
        String []parts;
        String []splitActivities;
        String []dates;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CountryAttributes countryAttributes;
        CountyAttributes countyAttributes;
        CityAttributes cityAttributes;
        PlaceAttributes placeAttributes;

        try {
            file = new FileReader(this.placesFile);
            BufferedReader br = new BufferedReader(file);
            while((line = br.readLine()) != null) {
                /*Reinitialise the activities list in order to have a new one for every city.*/
                activities = new ArrayList<String>();

                parts = line.split(";");
                placeName = parts[0];
                cityName = parts[1];
                countyName = parts[2];
                countryName = parts[3];
                averagePrice = Float.valueOf(parts[4]);
                splitActivities = parts[5].split(",");

                /*Create the activities list.*/
                for(int i = 0; i < splitActivities.length; i++) {
                    activities.add(splitActivities[i]);
                }

                /*Transform the input into Date format.*/
                dates = parts[6].split(",");
                availableFrom = dateFormat.parse(dates[0]);
                availableUntil = dateFormat.parse(dates[1]);

                /*Add every country, county, city, place to the hierarchy, it it doesn't contain them already.*/
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
                placeAttributes = new PlaceAttributes(averagePrice, activities, availableFrom, availableUntil, placeName,cityName, countyName, countryName);
                existentPlaces.put(placeName, placeAttributes);
                places.put(placeName, placeAttributes);
            }
            /*Close the input file.*/
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*Reads every line of the commands file, splits it and calls the adequate methods.*/
    public void readExecuteCommands(HashMap<String, CountryAttributes> countries,
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
                /*Depending on the command, call the right method.*/
                if(parts[0].compareTo("Display all") == 0) {
                    exec.displayAll(countries, bw);
                    continue;
                }
                if(parts[0].compareTo("Find location named") == 0) {
                    exec.findPlace(places, parts[1], bw);
                    continue;
                }
                if(parts[0].compareTo("Find locations in city") == 0) {
                    exec.findPlaceIn(places, parts[1], bw);
                    continue;
                }
                if(parts[0].compareTo("Find places by activity") == 0) {
                    exec.topPlacesbyActivity(countries, parts[1], bw);
                    continue;
                }
                if(parts[0].contains("between")) {
                    String []location = parts[0].split(" ");
                    String []locationParts = location[3].split("/");
                    countryName = locationParts[0];
                    countyName = locationParts[1];
                    cityName = locationParts[2];
                    String []dateParts = parts[1].split("-");
                    availableFrom = dateFormat.parse(dateParts[0]);
                    availableUntil = dateFormat.parse(dateParts[1]);
                    exec.topPlacesAvailable(countries, countryName, countyName, cityName, availableFrom, availableUntil,bw);
                    continue;
                }
                if(parts[0].contains("Cheapest")) {
                    String []location = parts[0].split(" ");
                    String []locationParts = location[3].split("/");
                    countryName = locationParts[0];
                    countyName = locationParts[1];
                    cityName = locationParts[2];
                    exec.topPlaces(countries, countryName, countyName, cityName,bw);
                    continue;
                }
            }
            /*Close both the output file and the commands one.*/
            bw.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
