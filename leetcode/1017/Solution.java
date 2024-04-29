class Solution {
    public String baseNeg2(int n) {
        StringBuilder sb = new StringBuilder();
        if (n == 0) {
            return "0";
        }
        while(n != 0) {
            int remainder = n % (-2);
            n /= (-2);
            if (remainder == -1) {
                // 余数不可为 -1 否则无法表示为二进制位，因此要将余数 = 1，除数同时加 1
                // a = (-2) * b + c
                // a - (-2) * b = c
                // a - (-2) * b + 2 = c + 2
                // a - (-2) * (b + 1) = c + 2
                // a = (-2) * (b + 1) + (c + 2)
                remainder += 2;
                n += 1;
            }
            sb.append(remainder);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        System.out.println("test: " + sol.baseNeg2(n));
    }
}