import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionBinarySearch {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[0] - o2[0]);
        List<Integer> arr = new ArrayList<>();
        for (int[] pair : pairs) {
            int first = pair[0];
            int second = pair[1];
            if (arr.isEmpty() || first > arr.get(arr.size() - 1)) {
                arr.add(second);
            } else {
                // set to right position
                int index = arr.size() - 1;
                arr.set(index, Math.min(arr.get(index), second));
            }
        }
        return arr.size();
    }

    public static void main(String[] args) {
        SolutionBinarySearch sol = new SolutionBinarySearch();
        // int[][] pairs = {{1,2}, {2,3}, {2,4}};
        // int[][] pairs = {{1,2}, {2,3}, {3,10}, {4,5}, {6,7}};
        int[][] pairs = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
        // int[][] pairs = {{1,2}, {2,4}, {3,4}, {3,5}, {5,7}, {5,8}};
        // int[][] pairs = {{1,2}};
        // int[][] pairs = {{1,2}, {2,3}, {3,4}};
        System.out.println("test: " + sol.findLongestChain(pairs));
    }
}