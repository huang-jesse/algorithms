import java.util.Arrays;

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int m = flowerbed.length;
        if (m <= 2) {
            // flowerbed[0] + flowerbed[1] == 0
            if (Arrays.stream(flowerbed).sum() == 0) {
                return n <= 1;
            } else {
                return n == 0;
            }
        }
        int count = 0;
        int index = 0;
        if (flowerbed[index] == 0 && flowerbed[index + 1] == 0) {
            flowerbed[index] = 1;
            count++;
        }
        index += 2;
        while (index < m - 1) {
            int current = flowerbed[index];
            if (current == 0 && flowerbed[index + 1] == 0 && flowerbed[index - 1] == 0) {
                flowerbed[index] = 1;
                count++;
                index += 2;
            } else {
                index++;
            }
        }
        if (flowerbed[m - 2] == 0 && flowerbed[m - 1] == 0) {
            flowerbed[m - 1] = 1;
            count++;
        }
        return count >= n;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] flowerbed = {1,0,0,0,1};
        int n = 1;
        System.out.println("test: " + sol.canPlaceFlowers(flowerbed, n));
    }
}