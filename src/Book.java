public class Book {
    private final String name;
    private final int index;

    public Book(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return name;
    }

}