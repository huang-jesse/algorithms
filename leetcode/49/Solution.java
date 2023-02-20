import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (String str : strs) {
            String formatedStr = formatStr(str);
            List<String> strList = anagramsMap.getOrDefault(formatedStr, new ArrayList<>());
            strList.add(str);
            anagramsMap.put(formatedStr, strList);
        }
        return anagramsMap.values().stream().collect(Collectors.toList());
    }

    private String formatStr(String str) {
        if (str == "") {
            return "";
        }
        int[] counter = new int[26];
        for (char cur : str.toCharArray()) {
            counter[cur - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int count = counter[i];
            if (count == 0) continue;
            char cur = (char)('a' + i);
            sb.append(cur);
            sb.append(count);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String[] strs = {"eat","tea","tan","ate","nat","bat","bat"};
        String[] strs = {"ape","pea","taz"};
        System.out.println("test: " + sol.groupAnagrams(strs));
    }
}