import java.util.Random;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompareString {
    public static double time(String alg, String[] a) {
        Stopwatch timer = new Stopwatch();

        if (alg.equals("GroupAnagrams"))
            GroupAnagrams.groupAnagrams(a);
        if (alg.equals("GroupAnagramsArrSol"))
            GroupAnagramsArrSol.groupAnagrams(a);
        if (alg.equals("GroupAnagramsMapSol"))
            GroupAnagramsMapSol.groupAnagrams(a);
        if (alg.equals("GroupAnagramsBuckets"))
            GroupAnagramsBuckets.groupAnagrams(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        String[] a = new String[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = randomString();
            total += time(alg, a);
        }
        return total;
    }

    public static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        int targetLength = StdRandom.uniform(100, 1000);
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .limit(targetLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
        return generatedString;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);

        StdOut.println("t1: " + t1);
        StdOut.println("t2: " + t2);
        StdOut.println();
        StdOut.printf("For %d random String\n %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}