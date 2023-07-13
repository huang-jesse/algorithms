import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Foo {
    private final CountDownLatch countDownLatchSecond = new CountDownLatch(1);
    private final CountDownLatch countDownLatchThird = new CountDownLatch(1);
    public Foo() {

    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatchSecond.countDown();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        countDownLatchSecond.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatchThird.countDown();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        countDownLatchThird.await();
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
        Foo foo = new Foo();
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