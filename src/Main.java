import java.util.Comparator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        TreeSet<Place> locations = new TreeSet<>(new Comparator<Place>() {
            @Override
            public int compare(Place p1, Place p2) {
                return (int)(p1.getAveragePrice()-p2.getAveragePrice());
            }
        });

        HandleInputFiles filesHandler = new HandleInputFiles("input_locations");

        filesHandler.readLocations(locations);
               

        System.out.println("The added locations are:");
        System.out.println(locations);
    }
}
