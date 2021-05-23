import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class SolutionPQ {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String cur = words[i];
            countMap.put(cur, countMap.getOrDefault(cur, 0) + 1);
        }
        Comparator<Map.Entry<String, Integer>> comparator1 = (o1, o2) -> o1.getValue().compareTo(o2.getValue());
        Comparator<Map.Entry<String, Integer>> comparator2 = (o1, o2) -> o2.getKey().compareTo(o1.getKey());
        // min heap priority
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(comparator1.thenComparing(comparator2));
        countMap.entrySet().forEach(entry -> {
            pq.offer(entry);
            // matian k size of priority queue
            if (pq.size() > k) {
                pq.poll();
            }
        });
        List<String> ans = new ArrayList<String>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        SolutionPQ sol = new SolutionPQ();
        String[] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        System.out.println("test: " + sol.topKFrequent(words, k));
    }
}