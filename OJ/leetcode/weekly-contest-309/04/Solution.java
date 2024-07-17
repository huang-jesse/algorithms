import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int m = meetings.length;
        int[] roomsCounter = new int[n];
        // {roomIndex, idleTime}
        PriorityQueue<long[]> pqBusyRooms = new PriorityQueue<>((o1, o2) -> {
            int compare = Long.compare(o1[1], o2[1]);
            if (compare == 0) {
                return Long.compare(o1[0], o2[0]);
            } else {
                return compare;
            }
        });
        // {roomIndex}
        List<Integer> indexesList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> pqIdleRooms = new PriorityQueue<>(indexesList);
        Arrays.sort(meetings, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < m; i++) {
            int[] curMeeting = meetings[i];
            long startTime = curMeeting[0];
            long endTime = curMeeting[1];
            long meetingTime = endTime - startTime;

            while (!pqBusyRooms.isEmpty() && pqBusyRooms.peek()[1] <= startTime) {
                int idleRoomIndex = (int)pqBusyRooms.poll()[0];
                pqIdleRooms.offer(idleRoomIndex);
            }

            if (pqIdleRooms.isEmpty()) {
                long[] nowIdleRoomInfo = pqBusyRooms.poll();
                int idleRoomIndex = (int)nowIdleRoomInfo[0];
                startTime = nowIdleRoomInfo[1];
                pqIdleRooms.offer(idleRoomIndex);
            }

            int usedRoomIndex = pqIdleRooms.poll();
            roomsCounter[usedRoomIndex]++;
            pqBusyRooms.offer(new long[]{usedRoomIndex, startTime + meetingTime});
        }
        int indexOfLargestCount = 0;
        for (int i = 0; i < n; i++) {
            if (roomsCounter[i] > roomsCounter[indexOfLargestCount]) {
                indexOfLargestCount = i;
            }
        }
        return indexOfLargestCount;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int[][] meetings = {{18,19},{3,12},{17,19},{2,13},{7,10}};
        // int n = 3;
        // int[][] meetings = {{1,20},{2,10},{3,5},{4,9},{6,8}};
        // int n = 2;
        // int[][] meetings = {{0,10},{1,5},{2,7},{3,4}};
        System.out.println("test: " + sol.mostBooked(n, meetings));
    }
}