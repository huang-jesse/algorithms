import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[] decode(int[] encoded) {
        int len = encoded.length;
        int n = len + 1;
        // get XOR result for each element of perm (perm[0] ~ perm[n-1])
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }
        // get XOR result for each element of perm (perm[1] ~ perm[n-1])
        int odd = 0;
        for (int i = 1; i < len; i += 2) {
            odd ^= encoded[i];
        }
        // get first element of perm
        int[] perm = new int[n];
        int first = total ^ odd;
        perm[0] = first;
        for (int i = 1; i < n; i++) {
            perm[i] = perm[i-1] ^ encoded[i-1];
        }

        return perm;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] encoded = new int[] {6,5,4,6};
        int[] ans = sol.decode(encoded);
        List<Integer> ansList = Arrays.stream(ans).boxed().collect(Collectors.toList());
        System.out.println("test: " + ansList);
    }
}