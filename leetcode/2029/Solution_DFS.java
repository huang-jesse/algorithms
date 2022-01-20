import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution_DFS {
    private int[] counter = new int[3];
    private int totalStones = 0;
    private List<Map<String, Boolean>> memo = new ArrayList<Map<String, Boolean>>();
    public boolean stoneGameIX(int[] stones) {
        for (int i = 0; i < 3; i++) {
            memo.add(new HashMap<>());
        }
        for (int stone : stones) {
            counter[stone % 3]++;
            totalStones++;
        }
        return stoneGame(0, 1);
    }
    
    private boolean stoneGame(int removeVal, int times) {
        if (times % 2 == 0 && times > totalStones) {
            // Bob's turn and there are no remaining stones.
            return true;
        }
        Map<String, Boolean> memoMap = memo.get(removeVal);
        String memoKey = counter[0] + "_" + counter[1] + "_" + counter[2];
        if (memoMap.containsKey(memoKey)) {
            return memoMap.get(memoKey);
        }
        boolean res = false;
        for (int i = 0; i < 3; i++) {
            int mod = (removeVal + i) % 3;
            if (counter[i] > 0 && mod != 0) {
                counter[i]--;
                res = res || !stoneGame(mod, times+1);
                counter[i]++;
                if (res) {
                    break;
                }
            }
        }
        memoMap.put(memoKey, res);
        return res;
    }

    public static void main(String[] args) {
        Solution_DFS sol = new Solution_DFS();
        int[] stones = {5,1,2,4,3};
        // int[] stones = {2,1};
        // int[] stones = {2};
        System.out.println("test: " + sol.stoneGameIX(stones));
    }
}