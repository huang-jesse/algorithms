class SolutionOptimization {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int m = flowerbed.length;
        int count = 0;
        int pre = -1;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                if (pre < 0) {
                    // 左边界计算
                    count += i / 2;
                } else {
                    count += (i - pre - 2) / 2;
                }
                pre = i;
            }
        }
        if (pre < 0) {
            // 花坛上没有花
            count += (m + 1) / 2;
        } else {
            // 右边界计算
            count += (m - pre - 1) / 2;
        }
        return count >= n;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] flowerbed = {1,0,0,0,1};
        int n = 1;
        System.out.println("test: " + sol.canPlaceFlowers(flowerbed, n));
    }
}