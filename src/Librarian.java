public class Librarian {
    private final String interest;

    public Librarian(String interest) {
        this.interest = interest;
    }

    public String getInterest() {
        return interest;
    }

    @Override
    public String toString() {
        return interest;
    }
}
