import java.util.LinkedList;

class Solution {
    public int findTheWinner(int n, int k) {
        if (n == 1) return 1;
        if (k == 1) return n;

        LinkedList<Integer> link = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            link.add(i);
        }
        // System.out.println(link);
        int current = 0;
        while(link.size() > 1) {
            int size = link.size();
            int mod = k % size;
            int move = (mod == 0 ? size : mod) - 1;
            int index = current+move > size-1 ? (current+move) - (size) : current+move;

            link.remove(index);
            current = index == link.size() ? 0 : index;
            // System.out.println("index: "+index);
            // System.out.println(link);
            // System.out.println("current: "+current);
        }
        return link.getFirst();
    }

    public static void main(String[] args) {
        int n = 6;
        int k = 5;
        Solution sol = new Solution();
        System.out.println("Winner: " + sol.findTheWinner(n, k));
    }
}