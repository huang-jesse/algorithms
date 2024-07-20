import java.util.concurrent.Semaphore;

class SolutionSemaphore {
    static class FooBar {
        private int n;
        private final Semaphore fooSemaphore = new Semaphore(0);
        private final Semaphore barSemaphore = new Semaphore(0);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            // make sure foo() execute first
            for (int i = 0; i < n; i++) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                barSemaphore.release();
                fooSemaphore.acquire();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            // wait util foo() execute first
            for (int i = 0; i < n; i++) {
                barSemaphore.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooSemaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        SolutionSemaphore.FooBar fooBar = new SolutionSemaphore.FooBar(n);
        Runnable printFooTask = new Runnable() {
            public void run() {
                System.out.print("foo");
            }
        };
        Runnable printBarTask = new Runnable() {
            public void run() {
                System.out.print("bar");
            }
        };
        Thread fooThread = new Thread(new Runnable() {
            public void run() {
                try {
                    fooBar.foo(printFooTask);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread barThread = new Thread(new Runnable() {
            public void run() {
                try {
                    fooBar.bar(printBarTask);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        barThread.start();
        fooThread.start();
        try {
            fooThread.join(1000);
            barThread.join(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}