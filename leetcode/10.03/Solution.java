class Solution {
    public int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        // 排除索引值位置特殊，如[2,0,1,2,2], 目标为2的情况
        if (arr[0] == target) return 0;
        // 获取索引
        int result = search(arr, target, 0, arr.length-1);
        // 获取最小索引
        return findDupMinIndex(arr, target, result);
    }

    private int search(int[] arr, int target, int left, int right) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        if (arr[mid] == target) return mid;
        // 数组特征，左右两边必定会有一侧有序
        if (arr[mid] > arr[left]) {
            // 左有序
            if (arr[left] <= target && target < arr[mid]) {
                // 可能在左侧，向左搜索
                return search(arr, target, left, mid-1);
            } else {
                // 向右搜索
                return search(arr, target, mid+1, right);
            }
        } else if (arr[mid] < arr[left]) {
            // 右有序
            if (arr[mid] < target && target <= arr[right]) {
                // 可能在右侧，向右搜索
                return search(arr, target, mid+1, right);
            } else {
                // 向左搜索
                return search(arr, target, left, mid-1);
            }
        } else if (arr[mid] == arr[left]) {// 此处也可直接用else替换else if，因为mid与left只有'<' '>' '=' 三种情况
            if (arr[mid] != arr[right]) {//右侧值与mid不相等，则右侧必定无序
                // 右侧无序，则左有序，左侧所有的值都与mid相等，因此排除
                // 向右搜索
                return search(arr, target, mid+1, right);
            } else {
                // 无法判断，左侧、还是右侧有序，因此两边都要搜索
                // 向左搜索
                int result = search(arr, target, left, mid-1);
                if (result == -1) {
                    // 向右搜索
                    result = search(arr, target, mid+1, right);
                }
                return result;
            }
        }
        return -1;
    }

    private int findDupMinIndex(int[] arr, int target, int index) {
        while(index-1 >= 0 && arr[index-1] == target) {
            index--;
        }
        return index;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Set parameters
        int[] testArr = new int[] {4,5,10,12,20,1,2};
        // Do test
        int test = sol.search(testArr, 5);
        System.out.println(test);
    }
}