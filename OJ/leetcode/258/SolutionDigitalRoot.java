/**
 * Digital_root: https://en.wikipedia.org/wiki/Digital_root
 */
class SolutionDigitalRoot {
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        } else {
            return 1 + ((num-1) % 9);
        }
    }

    public static void main(String[] args) {
        SolutionDigitalRoot sol = new SolutionDigitalRoot();
        int num = 38;
        System.out.println("test: " + sol.addDigits(num));
    }
}