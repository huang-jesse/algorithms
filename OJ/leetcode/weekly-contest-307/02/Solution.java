import java.util.TreeMap;

class Solution {
    public String largestPalindromic(String num) {
        int n = num.length();
        TreeMap<Character, Integer> tm = new TreeMap<>();
        char[] nums = num.toCharArray();
        for (int i = 0; i < n; i++) {
            tm.put(nums[i], tm.getOrDefault(nums[i], 0)+1);
        }
        if (tm.size() == 1 && tm.lastKey().equals('0')) {
            return "0";
        }

        Character mid = null;
        StringBuilder sb = new StringBuilder();
        while (!tm.isEmpty()) {
            Character maxKey = tm.lastKey();
            int count = tm.get(maxKey);
            if (maxKey.equals('0') && sb.length() == 0) {
                break;
            }
            if (count == 1) {
                if (mid == null) {
                    mid = maxKey;
                }
            } else {
                if (count % 2 == 1 && mid == null) {
                    mid = maxKey;
                }
                int times = count / 2;
                while (times > 0) {
                    times--;
                    sb.append(maxKey);
                }
            }
            tm.remove(maxKey);
        }
        StringBuilder ans = new StringBuilder(sb);
        if (mid != null) {
            ans.append(mid);
        }
        sb = sb.reverse();
        ans.append(sb.toString());
        return ans.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num = "444947137";
        System.out.println("test: " + sol.largestPalindromic(num));
    }
}