import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
                    for (String county : countiesSet) {
                        bw.write("\t" + county + "\n");
                        HashMap<String, CityAttributes> cities = counties.get(county).getCities();
                        citiesSet = cities.keySet();
                        for (String city : citiesSet) {
                            bw.write("\t\t" + city + "\n");
                            HashMap<String, PlaceAttributes> places = cities.get(city).getPlaces();
                            placesSet = places.keySet();
                            for (String place : placesSet) {
                                bw.write("\t\t\t" + place + "\n");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findPlace(HashMap<String, PlaceAttributes> places, String place,BufferedWriter bw) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            PlaceAttributes placeAttributes;
            bw.write("\nAll details about " + place + " are: \n");
            placeAttributes = places.get(place);
            bw.write("City: " + placeAttributes.getCity() + "\n");
            bw.write("County: " + placeAttributes.getCounty() + "\n");
            bw.write("Country: " + placeAttributes.getCountry() + "\n");
            bw.write("Average price/day: " + placeAttributes.getAveragePrice() + " RON\n");
            bw.write("Activities: " + placeAttributes.getActivities() + "\n");
            bw.write("Available period: "+ dateFormat.format(placeAttributes.getStart()) +
                    "-" + dateFormat.format(placeAttributes.getEnd()) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findPlaceIn(HashMap<String, PlaceAttributes> places, String city,BufferedWriter bw) {
        try {
            PlaceAttributes placeAttributes;
            Set<String> placesSet;
            placesSet = places.keySet();
            bw.write("\nAll your possibilities in " + city + " are: ");
            for (String place : placesSet) {
                placeAttributes = places.get(place);
                if(placeAttributes.getCity().equals(city)) {
                    bw.write(place+ "; ");
                }
            }
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void topPlacesAvailable(HashMap<String, CountryAttributes> countries, String countryName,
                          String countyName, String cityName, Date start, Date end, BufferedWriter bw) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            bw.write("\nTop places in " +cityName + " during the given period: ");
            Set<String> countriesSet, countiesSet, citiesSet, placesSet;
            countriesSet = countries.keySet();
            if(countriesSet.isEmpty()) {
                bw.write("There are no countries here.\n");
            }
            else {
                CountryAttributes countryAttributes = countries.get(countryName);
                CountyAttributes countyAttributes = countryAttributes.getCounties().get(countyName);
                CityAttributes cityAttributes = countyAttributes.getCities().get(cityName);
                TreeSet<PlaceAttributes> orderedPlaces = new TreeSet<PlaceAttributes>(new Comparator<PlaceAttributes>() {
                    @Override
                    public int compare(PlaceAttributes p1, PlaceAttributes p2) {
                        return (int) (p1.getAveragePrice() - p2.getAveragePrice());
                    }
                });
                Collection<PlaceAttributes> placeAttributes = cityAttributes.getPlaces().values();
                for (PlaceAttributes p : placeAttributes) {
                    if (p.availableBetween(start, end)) {
                        orderedPlaces.add(p);
                    }
                }
                if (orderedPlaces.isEmpty()) {
                    bw.write("No places available at the time.\n");
                } else {
                    bw.write(orderedPlaces.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void topPlaces(HashMap<String, CountryAttributes> countries, String countryName,
                          String countyName, String cityName, BufferedWriter bw) {
        try {
            bw.write("\nTop places in " +cityName + ": ");
            Set<String> countriesSet, countiesSet, citiesSet, placesSet;
            countriesSet = countries.keySet();
            if(countriesSet.isEmpty()) {
                bw.write("There are no countries here.\n");
            }
            else {
                CountryAttributes countryAttributes = countries.get(countryName);
                CountyAttributes countyAttributes = countryAttributes.getCounties().get(countyName);
                CityAttributes cityAttributes = countyAttributes.getCities().get(cityName);
                TreeSet<PlaceAttributes> orderedPlaces = new TreeSet<PlaceAttributes>(new Comparator<PlaceAttributes>() {
                    @Override
                    public int compare(PlaceAttributes p1, PlaceAttributes p2) {
                        return (int) (p1.getAveragePrice() - p2.getAveragePrice());
                    }
                });
                orderedPlaces.addAll(cityAttributes.getPlaces().values());
                bw.write(orderedPlaces.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void topPlacesbyActivity(HashMap<String, CountryAttributes> countries, String activity, BufferedWriter bw) {
        try {
            Set<String> countriesSet, countiesSet, citiesSet, placesSet;
            countriesSet = countries.keySet();
            bw.write("\nBy selecting " + activity + " as an activity, all your possibilities are: ");
            TreeSet<PlaceAttributes> orderedPlaces = new TreeSet<PlaceAttributes>(new Comparator<PlaceAttributes>() {
                @Override
                public int compare(PlaceAttributes p1, PlaceAttributes p2) {
                    return (int) (p1.getAveragePrice() - p2.getAveragePrice());
                }
            });
            for (String country : countriesSet) {
                HashMap<String, CountyAttributes> counties = countries.get(country).getCounties();
                countiesSet = counties.keySet();
                for (String county : countiesSet) {
                    HashMap<String, CityAttributes> cities = counties.get(county).getCities();
                    citiesSet = cities.keySet();
                    for (String city : citiesSet) {
                        HashMap<String, PlaceAttributes> places = cities.get(city).getPlaces();
                        placesSet = places.keySet();
                        for (String p : placesSet) {
                            PlaceAttributes placeAttributes = places.get(p);
                            if(placeAttributes.getActivities().contains(activity)) {
                                orderedPlaces.add(placeAttributes);
                            }
                        }
                    }
                }
            }
            if (orderedPlaces.isEmpty()) {
                bw.write("No places matching your criteria.\n");
            } else {
                bw.write(orderedPlaces.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
