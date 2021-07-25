import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public List<List<Long>> splitPainting(int[][] segments) {
        int n = segments.length;
        if (n == 1) {
            int[] segment = segments[0];
            List<Long> seg = Arrays.stream(segment).mapToLong(num -> num).boxed().collect(Collectors.toList());
            return Arrays.asList(seg);
        }

        List<List<Long>> painting = new ArrayList<>();
        for (int[] segment : segments) {
            addSegment(painting, segment);
        }
        return painting;
    }

    private static void addSegment(List<List<Long>> painting, int[] segment) {
        long start = segment[0];
        long end = segment[1];
        long color = segment[2];
        int n = painting.size();
        if (painting.isEmpty()) {
            List<Long> segmentList = Arrays.asList(start, end, color);
            painting.add(segmentList);
        } else {
            int minStart = getMinStartIndex(painting, start);
            int maxEnd = getMaxEndIndex(painting, end);
            if (minStart != n && maxEnd != -1) {
                mixSegment(painting, segment, minStart, maxEnd);
            } else {
                List<Long> segmentList = Arrays.asList(start, end, color);
                painting.add(segmentList);
            }
        }
    }

    private static void mixSegment(List<List<Long>> painting, int[] segment, int startOfMix, int endOfMix) {
        long color = segment[2];
        if (startOfMix == endOfMix) {
            mixMiddle(painting, segment, startOfMix, endOfMix);
        } else {
            mixLeft(painting, segment, startOfMix);
            mixRight(painting, segment, endOfMix);
            for (int i = startOfMix+1; i < endOfMix; i++) {
                List<Long> segmentList = painting.get(i);
                long colorOfSeg = segmentList.get(2);
                segmentList.set(2, colorOfSeg + color);
            }
        }
    }

    private static void mixLeft(List<List<Long>> painting, int[] segment, int startOfMix) {
        long start = segment[0];
        long color = segment[2];
        
        List<Long> segmentList = painting.get(startOfMix);
        long startOfSeg = segmentList.get(0);
        long endOfSeg = segmentList.get(1);
        long colorOfSeg = segmentList.get(2);

        painting.remove(startOfMix);
        int insertIndex = startOfMix;
        if (start != startOfSeg) {
            long curColor = start < startOfSeg ? color : colorOfSeg;
            List<Long> head = Arrays.asList(Math.min(start, startOfSeg), Math.max(start, startOfSeg), curColor);
            painting.add(insertIndex++, head);
        }
        List<Long> middle = Arrays.asList(Math.max(start, startOfSeg), endOfSeg, color + colorOfSeg);
        painting.add(insertIndex, middle);
    }

    private static void mixRight(List<List<Long>> painting, int[] segment, int endOfMix) {
        long end = segment[1];
        long color = segment[2];
        
        List<Long> segmentList = painting.get(endOfMix);
        long startOfSeg = segmentList.get(0);
        long endOfSeg = segmentList.get(1);
        long colorOfSeg = segmentList.get(2);

        painting.remove(endOfMix);
        int insertIndex = endOfMix;
        if (end != endOfSeg) {
            long curColor = end > endOfSeg ? color : colorOfSeg;
            List<Long> head = Arrays.asList(Math.min(end, endOfSeg), Math.max(end, endOfSeg), curColor);
            painting.add(insertIndex++, head);
        }
        List<Long> middle = Arrays.asList(startOfSeg, Math.min(end, endOfSeg), color + colorOfSeg);
        painting.add(insertIndex, middle);
    }

    private static void mixMiddle(List<List<Long>> painting, int[] segment, int startOfMix, int endOfMix) {
        long start = segment[0];
        long end = segment[1];
        long color = segment[2];

        List<Long> segmentList = painting.get(startOfMix);
        long startOfSeg = segmentList.get(0);
        long endOfSeg = segmentList.get(1);
        long colorOfSeg = segmentList.get(2);
        if (startOfSeg == start && endOfSeg == end) {
            segmentList.set(2, colorOfSeg + color);
        } else {
            painting.remove(startOfMix);
            int insertIndex = startOfMix;
            if (startOfSeg != start) {
                long curColor = startOfSeg < start ? colorOfSeg : color;
                List<Long> head = Arrays.asList(Math.min(startOfSeg, start), Math.max(startOfSeg, start), curColor);
                painting.add(insertIndex++, head);
            }
            List<Long> middle = Arrays.asList(Math.max(startOfSeg, start), Math.min(endOfSeg, end), color + colorOfSeg);
            painting.add(insertIndex++, middle);
            if (endOfSeg != end) {
                long curColor = endOfSeg > end ? colorOfSeg : color;
                List<Long> tail = Arrays.asList(Math.min(endOfSeg, end), Math.max(endOfSeg, end), curColor);
                painting.add(insertIndex, tail);
            }
        }
    }

    private static int getMinStartIndex(List<List<Long>> painting, long start) {
        int n = painting.size();
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            List<Long> segment = painting.get(mid);
            long endOfSeg = segment.get(1);
            if (start < endOfSeg) {
                right = mid;
            } else {
                // start >= endOfSeg
                left = mid + 1;
            }
        }
        return right;
    }

    private static int getMaxEndIndex(List<List<Long>> painting, long end) {
        int n = painting.size();
        int left = -1;
        int right = n-1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            List<Long> segment = painting.get(mid);
            long startOfSeg = segment.get(0);
            if (end > startOfSeg) {
                left = mid;
            } else {
                // end <= startOfSeg
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] segments = {{1,7,9},{6,8,15},{8,10,7}};
        //ans: [[1,6,9],[6,7,24],[7,8,15],[8,10,7]]
        int[][] segments = {{4,5,9},{8,12,5},{4,7,19},{14,15,1},{3,10,8},{17,20,18},{7,19,14},{8,16,6},{14,17,7},{11,13,3}};
        //ans: [[3,4,8],[4,5,36],[5,7,27],[7,8,22],[8,10,33],[10,11,25],[11,12,28],[12,13,23],[13,14,20],[14,15,28],[15,16,27],[16,17,21],[17,19,32],[19,20,18]]

        // int[][] segments = {{1,4,5},{4,7,7},{1,7,9}};
        System.out.println("test: " + sol.splitPainting(segments));
    }
}