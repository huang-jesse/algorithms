import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String reformat(String s) {
        int n = s.length();
        Queue<Character> letterQueue = new LinkedList<>();
        Queue<Character> digitQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                digitQueue.offer(cur);
            } else {
                letterQueue.offer(cur);
            }
        }
        if (Math.abs(letterQueue.size() - digitQueue.size()) > 1) {
            return "";
        }
        Queue<Character> firstQueue;
        Queue<Character> secondQueue;
        if (letterQueue.size() > digitQueue.size()) {
            firstQueue = letterQueue;
            secondQueue = digitQueue;
        } else {
            firstQueue = digitQueue;
            secondQueue = letterQueue;
        }

        StringBuilder sb = new StringBuilder();
        while (!firstQueue.isEmpty()) {
            sb.append(firstQueue.poll());
            if (!secondQueue.isEmpty()) {
                sb.append(secondQueue.poll());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "a0b1c2";
        System.out.println("test: " + sol.reformat(s));
    }
}