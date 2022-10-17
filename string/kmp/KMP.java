import java.util.ArrayList;
import java.util.List;

class KMP {
    private int[] pmt;
    private String p;

    public KMP(String p) {
        this.pmt = generatePMT(p);
        this.p = p;
    }

    private int[] generatePMT(String p) {
        int m = p.length();
        int[] pmt = new int[m];
        pmt[0] = 0;
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pmt[j - 1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                j++;
            }
            pmt[i] = j;
        }
        return pmt;
    }

    public int search(String s) {
        int n = s.length();
        int m = p.length();
        if (n < m) {
            return -1;
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = pmt[j - 1];
            }
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == m) {
                // match
                return i - j + 1;
            }
        }
        return -1;
    }

    public List<Integer> seatchAll(String s) {
        List<Integer> matchIndexes = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if (n < m) {
            return matchIndexes;
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = pmt[j - 1];
            }
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == m) {
                // match
                matchIndexes.add(i - j + 1);
                j = pmt[j - 1];
            }
        }
        return matchIndexes;
    }

    public static void main(String[] args) {
        String p = "ababcabaa";
        KMP kmp = new KMP(p);
        String s = "abababcabaa";
        System.out.println("index: " + kmp.search(s));
        System.out.println("index list: " + kmp.seatchAll(s));
    }
}