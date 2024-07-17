import java.util.PriorityQueue;

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */
class SeatManager {

    PriorityQueue<Integer> sm;

    public SeatManager(int n) {
        sm = new PriorityQueue<Integer>(n);
        for (int i = 1; i <= n; i++) {
            sm.offer(i);
        }
    }
    
    public int reserve() {
        return sm.poll();
    }
    
    public void unreserve(int seatNumber) {
        sm.offer(seatNumber);
    }

    public static void main(String[] args) {
        SeatManager sol = new SeatManager(1);
        // System.out.println("test: " + sol.test());
    }
}