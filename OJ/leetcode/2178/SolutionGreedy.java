import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SolutionGreedy {
    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) {
            return Collections.emptyList();
        }
        List<Long> ans = new ArrayList<>();
        long evenNum = 2L;
        while (evenNum <= finalSum) {
            finalSum -= evenNum;
            ans.add(evenNum);
            evenNum += 2L;
        }
        ans.set(ans.size() - 1, ans.get(ans.size() - 1) + finalSum);
        return ans;
    }

    public static void main(String[] args) {
        SolutionGreedy sol = new SolutionGreedy();
        // long finalSum = 7L;
        long finalSum = 28L;
        System.out.println("test: " + sol.maximumEvenSplit(finalSum));
    }
}