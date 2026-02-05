public class Book {
    private String name;
    private String description;
    private String genre;

    public Book(String name, String description, String genre) {
        this.name = name;
        this.description = description;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "------\nBook details:\n" + "Name: " + name + "\nDescription: " + description + "\nGenre: " + genre + "\n";
    }
}
