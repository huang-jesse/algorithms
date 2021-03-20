import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class GroupAnagrams {
    /**
     * n: length of the strs
     * m: length of every str in strs
     * total time complexity: O(n*(mlogm))
     * total space complexity: O(n)
    **/
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.emptyList();
        // space complexity O(n)
        Map<String, List<String>> mapStrs = new HashMap<>();

        // time complexity O(n*(mlogm))
        for(String str : strs) {
            // space complexity O(m)
            char[] charArr = str.toCharArray();
            // time complexity O(mlogm)
            Arrays.sort(charArr);
            String sortedStr = new String(charArr);

            List<String> strList = mapStrs.get(sortedStr);
            if (strList == null) {
                strList = new ArrayList<>();
                mapStrs.put(sortedStr, strList);
            }
            strList.add(str);
        }
        // time complexity O(n)
        return mapStrs.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        GroupAnagrams sol = new GroupAnagrams();
        // Set parameters
        String[] testArr = new String[] {"abc", "bdc", "bca"};
        // Do test
        List<List<String>> test = sol.groupAnagrams(testArr);
        System.out.println(test);
    }
}