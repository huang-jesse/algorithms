import java.util.HashMap;
import java.util.Map;

class FindSumPairs {

    int[] nums1;
    int[] nums2;
    Map<Integer, Integer> nums2Map;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.nums2Map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            int cur = nums2[i];
            this.nums2Map.put(cur, this.nums2Map.getOrDefault(cur, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int cur = this.nums2[index];
        this.nums2[index] += val;
        int after = this.nums2[index];
        int curCount = this.nums2Map.get(cur);
        if (curCount > 1) {
            this.nums2Map.put(cur, curCount-1);
        } else {
            this.nums2Map.remove(cur);
        }
        this.nums2Map.put(after, this.nums2Map.getOrDefault(after, 0)+1);
    }

    public int count(int tot) {
        int count = 0;
        for (int i : nums1) {
            int temp = tot - i;
            count += nums2Map.getOrDefault(temp, 0);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,1,2,2,2,3};
        int[] nums2 = new int[]{1,4,5,2,5,4};
        FindSumPairs find = new FindSumPairs(nums1, nums2);
        find.count(7);
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */