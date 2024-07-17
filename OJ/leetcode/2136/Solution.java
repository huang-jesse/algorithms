import java.util.Arrays;

class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        // desc order by growTime
        Arrays.sort(indexes, (o1, o2) -> growTime[o2] - growTime[o1]);
        int plantTimePreSum = 0;
        int ans = 0;
        for (int index : indexes) {
            plantTimePreSum += plantTime[index];
            ans = Math.max(ans, plantTimePreSum + growTime[index] + 1);
        }
        return ans - 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] plantTime = {1,2,3,2};
        int[] glowTime = {2,1,2,1};
        // int[] plantTime = {1,4,3};
        // int[] glowTime = {2,3,1};
        // int[] plantTime = {1};
        // int[] glowTime = {1};
        System.out.println("test: " + sol.earliestFullBloom(plantTime, glowTime));
    }
}