import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class DetectSquares {
    private Map<Integer, Integer> encodePoints = new HashMap<>();
    private Map<Integer, Set<Integer>> xAxis = new HashMap<>();
    public DetectSquares() {
    }
    
    public void add(int[] point) {
        Integer encode = encode(point);
        encodePoints.put(encode, encodePoints.getOrDefault(encode, 0) + 1);
        
        int x = point[0];
        Set<Integer> encodeSet = xAxis.getOrDefault(x, new HashSet<>());
        encodeSet.add(encode);
        xAxis.put(x, encodeSet);
    }
    
    public int count(int[] point) {
        int x1 = point[0];
        int y1 = point[1];
        Set<Integer> verticalEncodePoints = xAxis.getOrDefault(x1, new HashSet<>());
        int res = 0;
        for (int xy2Encode : verticalEncodePoints) {
            int xy2Count = encodePoints.getOrDefault(xy2Encode, 0);
            int[] xy2Point = decode(xy2Encode);
            int y2 = xy2Point[1];
            int sideLength = Math.abs(y2-y1);
            if (sideLength == 0) {
                continue;
            }
            // right side
            if (x1+sideLength <= 1000) {
                int xy3Encode = encode(new int[]{x1+sideLength, y1});
                int xy3Count = encodePoints.getOrDefault(xy3Encode, 0);
                int xy4Encode = encode(new int[]{x1+sideLength, y2});
                int xy4Count = encodePoints.getOrDefault(xy4Encode, 0);
                res += xy2Count * xy3Count * xy4Count;
            }
            // left side
            if (x1-sideLength >= 0) {
                int xy3Encode = encode(new int[]{x1-sideLength, y1});
                int xy3Count = encodePoints.getOrDefault(xy3Encode, 0);
                int xy4Encode = encode(new int[]{x1-sideLength, y2});
                int xy4Count = encodePoints.getOrDefault(xy4Encode, 0);
                res += xy2Count * xy3Count * xy4Count;
            }
        }
        return res;
    }

    private int encode(int[] point) {
        return point[0] * 1001 + point[1];
    }

    private int[] decode(int encode) {
        return new int[]{encode / 1001, encode % 1001};
    }

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
    public static void main(String[] args) {
        DetectSquares ds = new DetectSquares();
        ds.add(new int[]{3, 10});
        ds.add(new int[]{11, 2});
        ds.add(new int[]{3, 2});
        System.out.println("count: " + ds.count(new int[]{11, 10}));
        System.out.println("count: " + ds.count(new int[]{14, 8}));
    }
}