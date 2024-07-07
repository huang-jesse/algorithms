import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<String> list;
    private int n;
    public List<String> validStrings(int n) {
        this.n = n;
        this.list = new ArrayList<>();
        backtrack(0, 0);
        return list;
    }

    private void backtrack(int index, int mask) {
        if (index == n) {
            this.list.add(getBinaryString(mask));
            return;
        }
        int pre = 1;
        if (index > 0) {
            pre = (mask >> (index - 1)) & 1;
        }
        if (pre == 1) {
            // bit 0
            backtrack(index + 1, mask);
        }
        mask |= (1 << index);
        backtrack(index + 1, mask);
    }

    private String getBinaryString(int mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((mask >> i) & 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        System.out.println("test: " + sol.validStrings(n));
    }
}