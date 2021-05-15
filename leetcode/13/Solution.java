import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int curValue = symbolValues.get(s.charAt(i));
            if (i < n - 1 && curValue < symbolValues.get(s.charAt(i+1))) {
                ans -= curValue;
            } else {
                ans += curValue;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "MCMXCIV";
        System.out.println("test: " + sol.romanToInt(s));
    }
}