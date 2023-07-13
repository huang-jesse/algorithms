import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FooBoolean {
    private Boolean firstJobDone;
    private boolean secondJobDone;
    public FooBoolean() {
        firstJobDone = false;
        secondJobDone = false;
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJobDone = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!firstJobDone) {
            // waiting for the first job to be done.
            wait();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondJobDone = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!secondJobDone) {
            // waiting for the second job to be done.
            wait();
        }
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
        FooBoolean foo = new FooBoolean();
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