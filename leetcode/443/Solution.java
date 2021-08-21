class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        int ans = 0;
        int index = 0;
        char temp = chars[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            char cur = chars[i];
            if (temp != cur) {
                seCompressChar(chars, index, temp, count);
                int compressDigits = getCompressDigits(count);
                ans += compressDigits;
                index += compressDigits;
                count = 1;
                temp = cur;
            } else {
                count++;
            }
        }
        seCompressChar(chars, index, temp, count);
        int compressDigits = getCompressDigits(count);
        ans += compressDigits;

        return ans;
    }

    private void seCompressChar(char[] chars, int index, char cur, int count) {
        chars[index++] = cur;
        if (count > 1) {
            char[] compressChars = String.valueOf(count).toCharArray();
            for (int i = 0; i < compressChars.length; i++) {
                chars[index+i] = compressChars[i];
            }
        }
    }

    private static int getCompressDigits(int count) {
        if (count == 1) {
            return 1;
        } else {
            return String.valueOf(count).length() + 1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[] chars = {'a','a','b','b','c','c','c'};
        System.out.println("test: " + sol.compress(chars));
        System.out.println("chars: " + new String(chars));
    }
}