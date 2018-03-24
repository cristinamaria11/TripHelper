import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, CountryAttributes> countries = new HashMap<String, CountryAttributes>();
        HashMap<String, PlaceAttributes> places = new HashMap<String, PlaceAttributes>();

        String input_locations = new String();
        String input_commands = new String();
        String output_file = new String();

        /*Reading file names from stdin.*/
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please introduce the name of the locations file:");
            input_locations = br.readLine();
            System.out.println("Please introduce the name of the commands file:");
            input_commands = br.readLine();
            System.out.println("Please introduce the name of the output file:");
            output_file = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Send files further to be read/written.*/
        HandleInputFiles filesHandler = new HandleInputFiles(input_locations, input_commands, output_file);
        filesHandler.readLocations(countries, places);
        filesHandler.readExecuteCommands(countries, places);

    }
}
