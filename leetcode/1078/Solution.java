import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        int n = words.length;
        List<String> ansList = new ArrayList<>();
        for (int i = 0; i < n-2; i++) {
            if (words[i].equals(first) && words[i+1].equals(second)) {
                ansList.add(words[i+2]);
            }
        }
        String[] ansArr = new String[ansList.size()];
        ansArr = ansList.toArray(ansArr);
        return ansArr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String text = "alice is a good girl she is a good student";
        String first = "a";
        String second = "good";
        System.out.println("test: " + Arrays.stream(sol.findOcurrences(text, first, second)).collect(Collectors.toList()));
    }
}