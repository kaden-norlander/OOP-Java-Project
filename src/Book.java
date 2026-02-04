public class Book {
    private final String name;
    private final String description;
    private final String genre;

    public Book(String name, String description, String genre) {
        this.name = name;
        this.description = description;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "------\nBook details:\n" + "Name: " + name + "\nDescription: " + description + "\nGenre: " + genre;
    }
}
