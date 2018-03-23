import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashSet<Place> locations = new HashSet<Place>();
        Place x1 = new Place();
        Place x2 = new Place();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            x1 = new Place("Hotel Romula", new City("Caracal", new County("Olt", new Country("Romania"))),
                    100, new HashSet<String>(), sdf.parse("21/12/2012"), sdf.parse("21/12/2013"));

            x2 = new Place("Hotel Edinburgh", new City("Caracal", new County("Olt", new Country("Romania"))),
                    100, new HashSet<String>(), sdf.parse("21/12/2012"), sdf.parse("21/12/2013"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        locations.add(x1);
        locations.add(x2);

        System.out.println("The added locations are:");
        System.out.println(locations);
    }
}
