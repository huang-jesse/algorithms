import java.util.HashSet;
import java.util.Set;

class Solution {
    public int transportationHub(int[][] path) {
        Set<Integer> outSet = new HashSet<>();
        Set<Integer> pointSet = new HashSet<>();
        int[] inCounter = new int[1001];
        for (int[] curPath : path) {
            outSet.add(curPath[0]);
            pointSet.add(curPath[0]);
            pointSet.add(curPath[1]);
            inCounter[curPath[1]]++;
        }
        int size = pointSet.size();
        for (int i = 0; i < 1001; i++) {
            if (inCounter[i] == size - 1 && !outSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] path = {};
        System.out.println("test: " + sol.transportationHub(path));
    }
}