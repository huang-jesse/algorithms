import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagramsArrSol {

    /**
     * n: length of the strs
     * m: length of every str in strs
     * total time complexity: O(n*m)
     * total space complexity: O(n)
    **/
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.emptyList();
        // space complexity O(n)
        Map<IntArray, List<String>> mapStrs = new HashMap<>();

        // time complexity O(n*m)
        for(String str : strs) {
            // space complexity O(26)
            IntArray intArr = new IntArray();
            int strLen = str.length();
            // time complexity O(m)
            for (int i = 0; i < strLen; i++) {
                int charCode = str.charAt(i)-97;
                intArr.set(charCode, intArr.get(charCode)+1);
            }

            List<String> strList = mapStrs.get(intArr);
            if (strList == null) {
                strList = new ArrayList<>();
                mapStrs.put(intArr, strList);
            }
            strList.add(str);
        }
        // time complexity O(n)
        return mapStrs.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        GroupAnagramsArrSol sol = new GroupAnagramsArrSol();
        // Set parameters
        String[] testArr = new String[] {"a", "z", "a", "az", "za", "aa", "zz"};
        // Do test
        List<List<String>> test = sol.groupAnagrams(testArr);
        System.out.println(test);
    }
}