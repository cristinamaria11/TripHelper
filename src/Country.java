import java.util.Comparator;
import java.util.TreeSet;

public class Country {
    private String name;
    private TreeSet<County> counties;

    public Country(String name, TreeSet<County> counties) {
        this.name = name;
        this.counties = counties;
    }

    public Country(String name) {
        this(name, new TreeSet<>(new Comparator<County>() {
            @Override
            public int compare(County c1, County c2) {
                return c1.getName().compareTo(c2.getName());
            }
        }));
    }

    public Country() {
        this(new String());
    }

    public String getName() {
        return name;
    }

    public TreeSet<County> getCounties() {
        return counties;
    }

    public void addCounty(County x) {
        this.counties.add(x);
    }

    public void removeCounty(County x) {
        this.counties.remove(x);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Country)) {
            return false;
        }

        Country c = (Country) o;

        return this.name.equalsIgnoreCase(c.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
