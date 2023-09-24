import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class LockingTree {
    private int[] parent;
    private List<Integer>[] tree;
    private int[] locks;
    public LockingTree(int[] parent) {
        int n = parent.length;
        this.parent = parent;
        this.tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }
        this.locks = new int[n];
    }

    public boolean lock(int num, int user) {
        if (locks[num] == 0) {
            locks[num] = user;
            return true;
        } else {
            return false;
        }
    }

    public boolean unlock(int num, int user) {
        if (locks[num] == user) {
            locks[num] = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean upgrade(int num, int user) {
        if (locks[num] == 0 && hasOneLockedDescendant(num) && !hashAnyLockedAncestors(num)) {
            locks[num] = user;
            unlockAllDescendant(num);
            return true;
        } else {
            return false;
        }
    }

    private boolean hasOneLockedDescendant(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (locks[current] != 0) {
                    return true;
                }
                for (int next : tree[current]) {
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    private void unlockAllDescendant(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (int next : tree[current]) {
                    locks[next] = 0;
                    queue.offer(next);
                }
            }
        }
    }

    private boolean hashAnyLockedAncestors(int num) {
        int current = num;
        while (parent[current] != -1) {
            if (locks[parent[current]] != 0) {
                return true;
            } else {
                current = parent[current];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        LockingTree obj = new LockingTree(parent);
        boolean param_1 = obj.lock(2, 2);
        System.out.printf("param_1: %s\n", param_1); // true
        boolean param_2 = obj.unlock(2, 3);
        System.out.printf("param_2: %s\n", param_2); // false
        boolean param_4 = obj.upgrade(0, 1);
        System.out.printf("param_4: %s\n", param_4); // true
    }
}