import java.util.concurrent.atomic.AtomicBoolean;

class SolutionAtomic {
    static class FooBar {
        private int n;
        private final AtomicBoolean isBar = new AtomicBoolean();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            // make sure foo() execute first
            for (int i = 0; i < n; i++) {
                while (true) {
                    if (!isBar.get()) {
                        // printFoo.run() outputs "foo". Do not change or remove this line.
                        printFoo.run();
                        isBar.set(true);
                        break;
                    }
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            // wait util foo() execute first
            for (int i = 0; i < n; i++) {
                while (true) {
                    if (isBar.get()) {
                        // printBar.run() outputs "bar". Do not change or remove this line.
                        printBar.run();
                        isBar.set(false);
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        SolutionAtomic.FooBar fooBar = new SolutionAtomic.FooBar(n);
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