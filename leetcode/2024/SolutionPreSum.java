import java.util.HashMap;
import java.util.Map;

class SolutionPreSum {
    private static final char STR_F = 'F';
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int[] preSumF = new int[n];
        Map<Integer, Integer> preSumFMap = new HashMap<>();
        int[] preSumT = new int[n];
        Map<Integer, Integer> preSumTMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char cur = answerKey.charAt(i);
            if (i > 0) {
                preSumF[i] = preSumF[i-1];
                preSumT[i] = preSumT[i-1];
            }
            if (cur == STR_F) {
                preSumF[i]++;
            } else {
                preSumT[i]++;
            }
            if (!preSumFMap.containsKey(preSumF[i])) {
                if (preSumF[i] == 0) {
                    preSumFMap.put(preSumF[i], -1);
                } else {
                    preSumFMap.put(preSumF[i], i);
                }
            }
            if (!preSumTMap.containsKey(preSumT[i])) {
                if (preSumT[i] == 0) {
                    preSumTMap.put(preSumT[i], -1);
                } else {
                    preSumTMap.put(preSumT[i], i);
                }
            }
        }

        int ans = 1;
        for (int i = 1; i < n; i++) {
            // consecutive T
            int preFCount = preSumF[i] - k;
            int consecutiveT = i - preSumFMap.getOrDefault(preFCount, -1);
            // consecutive F
            int preTCount = preSumT[i] - k;
            int consecutiveF = i - preSumTMap.getOrDefault(preTCount, -1);
            ans = Math.max(ans, Math.max(consecutiveF, consecutiveT));
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionPreSum sol = new SolutionPreSum();
        String answerKey = "TTFTTTTTFT";
        int k = 1;
        System.out.println("test: " + sol.maxConsecutiveAnswers(answerKey, k));
    }
}