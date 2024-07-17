class Solution {
    public int countPoints(String rings) {
        int n = rings.length() / 2;
        int[] rods = new int[10];
        for (int i = 0; i < n; i++) {
            char ring = rings.charAt(2 * i);
            int rod = rings.charAt(2 * i + 1) - '0';
            if (ring == 'R') {
                rods[rod] |= 1;
            } else if (ring == 'G') {
                rods[rod] |= (1 << 1);
            } else {
                // B
                rods[rod] |= (1 << 2);
            }
        }
        int ans = 0;
        for (int rod : rods) {
            if (rod == 7) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String rings = "B0R0G0R9R0B0G0";
        System.out.println("test: " + sol.countPoints(rings));
    }
}