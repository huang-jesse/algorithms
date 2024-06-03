import java.util.Arrays;

class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int round = 0;
        int remain = candies;
        int start = 1;
        int end = num_people;
        while (remain >= ((long)num_people * (start + end) / 2)) {
            remain -= (long)num_people * (start + end) / 2; // Sn
            round++;
            start = end + 1;
            end = start + num_people - 1;
        }
        for (int i = 0; i < num_people; i++) {
            start = i + 1;
            end = num_people * (round - 1) + i + 1;
            ans[i] = round * (start + end) / 2;
            if (remain > 0) {
                int distribute = Math.min(remain, end + num_people);
                ans[i] += distribute;
                remain -= distribute;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int candies = 10;
        int numPeople = 3;
        System.out.println("test: " + Arrays.toString(sol.distributeCandies(candies, numPeople)));
    }
}