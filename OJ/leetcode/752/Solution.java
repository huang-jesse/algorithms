import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    static final String INIT_STATUS = "0000";
    public int openLock(String[] deadends, String target) {
        if (INIT_STATUS.equals(target)) {
            return 0;
        }
        Set<String> deadsSet = new HashSet<>(Arrays.asList(deadends));
        if (deadsSet.contains(INIT_STATUS)) {
            return -1;
        }
        Set<String> seen = new HashSet<>();
        Queue<String> statusQueue = new LinkedList<>();
        statusQueue.offer(INIT_STATUS);
        seen.add(INIT_STATUS);
        int step = 0;
        while (!statusQueue.isEmpty()) {
            step++;
            int currentSize = statusQueue.size();
            for (int i = 0; i < currentSize; i++) {
                String currentStatus = statusQueue.poll();
                List<String> nextStatusList = getNextStatusList(currentStatus);
                for (String nextStatus : nextStatusList) {
                    if (!seen.contains(nextStatus) && !deadsSet.contains(nextStatus)) {
                        if (target.equals(nextStatus)) {
                            return step;
                        }
                        statusQueue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    public List<String> getNextStatusList(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println("test " + sol.openLock(deadends, target));
    }
}