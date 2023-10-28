class Solution {
    public long pickGifts(int[] gifts, int k) {
        // 原地堆化，构造最大堆
        heapify(gifts);
        while (k > 0) {
            gifts[0] = (int)Math.sqrt(gifts[0]);
            sink(gifts, 0);
            k--;
        }
        long ans = 0;
        for (int gift : gifts) {
            ans += gift;
        }
        return ans;
    }

    private void heapify(int[] h) {
        int n = h.length;
        // 倒序遍历，下沉每个节点
        for (int i = (n - 2) / 2; i >= 0; i--) {
            sink(h, i);
        }
    }

    private void sink(int[] h, int i) {
        int n = h.length;
        while (2 * i + 1 < n) {
            int j = 2 * i + 1;
            // j 为左右子节点的最大值
            if (j + 1 < n && h[j] < h[j + 1]) {
                j++;
            }
            if (h[i] >= h[j]) {
                // 无需下沉
                break;
            }
            // 下沉
            swap(h, i, j);
            // 继续下沉子节点
            i = j;
        }
    }

    private void swap(int[] h, int i, int j) {
        int temp = h[i];
        h[i] = h[j];
        h[j] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] gifts = {25,64,9,4,100};
        int k = 4;
        System.out.println("test: " + sol.pickGifts(gifts, k));
    }
}