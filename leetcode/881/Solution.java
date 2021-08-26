import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        int left = 0;
        int right = n-1;
        int ans = 0;
        while (left <= right) {
            int rightWeight = people[right];
            int leftWeight = people[left];
            if (rightWeight + leftWeight <= limit) {
                ans++;
                left++;
                right--;
            } else {
                ans++;
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] people = {3,2,2,1};
        int limit = 3;
        System.out.println("test: " + sol.numRescueBoats(people, limit));
    }
}