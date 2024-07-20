import java.util.concurrent.Semaphore;

class Solution {
    static class FooBar {
        private int n;
        private final Semaphore semaphore = new Semaphore(0);
        private final Object obj = new Object();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            synchronized(obj) {
                // make sure foo() execute first
                semaphore.release();
                obj.wait();
                for (int i = 0; i < n; i++) {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    obj.notify();
                    obj.wait();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            // wait util foo() execute first
            semaphore.acquire();
            synchronized(obj) {
                obj.notify();
                for (int i = 0; i < n; i++) {
                    obj.wait();
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    obj.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newFixedThreadPool(2);
        int n = 10;
        Solution.FooBar fooBar = new Solution.FooBar(n);
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