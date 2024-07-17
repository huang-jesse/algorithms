import java.util.ArrayList;
import java.util.List;

class Solution {
    public int calPoints(String[] ops) {
        List<Integer> points = new ArrayList<>();
        for (String curStr : ops) {
            int lastIndex = points.size() - 1;
            if (curStr.equals("C")) {
                points.remove(lastIndex);
            } else if (curStr.equals("D")) {
                points.add(points.get(lastIndex) * 2);
            } else if (curStr.equals("+")) {
                points.add(points.get(lastIndex) + points.get(lastIndex-1));
            } else {
                points.add(Integer.parseInt(curStr));
            }
        }
        return points.stream().reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] ops = {"5","2","C","D","+"};
        System.out.println("test: " + sol.calPoints(ops));
    }
}