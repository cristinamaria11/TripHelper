import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class ExecuteCommands {

    public void displayAll(HashMap<String, CountryAttributes> countries, BufferedWriter bw) {
        try {
            Set<String> countriesSet, countiesSet, citiesSet, placesSet;
            countriesSet = countries.keySet();
            bw.write("All your possibilities are:\n");
            if(countriesSet.isEmpty()) {
                bw.write("There are no countries here.\n");
            }
            else {
                for (String country : countriesSet) {
                    bw.write(country + "\n");
                    HashMap<String, CountyAttributes> counties = countries.get(country).getCounties();
                    countiesSet = counties.keySet();
                    if(counties.isEmpty()) {
                        bw.write("There are no counties here\n");
                    }
                    else {
                        for (String county : countiesSet) {
                            bw.write("\t" + county + "\n");
                            HashMap<String, CityAttributes> cities = counties.get(county).getCities();
                            citiesSet = cities.keySet();
                            if(citiesSet.isEmpty()) {
                                bw.write("There are no cities here\n");
                            }
                            else {
                                for (String city : citiesSet) {
                                    bw.write("\t\t" + city + "\n");
                                    HashMap<String, PlaceAttributes> places = cities.get(city).getPlaces();
                                    placesSet = places.keySet();
                                    if(places.isEmpty()) {
                                        bw.write("There are no places here.\n");
                                    }
                                    else {
                                        for (String place : placesSet) {
                                            bw.write("\t\t\t" + place + "\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
