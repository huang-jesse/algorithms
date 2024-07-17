import java.util.ArrayList;
import java.util.List;

class SnapshotArray {
    private List<int[]>[] historys;
    private int snap_id;
    public SnapshotArray(int length) {
        this.historys = (List<int[]>[])new List[length];
        for (int i = 0; i < length; i++) {
            this.historys[i] = new ArrayList<>();
        }
        // 此后更改的值，快照都将是 >= 0
        this.snap_id = 0;
    }

    public void set(int index, int val) {
        // 所有 set 都仅保存当前的修改履历，{snap_id, val}
        this.historys[index].add(new int[]{this.snap_id, val});
    }

    public int snap() {
        // 返回当前版本号
        return this.snap_id++;
    }

    public int get(int index, int snap_id) {
        if (snap_id > this.snap_id) {
            // 不存在该版本快照
            throw new IllegalArgumentException();
        }
        int idx = binarySearch(index, snap_id);
        if (idx < 0) {
            // 二分查找没有结果，则表示该 snap_id 时没有进行修改，直接返回 0
            return 0;
        } else {
            return this.historys[index].get(idx)[1];
        }
    }

    /**
     * 二分查找获取 historys[index] 中小于等于 snap_id 的最大值的 idx
     * @param index
     * @param snap_id
     * @return idx
     */
    private int binarySearch(int index, int snap_id) {
        // {snap_id, val}
        List<int[]> snapIds = this.historys[index];
        if (snapIds.isEmpty() || snapIds.get(0)[0] > snap_id) {
            // 找不到目标值
            return -1;
        }
        int n = snapIds.size();
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (snapIds.get(mid)[0] <= snap_id) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // l 对应的 idx
        return l;
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        int snap_id = snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        System.out.println("snap_id: " + snap_id);
        snapshotArr.set(0,6);
        int val = snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
        System.out.println("array[0]: " + val);
    }
}