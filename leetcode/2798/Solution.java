class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ans = 0;
        for (int hour : hours) {
            if (hour >= target) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] hours = {0,1,2,3,4};
        int target = 2;
        System.out.println("test: " + sol.numberOfEmployeesWhoMetTarget(hours, target));
    }
}