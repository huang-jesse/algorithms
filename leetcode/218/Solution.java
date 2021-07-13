import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Comparator<int[]> descCompare = (a, b) -> b[1] - a[1];
        PriorityQueue<int[]> maxPq = new PriorityQueue<int[]>(descCompare);
        List<Integer> boundaries = new ArrayList<Integer>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = buildings.length;
        int index = 0;
        for (int boundary : boundaries) {
            // boundary >= lefti & boundary < righti
            while (index < n && buildings[index][0] <= boundary) {
                int rightBoundary = buildings[index][1];
                int height = buildings[index][2];
                maxPq.offer(new int[]{rightBoundary, height});
                index++;
            }
            // if boundary >= righti, than boundary is not in the range
            while (!maxPq.isEmpty() && maxPq.peek()[0] <= boundary) {
                maxPq.poll();
            }

            int maxn = maxPq.isEmpty() ? 0 : maxPq.peek()[1];
            int indexOfLast = ans.size() - 1;
            if (ans.size() == 0 || maxn != ans.get(indexOfLast).get(1)) {
                ans.add(Arrays.asList(boundary, maxn));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println("test: " + sol.getSkyline(buildings));
    }
}