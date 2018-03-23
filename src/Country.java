import java.util.HashSet;

public class Country {
    private String name;
    private HashSet<County> counties;

    public Country(String name, HashSet<County> counties) {
        this.name = name;
        this.counties = counties;
    }

    public Country(String name) {
        this(name, new HashSet<County>());
    }

    public Country() {
        this(new String());
    }

    public String getName() {
        return name;
    }

    public HashSet<County> getCounties() {
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
