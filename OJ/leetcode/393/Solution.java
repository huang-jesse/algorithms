class Solution {
    public boolean validUtf8(int[] data) {
        int n = data.length;
        int index = 0;
        boolean isValid = true;
        while (index < n) {
            int num = data[index];
            if ((num >> 7) == 0) {
                // 1 byte
                index++;
            } else if ((num >> 5) == 6) {
                // 2 byte
                if (index + 1 >= n || !validUnicode(data, index+1, index+1)) {
                    isValid = false;
                    break;
                }
                index = index + 2;
            } else if ((num >> 4) == 14) {
                // 3 byte
                if (index + 2 >= n || !validUnicode(data, index+1, index+2)) {
                    isValid = false;
                    break;
                }
                index = index + 3;
            } else if ((num >> 3) == 30) {
                // 4 byte
                if (index + 3 >= n || !validUnicode(data, index+1, index+3)) {
                    isValid = false;
                    break;
                }
                index = index + 4;
            } else {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private static boolean validUnicode(int[] data, int start, int end) {
        for (int i = start; i <= end; i++) {
            int num = data[i];
            if ((num >> 6) != 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] data = {230,136,145};
        System.out.println("test: " + sol.validUtf8(data));
    }
}