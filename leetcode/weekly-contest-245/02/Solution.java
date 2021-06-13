import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int left = 0;
        int right = removable.length;
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (isSubSerialStr(sChars, pChars, removable, mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private static boolean isSubSerialStr(char[] sChars, char[] pChars, int[] removable, int k) {
        if (k == 0) return true;
        Set<Integer> removeSet = new HashSet<>();
        for (int i = 0; i < k; i++) {
            removeSet.add(removable[i]);
        }
        int sIndex = 0;
        int pIndex = 0;
        while (sIndex < sChars.length && pIndex < pChars.length) {
            if (!removeSet.contains(sIndex) && sChars[sIndex] == pChars[pIndex]) {
                sIndex++;
                pIndex++;
            } else {
                sIndex++;
            }
        }
        return pIndex == pChars.length;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcacb";
        String p = "ab";
        int[] removable = {3,1,0};
        // String s = "qlevcvgzfpryiqlwy";
        // String p = "qlecfqlw";
        // int[] removable = {12,5};
        System.out.println("test: " + sol.maximumRemovals(s, p, removable));
    }
}