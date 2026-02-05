import java.util.*;
import java.util.stream.Collectors;

public class Library {
    static HashMap<String, Book> bookMap = new HashMap<>();
    static HashMap<String, Librarian> librarianMap = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public static <K, V extends Book> HashMap<K, V> filterMapByKey(HashMap<K, V> originalMap, String key) {
        return (HashMap<K, V>) originalMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(key))
                .collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));
    }

    public static <K, V extends Book> HashMap<K, V> filterMapByAttribute(HashMap<K, V> originalMap, String attributeValue) {
        return (HashMap<K, V>) originalMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getGenre().equals(attributeValue))
                .collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));
    }

    public static <K, V extends Book> HashMap<K, V> filterMapByName(HashMap<K, V> originalMap, String attributeValue) {
        return originalMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getName().equalsIgnoreCase(attributeValue)) // .equalsIgnoreCase is safer!
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
    }

    public void addBook(HashMap<String, Book> map, Scanner sc) {
        System.out.println("Give the book an ID.");
        String id = sc.nextLine();
        System.out.println("What is the Book name?");
        String name = sc.nextLine();
        System.out.println("Give a short description of the book.");
        String description = sc.nextLine();
        System.out.println("What genre is the book?");
        String genre = sc.nextLine();

        Book newBook = new Book(name, description, genre);

        map.put(id, newBook);
        System.out.println("Book added successfully!");
    }

    public void removeBook(HashMap<String, Book> map, Scanner sc) {
        System.out.println("What book would you like to remove? (Enter book ID)");
        String id = sc.nextLine();
        if (map.containsKey(id)) {
            Book removedBook = map.remove(id);
            String name = removedBook.getName();
            System.out.println(removedBook + name + " was removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void editBook(HashMap<String, Book> map, Scanner sc) {
        System.out.println("What book would you like to edit? (Enter book ID)");
        String id = sc.nextLine();
        Book bookToEdit = map.get(id);
        if (map.containsKey(id)) {
            System.out.println("------\nEdit name\nEdit Description\nEdit Genre\n(q)uit");
            String choiceEdit = sc.nextLine();
            if (choiceEdit.equalsIgnoreCase("edit name")) {
                System.out.println("What would you like to change the name to?");
                String name = sc.nextLine();
                bookToEdit.setName(name);
                System.out.println("Book edited successfully!");
            } else if (choiceEdit.equalsIgnoreCase("edit description")) {
                System.out.println("What would you like to change the description to?");
                String description = sc.nextLine();
                bookToEdit.setDescription(description);
                System.out.println("Book edited successfully!");
            } else if (choiceEdit.equalsIgnoreCase("edit genre")) {
                System.out.println("What would you like to change the genre to?");
                String genre = sc.nextLine();
                bookToEdit.setGenre(genre);
                System.out.println("Book edited successfully!");
            } else if (choiceEdit.equalsIgnoreCase("q")) {
                return;
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void viewBook(HashMap<String, Book> map, Scanner sc) {
        System.out.println("What title would you like to search for?");
        String name = sc.nextLine();
        HashMap<String, Book> results = filterMapByName(map, name);

        if (results != null && !results.isEmpty()) {
            results.forEach((key, book) -> System.out.println("Key: " + key + " | " + book.toString()));
        } else {
            System.out.println("No books found matching: " + name);
        }
    }

    public void getAllBooks() {
        for (HashMap.Entry<String, Book> entry : bookMap.entrySet()) {
            System.out.println(entry.toString());
        }
    }

    public void getAllBooksLibrarian() {
        for (HashMap.Entry<String, Book> entry : bookMap.entrySet()) {
            System.out.println(entry.toString());
            System.out.println("Book ID: " + entry.getKey());
        }
    }

    public void addLibrarian(HashMap<String, Librarian> map, Scanner sc) {
        System.out.println("What is the Librarian's name?");
        String name = sc.nextLine();
        System.out.println("What genre are they interested in?");
        String interest = sc.nextLine();

        Librarian newLibrarian = new Librarian(interest);

        map.put(name, newLibrarian);
        System.out.println("Librarian added successfully!");
    }

    public void viewLibrarian(HashMap<String, Librarian> map, Scanner sc) {
        System.out.print("Enter librarian name to view: ");
        String name = sc.nextLine();

        if (map.containsKey(name)) {
            Librarian librarian = map.get(name);
            System.out.println("Librarian details: " + name + " Genre Interest: " + librarian.toString());
        } else {
            System.out.println("Librarian not found.");
        }
    }

    public void getAllLibrarians() {
        for (HashMap.Entry<String, Librarian> entry : librarianMap.entrySet()) {
            System.out.println("Librarian Name: " + entry.getKey() + ", Genre Interest: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library play = new Library();

        while (true) {
            System.out.println("Are you a librarian?");
            String choice1 = scanner.nextLine();

            if (choice1.equalsIgnoreCase("yes")) {
                while (true) {
                    System.out.println("------\nAdd new librarian\nAdd new book\nRemove book\nEdit book\nView librarians\nView books\n(q)uit");
                    String choice2 = scanner.nextLine();

                    if (choice2.equalsIgnoreCase("add new librarian")) {
                        System.out.println("------\n");
                        play.addLibrarian(librarianMap, scanner);
                    } else if (choice2.equalsIgnoreCase("add new book")) {
                        System.out.println("------\n");
                        play.addBook(bookMap, scanner);
                    } else if (choice2.equalsIgnoreCase("remove book")) {
                        System.out.println("------\n");
                        play.removeBook(bookMap, scanner);
                    } else if (choice2.equalsIgnoreCase("edit book")) {
                        System.out.println("------\n");
                        play.editBook(bookMap, scanner);
                    } else if (choice2.equalsIgnoreCase("view librarians")) {
                        System.out.println("------\n");
                        play.getAllLibrarians();
                    } else if (choice2.equalsIgnoreCase("view books")) {
                        System.out.println("------\n");
                        play.getAllBooksLibrarian();
                    } else if (choice2.equalsIgnoreCase("q")) {
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
            } else if (choice1.equalsIgnoreCase("no")) {
                while (true) {
                    System.out.println("------\nSelect librarian for recommendation\nSelect book\nView librarians\nView books\n(q)uit");
                    String choice3 = scanner.nextLine();

                    if (choice3.equalsIgnoreCase("select librarian")) {
                        System.out.println("What librarian would you like a recommendation from?");
                        String name = scanner.nextLine();

                        if (librarianMap.containsKey(name)) {
                            Librarian librarian = librarianMap.get(name);
                            if (librarian != null) {
                                String favoriteGenre = librarian.getInterest();
                                System.out.println("My favorite genre is " + favoriteGenre + " here is a list!");
                                HashMap<String, Book> filteredBookMap = filterMapByAttribute(bookMap, favoriteGenre);
                                for (HashMap.Entry<String, Book> entry : filteredBookMap.entrySet()) {
                                    System.out.println(entry.toString());
                                }
                            }
                        } else {
                            System.out.println("Librarian not found.");
                        }
                    } else if (choice3.equalsIgnoreCase("select book")) {
                        System.out.println("------\n");
                        play.viewBook(bookMap, scanner);
                    } else if (choice3.equalsIgnoreCase("view librarians")) {
                        System.out.println("------\n");
                        play.getAllLibrarians();
                    } else if (choice3.equalsIgnoreCase("view books")) {
                        System.out.println("------\n");
                        play.getAllBooks();
                    } else if (choice3.equalsIgnoreCase("q")) {
                        break;
                    }
                }
            } else if (choice1.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Not a valid input.");
            }

        }
    }

}

