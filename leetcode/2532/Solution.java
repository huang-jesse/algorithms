import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public final static int INF = (int)(1e9 + 7);
    public Comparator<Worker> compareEfficient = (o1, o2) -> {
        boolean isLessEfficient = false;
        if ((o1.leftToRight + o1.rightToLeft) > (o2.leftToRight + o2.rightToLeft)) {
            isLessEfficient = true;
        } else if ((o1.leftToRight + o1.rightToLeft) == (o2.leftToRight + o2.rightToLeft)
            && o1.id > o2.id) {
            isLessEfficient = true;
        }
        if (isLessEfficient) {
            return -1;
        } else {
            return 1;
        }
    };
    public Comparator<Worker> compareTimeAndEfficient = (o1, o2) -> {
        // Primary compare
        if (o1.workedTime != o2.workedTime) {
            return o1.workedTime < o2.workedTime ? -1 : 1;
        } else {
            // o1.workedTime == o2.workedTime
            // Secondary compare
            return compareEfficient.compare(o1, o2);
        }
    };
    class Worker {
        int id;
        int workedTime;
        int leftToRight;
        int pickOld;
        int rightToLeft;
        int putNew;
        public Worker(int id, int workedTime, int leftToRight, int pickOld, int rightToLeft, int putNew) {
            this.id = id;
            this.workedTime = workedTime;
            this.leftToRight = leftToRight;
            this.pickOld = pickOld;
            this.rightToLeft = rightToLeft;
            this.putNew = putNew;
        }
    }

    class CrossingQueue {
        private PriorityQueue<Worker> busyWorkersPq;
        private PriorityQueue<Worker> waitingWorkersPq;
        public CrossingQueue() {
            this.busyWorkersPq = new PriorityQueue<>(compareTimeAndEfficient);
            this.waitingWorkersPq = new PriorityQueue<>(compareEfficient);
        }

        public void offer(Worker worker) {
            this.busyWorkersPq.offer(worker);
        }

        public Worker pollWaitingWorker() {
            if (this.waitingWorkersPq.isEmpty()) {
                return null;
            }
            return this.waitingWorkersPq.poll();
        }

        public Worker peekBusyWorker() {
            if (this.hasBusyWorkers()) {
                return this.busyWorkersPq.peek();
            } else {
                return null;
            }
        }

        public boolean hasWaitingWorker() {
            return !this.waitingWorkersPq.isEmpty();
        }

        public boolean hasBusyWorkers() {
            return !this.busyWorkersPq.isEmpty();
        }

        public void refreshWaitingWorkers(int currentTime) {
            if (this.isEmpty()) {
                return;
            }
            while (!this.busyWorkersPq.isEmpty() && this.busyWorkersPq.peek().workedTime <= currentTime) {
                this.waitingWorkersPq.offer(this.busyWorkersPq.poll());
            }
        }

        public boolean isEmpty() {
            return this.busyWorkersPq.isEmpty() && this.waitingWorkersPq.isEmpty();
        }
    }

    public int findCrossingTime(int n, int k, int[][] time) {
        CrossingQueue leftPq = new CrossingQueue();
        CrossingQueue rightPq = new CrossingQueue();

        // Waiting in left bank of river
        for (int i = 0; i < time.length; i++) {
            leftPq.offer(new Worker(i, 0, time[i][0], time[i][1], time[i][2], time[i][3]));
        }
        // Crossing bridge
        int currentTime = 0;
        int ans = 0;
        while (n > 0 || !rightPq.isEmpty()) {
            // Refresh waiting workers
            rightPq.refreshWaitingWorkers(currentTime);
            leftPq.refreshWaitingWorkers(currentTime);
            // Check if the worker waiting on the both side of the bridge
            if (rightPq.hasWaitingWorker()) {
                // The worker waiting on the right side of the bridge, so right side first
                // Right to left
                currentTime = corssRightToLeft(currentTime, leftPq, rightPq);
                ans = currentTime;
                continue;
            }
            if (n > 0 && leftPq.hasWaitingWorker()) {
                // The worker only waiting on the left side of the bridge, so left side first
                // Left to right
                currentTime = corssLeftToRight(currentTime, leftPq, rightPq);
                // Pick a box
                n--;
                continue;
            }
            // There are no worker waiting on the both side, then set the currentTime to the min workedTime
            int newTime = INF;
            if (rightPq.hasBusyWorkers()) {
                newTime = Math.min(rightPq.peekBusyWorker().workedTime, newTime);
            }
            if (leftPq.hasBusyWorkers()) {
                newTime = Math.min(leftPq.peekBusyWorker().workedTime, newTime);
            }
            if (newTime != INF) {
                currentTime = Math.max(newTime, currentTime);
            }
        }
        return ans;
    }

    private int corssLeftToRight(int currentTime, CrossingQueue leftPq, CrossingQueue rightPq) {
        // Left to right
        Worker worker = leftPq.pollWaitingWorker();
        currentTime = currentTime + worker.leftToRight;
        worker.workedTime = currentTime + worker.pickOld;
        rightPq.offer(worker);
        return currentTime;
    }

    private int corssRightToLeft(int currentTime, CrossingQueue leftPq, CrossingQueue rightPq) {
        // Right to left
        Worker worker = rightPq.pollWaitingWorker();
        currentTime = currentTime + worker.rightToLeft;
        worker.workedTime = currentTime + worker.putNew;
        leftPq.offer(worker);
        return currentTime;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int k = 2;
        int[][] time = {{1,9,1,8},{10,10,10,10}};
        // int n = 1;
        // int k = 3;
        // int[][] time = {{1,1,2,1},{1,1,3,1},{1,1,4,1}};
        System.out.println("test: " + sol.findCrossingTime(n, k, time));
    }
}