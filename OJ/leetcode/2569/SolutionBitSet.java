import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

class SolutionBitSet {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        long sum = Arrays.stream(nums2).mapToLong(num -> (long)num).sum();
        int n = nums1.length;
        BitSet bitSet = new BitSet(n);
        for (int i = 0; i < n; i++) {
            if (nums1[i] == 1) {
                bitSet.set(i);
            }
        }
        List<Long> resList = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                bitSet.flip(query[1], query[2] + 1);
            } else if (query[0] == 2) {
                int p = query[1];
                sum += (long)bitSet.cardinality() * p;
            } else {
                // query[0] == 3
                resList.add(sum);
            }
        }
        return resList.stream().mapToLong(l -> l).toArray();
    }

    public static void main(String[] args) {
        SolutionBitSet sol = new SolutionBitSet();
        // int[] nums1 = {1,0,1};
        // int[] nums2 = {0,0,0};
        // int[][] queries = {{1,1,1},{2,1,0},{3,0,0}};
        int[] nums1 = {1,0,1};
        int[] nums2 = {44,28,35};
        int[][] queries = {{1,0,1},{2,10,0},{2,2,0},{2,7,0},{3,0,0},{3,0,0},{1,2,2},{1,1,2},{2,1,0},{1,0,2},{1,2,2},{1,0,2},{3,0,0},{1,1,2},{3,0,0},{1,0,1},{2,21,0},{1,0,1},{2,26,0},{1,1,1}};
        System.out.println("test: " + Arrays.stream(sol.handleQuery(nums1, nums2, queries)).boxed().collect(Collectors.toList()));
    }
}