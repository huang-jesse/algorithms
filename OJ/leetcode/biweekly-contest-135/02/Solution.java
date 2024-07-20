class Solution {
    public int minimumLength(String s) {
        int n = s.length();
        int[] counter = new int[26];
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            counter[cur - 'a']++;
        }
        int ans = 0;
        for (int count : counter) {
            if (count >= 3) {
                if (count % 2 == 0) {
                    ans += 2;
                } else {
                    ans += 1;
                }
            } else {
                ans += count;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abaacbcbb";
        System.out.println("test: " + sol.minimumLength(s));
    }
}