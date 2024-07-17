import java.util.TreeMap;

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int num : arr) {
            tm.put(num, tm.getOrDefault(num, 0) + 1);
        }
        int leftMax = -1;
        for (int num : arr) {
            int count = tm.get(num);
            if (count == 1) {
                tm.remove(num);
            } else {
                tm.put(num, count-1);
            }

            if (num > leftMax) {
                leftMax = num;
            }
            if (tm.isEmpty() || leftMax <= tm.firstKey()) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] arr = {2,4,1,3,4};
        int[] arr = {2,1,3,4,4};
        System.out.println("test: " + sol.maxChunksToSorted(arr));
    }
}