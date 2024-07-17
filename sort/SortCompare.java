import java.util.Random;

public class SortCompare {
    public static double time(String alg, Double[] a) {
        Timer timer = new Timer();

        if (alg.equals("MergeOriginal"))
            MergeOriginal.sort(a);
        if (alg.equals("Merge"))
            Merge.sort(a);
        if (alg.equals("QuickOriginal"))
            QuickOriginal.sort(a);
        if (alg.equals("Quick"))
            Quick.sort(a);
        if (alg.equals("Quick3Way"))
            Quick3Way.sort(a);
        return timer.elapsed();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        Random random = new Random(37);
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = random.nextDouble();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);
        System.out.println();
        System.out.printf("For %d random Doubles\n %s is", N, alg1);
        System.out.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}