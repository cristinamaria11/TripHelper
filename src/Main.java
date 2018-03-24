import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        HashMap<String, CountryAttributes> countries = new HashMap<String, CountryAttributes>();
        HashMap<String, PlaceAttributes> places = new HashMap<String, PlaceAttributes>();

        HandleInputFiles filesHandler = new HandleInputFiles("input_locations", "input_commands", "output_file");

        filesHandler.readLocations(countries, places);
        filesHandler.readCommands(countries, places);

    }
}
