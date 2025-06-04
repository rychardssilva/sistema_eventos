

public class Event1 {
    private String name;
    private String date;

    public Event1(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name + " - " + date;
    }
}
