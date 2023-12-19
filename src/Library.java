import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Library {

    private final List[] books = new ArrayList[5];

    private final Semaphore semaphore = new Semaphore(2); // 스레드 2개까지 접근 가능하게 제한하겠다.

    public Library() {
        for (int i = 0; i < 5; i++) {
            List<Book> list = new ArrayList<>();
            books[i] = list;
        }
    }

    public synchronized void printList() {

        for (int i = 0; i < books.length; i++) {
            if (!books[i].isEmpty()) {
                System.out.println(books[i].get(0) + " 현재 재고 : " + books[i].size());
            }
        }

    }

    public void buyBook(Book book) {

        int index = book.getIndex();

        try {
            semaphore.acquire(); // 납품할 생산자는 최대 2명으로 제한하여 접근하게 함 
            synchronized (books[index]) { // books[index]에 대한 모니터 락 획득.

                while (books[index].size() >= 10) { // 책이 이미 10개 이상 존재한다면 더 이상 받을 수 없음
                    books[index].wait(); // books[index]가 비워질떄 까지 기다려야 하기 때문에 모니터 락을 반환하고 스레드는 일시정지 시킴
                }

                Thread.sleep(1000); // 입고 하는데 1초 걸림.

                System.out.println("책 입고 완료 : " + book);
                books[index].add(book); // 책을 추가
                books[index].notifyAll(); // books[index]에 접근을 기다리는 모든 스래드를 꺠움
                printList();
            }
            semaphore.release(); // 생산자 접근 해제
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }


    }

    public void sellBook(int index) {

        try {
            synchronized (books[index]) { // books[index]에 대한 모니터 락 획득.

                while (books[index].isEmpty()) { // 책이 존재하지 않는다면
                    books[index].wait(); // books[index]가 채워질때 까지 기다려야 하기 떄문에
                    // books[index]에 대한 접근을 허용하기 위해 모니터 락을 반환하고 , 스래드는 일시정지 시킴
                    //책이 입고되면 notifyAll()로 인해 정지해있던 스래드가 다시 모니터 락을 얻기 위해 경쟁함
                }

                Thread.sleep(100); // 책 출고시 0.1초 걸림.

                System.out.println("책 출고 완료 : " + books[index].get(0));
                books[index].remove(0); // 책 판매돼서 재고 1개 삭제 됨.
                books[index].notifyAll(); // // books[index]에 접근을 기다리는 모든 스레드를 꺠움
                printList();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException();
        }


    }

}