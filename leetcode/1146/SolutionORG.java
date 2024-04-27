import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class SnapshotArrayORG {
    private List<Integer>[] snapshots;
    private Map<Integer, Integer>[] snapshotMaps;
    private Set<Integer> modifiedIndexesSet; // 标记当前被 set 但未 snapshot 的 index
    private int snap_id;

    public SnapshotArrayORG(int length) {
        this.snapshots = (List<Integer>[]) new List[length];
        this.snapshotMaps = (Map<Integer, Integer>[]) new Map[length];
        this.modifiedIndexesSet = new TreeSet<>();
        for (int i = 0; i < length; i++) {
            this.snapshots[i] = new ArrayList<>();
            this.snapshotMaps[i] = new HashMap<>((int)(length / 0.47 + 1), 0.47f);
            // 构造初始值，snap_id = -1 表示原始数组
            this.snapshots[i].add(-1);
            // {snap_id, val}
            this.snapshotMaps[i].put(-1, 0);

            // 默认每一项都已经修改过一次（表示 snap_id = 0 时可以保存全部快照）
            this.modifiedIndexesSet.add(i);
        }
        this.snap_id = 0;
    }

    public void set(int index, int val) {
        // 所有 set 都仅修改 snap_id = -1 的值表示直接在原始数组上修改值。
        this.snapshotMaps[index].put(-1, val);
        // 标记当前 index 已修改
        this.modifiedIndexesSet.add(index);
    }

    public int snap() {
        // 更新本次 modifiedIndexesSet 中的 snapshot 结果
        for (int i : modifiedIndexesSet) {
            // 新增当前版本id的snapshot
            this.snapshots[i].add(this.snap_id);
            // 新增当前版本的值为 snap_id = -1 中的值
            this.snapshotMaps[i].put(this.snap_id, this.snapshotMaps[i].get(-1));
        }
        this.modifiedIndexesSet.clear();
        // 返回当前版本号
        return this.snap_id++;
    }

    public int get(int index, int snap_id) {
        if (snap_id >= this.snap_id) {
            // 不存在该版本快照
            throw new IllegalArgumentException();
        }
        // 二分查找获取 snap_id 对应的快照值
        List<Integer> snapIds = this.snapshots[index];
        int n = snapIds.size();
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (snapIds.get(mid) <= snap_id) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // l 对应的 snap_id
        int resSnapId = snapIds.get(l);
        return this.snapshotMaps[index].get(resSnapId);
    }

    public static void main(String[] args) {
        SnapshotArrayORG snapshotArr = new SnapshotArrayORG(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        int snap_id = snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        System.out.println("snap_id: " + snap_id);
        snapshotArr.set(0,6);
        int val = snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
        System.out.println("array[0]: " + val);
    }
}