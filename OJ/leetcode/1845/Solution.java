import java.util.PriorityQueue;

class SeatManager {
    PriorityQueue<Integer> pq;
    public SeatManager(int n) {
        this.pq = new PriorityQueue<>(n);
        for (int i = 1; i <= n; i++) {
            this.pq.offer(i);
        }
    }

    public int reserve() {
        return this.pq.poll();
    }

    public void unreserve(int seatNumber) {
        this.pq.offer(seatNumber);
    }

    public static void main(String[] args) {
        int n = 5;
        SeatManager obj = new SeatManager(n);
        int param_1 = obj.reserve();
        int seatNumber = 1;
        obj.unreserve(seatNumber);
    }
}