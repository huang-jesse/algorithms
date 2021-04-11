import java.util.stream.IntStream;

class Solution {
    public int reinitializePermutation(int n) {
        int step = 0;

        Integer[] perm = IntStream.rangeClosed(0, n-1).boxed().toArray(Integer[]::new);
        while (step == 0 || !isInitialize(perm)) {
            Integer[] arr = new Integer[n];
            for (int i = 0; i < n; i++) {
                if (i%2 == 0) {
                    arr[i] = perm[i/2];
                } else {
                    arr[i] = perm[n/2 + (i-1)/2];
                }
            }
            perm = arr;
            // printArr(perm);
            step++;
        }
        return step;
    }

    private boolean isInitialize(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i-1]+1) return false;
        }
        return true;
    }

    private void printArr(Integer[] a) {
        for (Integer i : a) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Set parameters
        int n = 1000;
        // Do test
        int test = sol.reinitializePermutation(n);
        System.out.println(test);
    }
}