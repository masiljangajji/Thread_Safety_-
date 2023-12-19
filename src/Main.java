public class Main {

    private static final int MAX_SELLERS_SIZE = 5;
    private static final int MAX_BUYERS_SIZE = 10;

    public static void main(String[] args) {

        Library library = new Library(); // 서점
        BookList bookList = new BookList(); // 서점에서 판매하는 책 리스트
        Seller[] sellers = new Seller[MAX_SELLERS_SIZE]; // 5명의 생산자
        Buyer[] buyers = new Buyer[MAX_BUYERS_SIZE]; // 10명의 소비자


        for (int i = 0; i < MAX_SELLERS_SIZE; i++) {
            sellers[i] = new Seller(library, bookList);
            sellers[i].start();
        }

        for (int i = 0; i < MAX_BUYERS_SIZE; i++) {
            buyers[i] = new Buyer(library);
            buyers[i].start();
        }

    }
}