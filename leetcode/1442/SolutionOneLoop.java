import java.util.HashMap;
import java.util.Map;

class SolutionOneLoop {
    public int countTriplets(int[] arr) {
        int ans = 0;
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i-1] ^ arr[i-1];
        }
        // if s(i) = s(k+1), s(0)=0
        // then -> ans = (k - i1) + (k - i2) + (k - i3) ... (k - im) = m*k - (i1 + i2 + ... + im)
        Map<Integer, Integer> count = new HashMap<>();// m
        Map<Integer, Integer> sum = new HashMap<>();// i1 + i2 + ... + im
        // loop
        for (int k = 0; k < n; k++) {
            if (count.containsKey(s[k+1])) {
                int m = count.get(s[k+1]);
                int total = sum.get(s[k+1]);
                ans += m*k - total;
            }
            int i = k;
            count.put(s[i], count.getOrDefault(s[i], 0) + 1);
            sum.put(s[i], sum.getOrDefault(s[i], 0) + i);
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOneLoop sol = new SolutionOneLoop();
        int[] arr = new int[]{7,11,12,9,5,2,7,17,22};
        System.out.println("test: " + sol.countTriplets(arr));
    }
}