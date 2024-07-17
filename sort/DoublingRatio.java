import java.util.Random;

public class DoublingRatio {
    public static double timeTrial(int N) {
        Random random = new Random(37);
        int MAX = Integer.MAX_VALUE;
        // int MAX = 1000000;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = random.nextInt(MAX);
        Timer timer = new Timer();
        // int cnt = ThreeSum.count(a);
        // Quick.sort(a);
        Bubble.sort(a);
        return (double)timer.elapsed();
    }

    public static void main(String[] args) {
        double prev = timeTrial(125);
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            System.out.printf("%6d %7.1f ", N, time);
            System.out.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }
}