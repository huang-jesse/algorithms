import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class Solution {
    private int CAP = 10000;
    private List<Integer> auxList = new ArrayList<>();
    private int sumOfWeight;
    private Supplier<IntStream> randomInts;

    public Solution(int[] w) {
        int n = w.length;
        this.sumOfWeight = Arrays.stream(w).sum();
        for (int id = 0; id < n; id++) {
            addToAuxList(id, w[id]);
        }

        Random random = new Random();
        this.randomInts =  () -> random.ints(0, this.auxList.size());
    }

    private void addToAuxList(int id, int weight) {
        double p = (double)weight / this.sumOfWeight;
        int addNum = (int)Math.ceil(p * CAP);
        while (addNum > 0) {
            this.auxList.add(id);
            addNum--;
        }
    }
    
    public int pickIndex() {
        int index = randomInts.get().findAny().getAsInt();
        return this.auxList.get(index);
    }

    public static void main(String[] args) {
        int[] w = {1,3};
        Solution sol = new Solution(w);
        for (int i = 0; i < 5; i++) {
            System.out.println("pick: " + sol.pickIndex());
        }
    }
}