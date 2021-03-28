import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        Map<String, String> knowMap = new HashMap<>(knowledge.size());
        for (List<String> know : knowledge) {
            knowMap.put(know.get(0), know.get(1));
        }

        StringBuilder replacedStr = new StringBuilder();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char charStr = s.charAt(i);
            if (charStr == '(') {
                // jump to next i
                key.append(s.charAt(++i));
                continue;
            }
            if (charStr == ')') {
                String value = knowMap.get(key.toString());
                replacedStr.append(value == null ? "?" : value);
                key = new StringBuilder();
                continue;
            }
            if (key.length() != 0) {
                key.append(charStr);
                continue;
            }
            replacedStr.append(charStr);
        }
        return replacedStr.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Set parameters
        String s = "(a)(b)";
        String[][] ss = new String[][]{{"a","b"}, {"b","a"}};
        List<List<String>> testArr = new ArrayList<>();
        for (String[] arr : ss) {
            testArr.add(Arrays.asList(arr));
        }
        // Do test
        String test = sol.evaluate(s, testArr);
        System.out.println(test);
    }
}