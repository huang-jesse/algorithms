class Solution {
    private static final int DIMENSION_LIMIT = 10_000;
    private static final int VOLUMN_LIMIT = 1_000_000_000;
    private static final int MASS_LIMIT = 100;
    public String categorizeBox(int length, int width, int height, int mass) {
        if (isBulky(length, width, height) && isHeavy(mass)) {
            return "Both";
        } else if (isBulky(length, width, height)) {
            return "Bulky";
        } else if (isHeavy(mass)) {
            return "Heavy";
        } else {
            return "Neither";
        }
    }

    private boolean isHeavy(int mass) {
        return mass >= MASS_LIMIT;
    }

    private boolean isBulky(int length, int width, int height) {
        return length >= DIMENSION_LIMIT || width >= DIMENSION_LIMIT || height >= DIMENSION_LIMIT || (long)length * width * height >= VOLUMN_LIMIT;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int length = 1000;
        int width = 35;
        int height = 700;
        int mass = 300;
        System.out.println("test: " + sol.categorizeBox(length, width, height, mass));
    }
}