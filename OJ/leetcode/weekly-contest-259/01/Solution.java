class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String s : operations) {
            if (s.equals("++X") || s.equals("X++")) {
                ans++;
            } else {
                ans--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] operations = {"--X","X++","X++"};
        System.out.println("test: " + sol.finalValueAfterOperations(operations));
    }
}