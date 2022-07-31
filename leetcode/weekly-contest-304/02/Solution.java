class Solution {
    public int maximumGroups(int[] grades) {
        int n = grades.length;
        int ans = 0;
        int groupSize = 1;
        while (n >= groupSize) {
            n = n - groupSize;
            ans++;
            groupSize++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] grades = {10,6,12,7,3,5};
        int[] grades = {1,1};
        System.out.println("test: " + sol.maximumGroups(grades));
    }
}