import java.util.concurrent.ThreadLocalRandom;

public class Buyer extends Thread {

    private final Library library;

    Buyer(Library library) {
        this.library = library;
    }

    @Override
    public void run() {

        while (true) {

            try {
                // multi thread환경에서 난수 생성 , 구입할 책 결정
                int index = ThreadLocalRandom.current().nextInt(0, 5);

                library.sellBook(index);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

        }

    }
}