class Solution {
    private static final String SPACE = " ";
    private static final char DOLLAR = '$';
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(SPACE);
        double remainRate = (double)(100 - discount) / 100;
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (!isPrice(word)) {
                continue;
            }
            long price = Long.parseLong(word.substring(1));
            String remainStr = String.format("$%.2f", price * remainRate);
            words[i] = remainStr;
        }
        return String.join(SPACE, words);
    }

    private boolean isPrice(String word) {
        int n = word.length();
        if (word.charAt(0) != DOLLAR || n < 2) {
            return false;
        }
        for (int i = 1; i < n; i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String sentence = "there are $1 $2 and 5$ candies in the shop";
        int discount = 50;
        System.out.println("test: " + sol.discountPrices(sentence, discount));
    }
}