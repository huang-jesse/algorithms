import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class GroupAnagramsMapSol {
    /**
     * n: length of the strs
     * m: length of every str in strs
     * total time complexity: O(n*m)
     * total space complexity: O(n)
    **/
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.emptyList();
        // space complexity O(n)
        Map<Map<Integer, Integer>, List<String>> mapStrs = new HashMap<>();

        // time complexity O(n*m)
        for(String str : strs) {
            // space complexity O(26)
            Map<Integer, Integer> charCodeMap = new HashMap<>(26);
            int strLen = str.length();
            // time complexity O(m)
            for (int i = 0; i < strLen; i++) {
                Integer charCode = (int)str.charAt(i);
                Integer count = charCodeMap.get(charCode);
                if (count == null) {
                    charCodeMap.put(charCode, 1);
                } else {
                    charCodeMap.put(charCode, count+1);
                }
            }

            List<String> strList = mapStrs.get(charCodeMap);
            if (strList == null) {
                strList = new ArrayList<>();
                mapStrs.put(charCodeMap, strList);
            }
            strList.add(str);
        }
        // time complexity O(n)
        return mapStrs.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        GroupAnagramsMapSol sol = new GroupAnagramsMapSol();
        // Set parameters
        String[] testArr = new String[] {"cdb", "bdc", "bca"};
        // Do test
        List<List<String>> test = sol.groupAnagrams(testArr);
        System.out.println(test);
    }
}