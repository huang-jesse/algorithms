import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        // all tasks
        Task[] tsks = new Task[n];
        for (int i = 0; i < n; i++) {
            tsks[i] = new Task(tasks[i][0], tasks[i][1], i);
        }
        // sort by enqueueTime
        Arrays.sort(tsks, Comparator.comparing(task -> task.eTime));
        // the queue of waiting for run
        PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.<Task>comparingInt(task -> task.pTime).thenComparing(task -> task.id));

        // index of tsks
        int index = 0;
        // thread time
        int time = 0;
        List<Integer> orders = new ArrayList<>(n);
        while (index < n || !queue.isEmpty()) {
            // fast-forward thread time to the eTime of current task, when thread idle
            if (queue.isEmpty()) {
                time = Math.max(time, tsks[index].eTime);
            }
            // add task to the queue, when current thread time is on the enqueue time
            while (index < n && tsks[index].eTime <= time) {
                queue.add(tsks[index]);
                index++;
            }
            // if queue is not empty, runing task
            if (!queue.isEmpty()) {
                Task runingTask = queue.poll();
                time = time + runingTask.pTime;
                orders.add(runingTask.id);
            }
        }

        return orders.stream().mapToInt(Integer::valueOf).toArray();
    }

    private class Task {
        int eTime;
        int pTime;
        int id;
        Task(int eTime, int pTime, int id) {
            this.eTime = eTime;
            this.pTime = pTime;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        int[][] tasks = new int[][]{{1,2},{2,4},{3,2},{4,1}};
        Solution sol = new Solution();
        int[] orders = sol.getOrder(tasks);
        List<Integer> list = Arrays.stream(orders).boxed().collect(Collectors.toList());
        System.out.println("Orders: " + list);
    }
}