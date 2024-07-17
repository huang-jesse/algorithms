import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String cur = words[i];
            countMap.put(cur, countMap.getOrDefault(cur, 0) + 1);
        }
        List<WordFrequency> wf = new ArrayList<>();
        countMap.forEach((key, value) -> {
            wf.add(new WordFrequency(key, value));
        });

        Comparator<WordFrequency> comparator1 = (o1, o2)-> Integer.compare(o2.frequecy, o1.frequecy);
        Comparator<WordFrequency> comparator2 = (o1, o2)-> o1.word.compareTo(o2.word);
        Collections.sort(wf, comparator1.thenComparing(comparator2));

        List<String> ans = wf.stream().map(w -> w.word).limit(k).collect(Collectors.toList());
        return ans;
    }

    private class WordFrequency {
        String word;
        int frequecy;

        WordFrequency(String word, int frequency) {
            this.word = word;
            this.frequecy = frequency;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        System.out.println("test: " + sol.topKFrequent(words, k));
    }
}