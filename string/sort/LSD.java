/**
 * Least significant digit first sort
 */
public class LSD {

    /**
     * @description: LSD Sort
     * @param a target array
     * @param k pre k char for sort base
     * @return
     */
    public static void sort(String[] a, int k) {
        int n = a.length;
        int r = 256; // max of char value
        for (int d = k-1; d >= 0; d--) {
            int[] aux = new int[n];
            for (int i = 0; i < n; i++) {
                aux[i] = a[i].charAt(d);
            }
            countingSort(a, aux, r);
        }
    }

    /**
     * Counting Sort
     * @param target
     * @param aux sort base of the target
     * @param k max of aux
     */
    private static void countingSort(String[] target, int[] aux, int r) {
        int n = target.length; // target length == aux length
        // count
        int[] count = new int[r+1];
        for (int i = 0; i < n; i++) {
            count[aux[i]+1]++;
        }
        // pre count
        for (int i = 1; i < r; i++) {
            count[i] += count[i-1];
        }

        // sort
        String[] bak = new String[n];
        for (int i = 0; i < n; i++) {
            int cur = aux[i];
            int sortedIndex = count[cur]++;
            bak[sortedIndex] = target[i];
        }

        // write sorted results to target
        for (int i = 0; i < n; i++) {
            target[i] = bak[i];
        }
    }

    private static void show(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i-1]) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845",
                      "4JZY524", "1ICK750", "3CIO720", "1OHV845", "1OHV845",
                      "2RLA629", "2RLA629", "3ATW723"};
        int k = 7;
        sort(a, k);
        System.out.println("Is sorted: " + isSorted(a));
        show(a);
    }
}