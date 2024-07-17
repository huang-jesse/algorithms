class Solution {
    private static final char EMPTY_CHAR = 0;
    public String convert(String s, int numRows) {
        int n = s.length();
        if (numRows == 1 || numRows >= n) {
            return s;
        }
        // period
        int period = numRows + (numRows - 2);
        int colNumOfPerPeriod = numRows - 1;
        // total col
        int totalCol = (n+period-1) / period * colNumOfPerPeriod;
        char[][] zigZagChars = new char[numRows][totalCol];

        int index = 0;
        int row = 0;
        int col = 0;
        while (index < n) {
            char cur = s.charAt(index);
            zigZagChars[row][col] = cur;
            if (index % period < numRows-1) {
                // vertical
                row++;
            } else {
                // zig
                row--;
                col++;
            }
            index++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < totalCol; j++) {
                if (zigZagChars[i][j] != EMPTY_CHAR) {
                    sb.append(zigZagChars[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println("test: " + sol.convert(s, numRows));
    }
}