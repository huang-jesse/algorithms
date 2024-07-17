import java.util.ArrayList;
import java.util.List;

class Solution {
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        int[] dCounter = new int[n];
        for (int i = n-1; i >= 0; i--) {
            char cur = pattern.charAt(i);
            if (cur == 'D') {
                if (i < n-1) {
                    dCounter[i] = dCounter[i+1] + 1;
                } else {
                    dCounter[i] = 1;
                }
            }
        }

        List<Integer> numList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            numList.add(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int cnt : dCounter) {
            if (cnt == 0) {
                int minNum = numList.get(0);
                sb.append(minNum);
                numList.remove(0);
            } else {
                int num = numList.get(cnt);
                sb.append(num);
                numList.remove(cnt);
            }
        }
        sb.append(numList.get(0));
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String pattern = "IIIDIDDD";
        String pattern = "DDIIIDD";
        System.out.println("test: " + sol.smallestNumber(pattern));
    }
}