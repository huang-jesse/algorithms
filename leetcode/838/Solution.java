import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
    private static final char LEFT = 'L';// -1
    private static final int LEFT_VAL = -1;
    private static final char RIGHT = 'R';// 1
    private static final int RIGHT_VAL = 1;
    private static final char INIT = '.';// 0
    private static final int INIT_VAL = 0;
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] chars = dominoes.toCharArray();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char cur = chars[i];
            if (cur == LEFT) {
                queue.offer(new int[]{i, LEFT_VAL});
            } else if (cur == RIGHT) {
                queue.offer(new int[]{i, RIGHT_VAL});
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, Integer> nextMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curIndex = cur[0];
                int curVal = cur[1];
                if (curVal == LEFT_VAL && curIndex > 0 && chars[curIndex - 1] == INIT) {
                    Integer nextIndex = curIndex - 1;
                    Integer nextVal= nextMap.getOrDefault(nextIndex, INIT_VAL) + LEFT_VAL;
                    nextMap.put(nextIndex, nextVal);
                } else if (curVal == RIGHT_VAL && curIndex < n-1 && chars[curIndex + 1] == INIT) {
                    Integer nextIndex = curIndex + 1;
                    Integer nextVal = nextMap.getOrDefault(nextIndex, INIT_VAL) + RIGHT_VAL;
                    nextMap.put(nextIndex, nextVal);
                }
            }
            for (Map.Entry<Integer, Integer> nextInfo : nextMap.entrySet()) {
                if (nextInfo.getValue() != INIT_VAL) {
                    queue.offer(new int[]{nextInfo.getKey(), nextInfo.getValue()});
                    chars[nextInfo.getKey()] = nextInfo.getValue() == RIGHT_VAL ? RIGHT : LEFT;
                }
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String donimoes = ".L.R...LR..L..";
        // LL.RR.LLRRLL..
        System.out.println("test: " + sol.pushDominoes(donimoes));
    }
}