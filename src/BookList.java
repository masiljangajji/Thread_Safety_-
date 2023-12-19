import java.util.ArrayList;
import java.util.List;

public class BookList {


    private final List<Book> bookList = new ArrayList<>();


    BookList() {
        bookList.add(new Book("마법사의 돌", 0));
        bookList.add(new Book("비밀의방", 1));
        bookList.add(new Book("아즈카반의죄수", 2));
        bookList.add(new Book("불의잔", 3));
        bookList.add(new Book("불사조기사단", 4));
    }


    public Book getBookByIndex(int index) {
        return bookList.get(index);
    }
}
