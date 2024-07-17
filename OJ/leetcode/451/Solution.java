import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character cur = s.charAt(i);
            Integer count = charMap.getOrDefault(cur, 0);
            charMap.put(cur, count+1);
        }

        List<Map.Entry<Character, Integer>> charEntrys = charMap.entrySet().stream().collect(Collectors.toList());
        Comparator<Map.Entry<Character, Integer>> compareByCountReverse = (o1, o2) -> o2.getValue().compareTo(o1.getValue());
        charEntrys.sort(compareByCountReverse);

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Character, Integer> charEntry : charEntrys) {
            int count = charEntry.getValue();
            char cur = charEntry.getKey();
            while (count > 0) {
                sb.append(cur);
                count--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "Aabb-=";
        System.out.println("test: " + sol.frequencySort(s));
    }
}