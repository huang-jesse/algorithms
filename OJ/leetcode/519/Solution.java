import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

class Solution {
    private int m;
    private int n;
    private int total;
    private Map<Integer, Integer> indexMap = new HashMap<>();
    private Random random = new Random();
    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m*n;
    }

    public int[] flip() {
        if (total <= 0)
            return null;
        int randomIndex = random.nextInt(total);
        this.total--;
        // get realIndex from indexMap
        int realIndex = indexMap.getOrDefault(randomIndex, randomIndex);
        // convert array index to matrix index
        int[] matrixIndex = toMatrixIndex(realIndex, n);
        // put the realIndex of total to the randomIndex in map
        indexMap.put(randomIndex, indexMap.getOrDefault(total, total));
        return matrixIndex;
    }

    private static int[] toMatrixIndex(int index, int n) {
        return new int[] {index/n, index%n};
    }

    public void reset() {
        this.total = m * n;
        this.indexMap.clear();
    }

    public static void main(String[] args) {
        int m = 10000;
        int n = 10000;
        Solution sol = new Solution(m, n);
        List<Integer> flipedIndex = Arrays.stream(sol.flip()).boxed().collect(Collectors.toList());
        System.out.println("flipedIndex: " + flipedIndex);
        List<Integer> flipedIndex2 = Arrays.stream(sol.flip()).boxed().collect(Collectors.toList());
        System.out.println("flipedIndex: " + flipedIndex2);
        sol.reset();
    }
}