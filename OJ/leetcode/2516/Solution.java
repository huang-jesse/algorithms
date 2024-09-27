class Solution {
    public int takeCharacters(String s, int k) {
        if (k == 0) return 0;
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] counter = new int[3];
        for (int j = n - 1; j >= 0; j--) {
            counter[chars[j] - 'a']++;
        }
        if (counter[0] < k || counter[1] < k || counter[2] < k) return -1;

        int j = 0;
        while (j < n && counter[chars[j] - 'a'] > k) {
            counter[chars[j] - 'a']--;
            j++;
        }
        int ans = n - j;

        int i = 0;
        while (i < n) {
            counter[chars[i] - 'a']++;
            while (j < n && counter[chars[j] - 'a'] > k) {
                counter[chars[j] - 'a']--;
                j++;
            }
            ans = Math.min(ans, n - (j - i - 1));
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aabaaaacaabc";
        int k = 2; // res: 8
        System.out.println("test: " + sol.takeCharacters(s, k));
    }
}