import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        int n = student_id.length;
        int[] points = new int[n];
        Map<String, Integer> words = new HashMap<>();
        for (String positive : positive_feedback) {
            words.put(positive, 3);
        }
        for (String negative : negative_feedback) {
            words.put(negative, -1);
        }

        for (int i = 0; i < n; i++) {
            String currentReport = report[i];
            String[] feedbacks = currentReport.split(" ");
            for (int j = 0; j < feedbacks.length; j++) {
                points[i] += words.getOrDefault(feedbacks[j], 0);
            }
        }
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (o1, o2) -> {
            int firstCompare = points[o2] - points[o1];
            if (firstCompare == 0) {
                return student_id[o1] - student_id[o2];
            }
            return firstCompare;
        });
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(student_id[indexes[i]]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] positive_feedback = {"smart","brilliant","studious"};
        String[] negative_feedback = {"not"};
        String[] report = {"this student is studious","the student is smart"};
        int[] student_id = {1, 2};
        int k = 2;
        System.out.println("test: " + sol.topStudents(positive_feedback, negative_feedback, report, student_id, k));
    }
}