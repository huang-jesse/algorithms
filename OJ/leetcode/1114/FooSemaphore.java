import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class FooSemaphore {
    private Semaphore two = new Semaphore(0);
    private Semaphore three = new Semaphore(0);

    public FooSemaphore() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        two.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        Runnable printFirst = new Runnable() {
            @Override
            public void run() {
                System.out.println("printFirst");
            }
        };
        Runnable printSecond = new Runnable() {
            @Override
            public void run() {
                System.out.println("printSecond");
            }
        };
        Runnable printThird = new Runnable() {
            @Override
            public void run() {
                System.out.println("printThird");
            }
        };
        FooSemaphore foo = new FooSemaphore();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable runFirst = new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first(printFirst);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Runnable runSecond = new Runnable() {
            @Override
            public void run() {
                try {
                    foo.second(printSecond);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Runnable runThird = new Runnable() {
            @Override
            public void run() {
                try {
                    foo.third(printThird);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        executor.submit(runFirst);
        executor.submit(runSecond);
        executor.submit(runThird);
    }
}