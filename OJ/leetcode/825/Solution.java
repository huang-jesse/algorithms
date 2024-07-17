import java.util.Arrays;

class Solution {
    public int numFriendRequests(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {
                ++left;
            }
            while (right + 1 < n && ages[right + 1] <= age) {
                ++right;
            }
            ans += right - left;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] ages = {16,16};
        // int[] ages = {16,17,18};
        System.out.println("test: " + sol.numFriendRequests(ages));
    }
}