import java.util.HashMap;
import java.util.Map;

class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> xyMap = new HashMap<>();
    public DetectSquares() {
    }
    
    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        Map<Integer, Integer> yMap = xyMap.getOrDefault(x, new HashMap<>());
        int count = yMap.getOrDefault(y, 0);
        yMap.put(y, count+1);
        xyMap.put(x, yMap);
    }
    
    public int count(int[] point) {
        int x = point[0];
        int y = point[1];

        int ans = 0;
        Map<Integer, Integer> yMap = xyMap.getOrDefault(x, new HashMap<>());
        for (Integer y1 : yMap.keySet()) {
            if (y1 == y) {
                continue;
            }
            int x1 = x;

            int x2 = x + y - y1;
            int y2 = y;
            ans += getSquaresCount(x1, y1, x2, y2);

            x2 = x + y1 - y;
            y2 = y;
            ans += getSquaresCount(x1, y1, x2, y2);
        }
        return ans;
    }

    private int getPointCount(int x, int y) {
        if (!xyMap.containsKey(x)) {
            return 0;
        }
        Map<Integer, Integer> yMap = xyMap.get(x);
        if (!yMap.containsKey(y)) {
            return 0;
        }
        return yMap.get(y);
    }

    private int getSquaresCount(int x1, int y1, int x2, int y2) {
        int count1 = getPointCount(x1, y1);

        int count2 = getPointCount(x2, y2);
            
        int x3 = x2;
        int y3 = y1;
        int count3 = getPointCount(x3, y3);
        
        return count1 * count2 * count3;
    }

    
    public static void main(String[] args) {
        DetectSquares ds = new DetectSquares();
        int[] point = {};
        ds.add(point);
        System.out.println("add: " + ds.count(point));
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */