class Solution {
    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length();
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            counter[i + 1] = counter[i];
            if (s.charAt(i) == '0') counter[i + 1]++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int zeroCount = counter[j + 1] - counter[i];
                int oneCount = j - i + 1 - zeroCount;
                if (zeroCount <= k || oneCount <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "10101";
        int k = 1;
        System.out.println("test: " + sol.countKConstraintSubstrings(s, k));
    }
}