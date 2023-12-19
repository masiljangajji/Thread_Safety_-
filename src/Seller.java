import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread {

    private final Library library;
    private final BookList bookList;

    Seller(Library library, BookList bookList) {
        this.library = library;
        this.bookList = bookList;
    }

    @Override
    public void run() {

        while (true) {

            try {
                int index = ThreadLocalRandom.current().nextInt(0, 5);
                // multi thread환경에서 난수 생성

                library.buyBook(bookList.getBookByIndex(index)); // 책 입고
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }

    }
}